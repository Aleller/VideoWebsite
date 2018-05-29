import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;

import model.Query;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "Upload")
public class Upload extends HttpServlet {
    //上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "../../../../VideoWebsite/web/video";

    //上传配置
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; //3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; //40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; //50MB

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contributionName = null;
        String userName = null;
        String fileName = null;

        //检测是否为多媒体上传
        if(!ServletFileUpload.isMultipartContent(request)){
            //如果不是则停止
            PrintWriter writer = response.getWriter();
            writer.println("error:表单必须包含enctype=multipart/form-data");
            writer.flush();
            return;
        }

        //配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //设置内存临界值，超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        //设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        //设置最大文件大小
        upload.setFileSizeMax(MAX_FILE_SIZE);

        //设置最大请求大小
        upload.setSizeMax(MAX_REQUEST_SIZE);

        //中文处理
        upload.setHeaderEncoding("UTF-8");

        //构造临时路径来存放上传的文件
        //这个路径相对于当前应用的目录
        String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY;

        //如果目录不存在则创建
        File uploadDIR = new File(uploadPath);
        if(!uploadDIR.exists()){
            uploadDIR.mkdir();
        }

        boolean writeSuccess = false;
        try{
            //解析请求的内容，提取文件并写入磁盘
            List<FileItem> formItems = upload.parseRequest(request);

            if(formItems != null && formItems.size() > 0){
                //迭代表单数据
                for(FileItem item : formItems){
                    //处理不在表单中的字段
                    if(!item.isFormField()){
                        fileName = new File(item.getName()).getName();

                        //文件重命名：命名为当前最大videoID加1，避免重名
                        Query query = new Query();
                        query.initializeResources();

                        String sql = "select * from video";
                        ResultSet rs = query.retrieve(sql);
                        int count = -1;
                        while(rs.next()){
                            int temp = Integer.parseInt(rs.getString(1));
                            if(count <= temp){
                                count = temp;
                            }
                        }
                        fileName = String.valueOf(count+1) + ".mp4";

                        query.destroyResources();
                        //重命名完成

                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        //在控制台上输出文件的上传路径
                        System.out.println(filePath);
                        //保存文件到硬盘
                        item.write(storeFile);
                        request.setAttribute("message","文件上传成功");

                        writeSuccess = true;

                    }
                    else{
                        String nameTemp = item.getFieldName();

                        // Set charset = UTF-8 Default = ISO-8859-1
                        // Get field value
                        if(nameTemp.equals("contributionName")){
                            contributionName = item.getString("utf-8");
                        }
                    }
                }
            }
        }catch(Exception ex){
            request.setAttribute("message","错误信息：" + ex.getMessage());
        }

        if(writeSuccess){
            //如果文件已经写入磁盘，则记录入库
            Query query = new Query();

            query.initializeResources();

            Cookie cookies[] = request.getCookies();
            if(cookies!=null){
                for(int i=0;i<cookies.length;i++){
                    if(cookies[i].getName().equals("userName")){
                        userName = cookies[i].getValue();
                    }
                }
            }
            String sql = "insert into video values(NULL, '" + userName + "', 'video\\\\"+ fileName +"', '"+ contributionName +"')";
            query.create(sql);

            query.destroyResources();
            //记录已经入库
        }

        //跳转到message.jsp
        getServletContext().getRequestDispatcher("/uploadMessage.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
