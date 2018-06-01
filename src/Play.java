import model.Query;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Play extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*这个servlet的任务是要把对应的video的路径提供给jsp页面*/

        //数据库查询
        Query query = new Query();
        query.initializeResources();

        //String path = "C:/Data/java/VideoWebsite/video/7.mp4";
        //String contributionName = "7v";
        String path = null;
        String contributionName = null;
        String contributionOwner = null;

        String videoID_str = request.getParameter("videoID");
        int videoID = Integer.parseInt(videoID_str);
        String sql = "select * from video where videoID='" + videoID + "'";
        ResultSet rs = query.retrieve(sql);
        try{
            while(rs.next()){
                contributionOwner = rs.getString(2);
                path = rs.getString(3);
                contributionName = rs.getString(4);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        query.destroyResources();

        if(contributionName!=null && contributionOwner!=null && path!=null){
            request.setAttribute("success", "true");
            request.setAttribute("path", path);
            request.setAttribute("contributionName", contributionName);
            request.setAttribute("contributionOwner", contributionOwner);
            request.setAttribute("videoID_str", videoID_str);
        }else{
            request.setAttribute("success", "false");
            request.setAttribute("errorMessage", "视频不见了……");
        }

        RequestDispatcher view = request.getRequestDispatcher("play.jsp");
        view.forward(request, response);
    }
}
