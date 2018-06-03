import model.Query;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Collect")
public class Collect extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //检查是否登录
        boolean loginSuccess = false;
        String userName = null;
        String videoID_str = request.getParameter("videoID_str");

        //检查是否登录；获取userName
        Cookie cookies[] = request.getCookies();
        for(int i=0;i<cookies.length; i++){
            if(cookies[i].getName().equals("loginSuccess")){
                if(cookies[i].getValue().equals("true")){
                    loginSuccess = true;
                }
            }
            if(cookies[i].getName().equals("userName")){
                userName = cookies[i].getValue();
            }
        }

        if(loginSuccess){
            Query query = new Query();
            query.initializeResources();

            String sql = "insert into collection values ('"+userName+"','"+videoID_str+"')";
            int effectedLine = query.create(sql);

            query.destroyResources();

            String message = null;
            if(effectedLine == 1){
                message = "收藏成功！";
            }
            else{
                message = "收藏失败，是否已经收藏过了？";
            }

            request.setAttribute("message", message);
            RequestDispatcher view = request.getRequestDispatcher("collect.jsp");
            view.forward(request,response);

            return;
        }else{
            response.sendRedirect("login.jsp");
            return;
        }
    }
}
