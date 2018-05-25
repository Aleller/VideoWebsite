import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Query;

@WebServlet(name = "RegisterCheck")
public class RegisterCheck extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("RegisterCheck Servlet");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password_1");

        boolean successful = false;

        /*在数据库中注册用户*/
        //先检查有没有重复的名字
        boolean repeatName = false;
        //拼sql
        String sql = "select * from user where userName='" + userName + "'";
        //查询
        Query query = new Query();

        query.initializeResources();

        ResultSet rs = query.retrieve(sql);
        try{
            int n = 0;
            while(rs.next()){
                n++;
            }
            if(n>1){
                repeatName = true;
            }
        }catch(java.sql.SQLException e){
            e.printStackTrace();
        }

        //没有的话再插入
        if(!repeatName){
            sql = "insert into user values('" + userName + "','" + password + "')";
            int n = query.create(sql);
            if(n==1){
                successful = true;
            }
        }

        query.destroyResources();

        /*dispatch给registerSuccess.jsp*/
        request.setAttribute("successful", successful);
//        if(successful){
//            request.setAttribute("successful", "true");
//        }else{
//            request.setAttribute("successful", "false");
//        }
        RequestDispatcher view = request.getRequestDispatcher("registerSuccessful.jsp");
        view.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
