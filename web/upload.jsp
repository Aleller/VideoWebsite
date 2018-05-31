<%--
  Created by IntelliJ IDEA.
  User: Alell
  Date: 2018.05.27
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>投稿</title>
</head>


<%
    //根据cookie判断用户是否已经登录，如果没有登录，重定向到登录页面
    boolean login = false;
    String loginSuccess = "false";
    String userName;

    Cookie cookies[] = request.getCookies();
    Cookie cookie = null;
    if(cookies != null){
        for(int i=0; i<cookies.length; i++){
            cookie = cookies[i];
            if(cookie.getName().equals("loginSuccess")){
                loginSuccess = cookie.getValue();
            }
            if(cookie.getName().equals("userName")){
                userName = cookie.getValue();
            }
        }
    }

    if(loginSuccess.equals("false")){
        response.sendRedirect("login.jsp");
    }
    //判断完毕
%>

<body>
<form method="post" action="upload.do" enctype="multipart/form-data">
    稿件名称：<br>
    <input type="text" name="contributionName"/><br>
    选择一个mp4文件（仅限AVC编码）：<br>
    <input type="file" name="video"/><br>
    <input type="submit" value="上传"/>
</form>
</body>
</html>
