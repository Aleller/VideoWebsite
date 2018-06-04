import model.Query;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Space extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //这个servlet的作用是将用户请求访问的个人空间中所要展示的信息提供给jsp页面
        String userName = request.getParameter("userName");

        //获取用户的投稿信息
        Query query = new Query();
        query.initializeResources();

        String sql = "select videoID,contributionName from video where userName='" + userName + "'";
        ResultSet resultSet = query.retrieve(sql);
        int contributionCount = 0;
        try{
            while(resultSet.next()){
                contributionCount++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        String videoID[] = new String[contributionCount];
        String contributionName[] = new String[contributionCount];

        resultSet = query.retrieve(sql);
        try{
            int i=0;
            while(resultSet.next()){
                videoID[i] = resultSet.getString(1);
                contributionName[i] = resultSet.getString(2);
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        //获取用户的投稿信息完毕

        //获取用户的收藏信息
        sql = "select * from collection as a,video as b where a.videoID = b.videoID and a.userName='"+userName+"'";
        resultSet = query.retrieve(sql);
        int collectCount = 0;
        try{
            while(resultSet.next()){
                collectCount++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        String collectVideoID[] = new String[collectCount];
        String collectContributionName[] = new String[collectCount];

        resultSet = query.retrieve(sql);
        try{
            int i=0;
            while(resultSet.next()){
                collectVideoID[i] = resultSet.getString("videoID");
                collectContributionName[i] = resultSet.getString("contributionName");
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        //获取收藏信息完毕

        //获取cookie验证用户是否已经登录
        Cookie cookie1 = null;
        Cookie cookie2 = null;
        Cookie cookies[] = request.getCookies();
        boolean loginSuccess = false;
        String loginUserName = null;

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
                    loginUserName = cookie2.getValue();
                }
            }
        }

        request.setAttribute("videoID", videoID);
        request.setAttribute("contributionName", contributionName);
        request.setAttribute("contributionCount", contributionCount);
        request.setAttribute("userName", userName);
        request.setAttribute("collectCount", collectCount);
        request.setAttribute("collectVideoID", collectVideoID);
        request.setAttribute("collectContributionName", collectContributionName);
        request.setAttribute("loginSuccess", loginSuccess);
        request.setAttribute("loginUserName", loginUserName);

        query.destroyResources();

        RequestDispatcher view = request.getRequestDispatcher("space.jsp");
        view.forward(request,response);
    }
}
