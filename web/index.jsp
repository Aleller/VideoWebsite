<%@ page import="java.io.PrintWriter" %><%--
    Created by IntelliJ IDEA.
    User: Alell
    Date: 2018.05.18
    Time: 18:26
    To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>网站首页</title>
</head>
<body>

<%!
    public void jspInit() {

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
                out.println(cookie2.getValue() + "loginsuccess!");
            }
        }
    }
%>

</body>
</html>