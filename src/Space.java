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
        request.setAttribute("videoID", videoID);
        request.setAttribute("contributionName", contributionName);
        request.setAttribute("contributionCount", contributionCount);
        request.setAttribute("userName", userName);

        query.destroyResources();

        RequestDispatcher view = request.getRequestDispatcher("space.jsp");
        view.forward(request,response);
    }
}
