import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginCheck")
public class LoginCheck extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //假设已经验证用户名和密码成功了
        String userName = "admin";
        String password = "admin";

        Cookie cookie1 = new Cookie("loginSuccess","true");
        Cookie cookie2 = new Cookie("userName","admin");
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
