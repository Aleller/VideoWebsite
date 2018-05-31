import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Query;

public class Index extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * 功能：从数据库中随机选择8个视频的信息（videoID, contributionName），以便于jsp进行展示
     * 最好有缩略图……
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取cookie验证用户是否已经登录
        Cookie cookie1 = null;
        Cookie cookie2 = null;
        Cookie cookies[] = request.getCookies();
        boolean loginSuccess = false;
        String userName = null;

        if(cookies != null){
            for(int i=0;i<cookies.length;i++){
                if(cookies[i].getName().equals("loginSuccess")){
                    cookie1 = cookies[i];
                }
                if(cookies[i].getName().equals("userName")){
                    cookie2 = cookies[i];
                }
            }

            if((cookie1 != null) && (cookie2 != null)){
                if(cookie1.getValue().equals("true")){
                    loginSuccess = true;
                    userName = cookie2.getValue();
                }
            }
        }

        Query query = new Query();
        query.initializeResources();

        String sql = "select * from video order by rand() limit 8";
        ResultSet resultSet = query.retrieve(sql);
        String videoID[] = new String[8];//没有考虑数据库里视频记录少于8个的情况
        String contributionName[] = new String[8];
        String contributionOwner[] = new String[8];

        try{
            int i=0;
            while(resultSet.next()){
                videoID[i] = resultSet.getString(1);
                contributionOwner[i] = resultSet.getString(2);
                contributionName[i] = resultSet.getString(4);
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        query.destroyResources();

        request.setAttribute("videoID", videoID);
        request.setAttribute("contributionName", contributionName);
        request.setAttribute("contributionOwner", contributionOwner);
        request.setAttribute("loginSuccess", loginSuccess);
        if(loginSuccess){
            request.setAttribute("userName", userName);
        }

        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
        view.forward(request,response);
    }
}
