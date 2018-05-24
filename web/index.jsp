<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Alell
  Date: 2018.05.23
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>网站首页</title>
</head>
<body>

<%!
    public void jspInit() {
        //初始化数据库连接

        //如果是从login.jsp重定向到index.jsp，查看cookie判断是否已经登录

        //
    }

    public void jspDestroy() {

    }
%>

<%
    //获取cookie验证用户是否已经登录
    Cookie cookie1 = null;
    Cookie cookie2 = null;
    Cookie cookies[] = request.getCookies();
    boolean loginSuccess = false;

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
                out.println(cookie2.getValue() + "login success!");
            }
        }
    }
%>

</body>
</html>
