import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "LoginCheck")
public class LoginCheck extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*获取用户名和密码*/
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        boolean success = false;

        /*在数据库中查询*/
        //获取连接对象conn
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/VideoWebsite";
        String DBUserName = "root";
        String DBPassword = "admin";
        com.mysql.jdbc.Connection conn = null;

        try{
            Class.forName(driver);//classLoader
            conn = (Connection) DriverManager.getConnection(url,DBUserName,DBPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

        //拼接sql语句，进行查询
        String sql = "select * from user where userName='" + userName + "' and password='" + password + "'";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = (PreparedStatement)conn.prepareStatement(sql);
            rs = ps.executeQuery();
            int rowCount = 0;
            while (rs.next()) {//每行
                rowCount++;
                if(rs.getString(2).equals(password)){
                    success = true;
                }
            }
            if(rowCount != 1){
                success = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //关闭数据库连接
        try{
            if(rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            if(ps != null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*设置cookie*/
        if(success){
            //设置登录成功cookie
            Cookie cookie1 = new Cookie("loginSuccess","true");
            Cookie cookie2 = new Cookie("userName", userName);
            response.addCookie(cookie1);
            response.addCookie(cookie2);
        }else{
            //清除cookie
            Cookie cookies[] = request.getCookies();
            for(int i=0;i<request.getCookies().length;i++){
                if(cookies[i].getName().equals("loginSuccess")){
                    cookies[i].setMaxAge(0);
                }
                if(cookies[i].getName().equals("userName")){
                    cookies[i].setMaxAge(0);
                }
            }
        }

        PrintWriter out = response.getWriter();
        //out.println("login success!");

        response.sendRedirect("/index.jsp");

        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
