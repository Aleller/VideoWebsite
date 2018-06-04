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

    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>

    <style>
        #uploadForm{
            margin: 0px 400px 0px 400px;
        }
    </style>
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

<div class="text-center">
    <div class="page-header">
        <h1>投稿</h1>
    </div>
</div>

<form id="uploadForm" role="form" method="post" action="upload.do" enctype="multipart/form-data">
    <div class="input-group">
        <span class="input-group-addon">稿件名称：</span>
        <input type="text" class="form-control" name="contributionName">
    </div>
    <br>
    <div class="form-group">
        <label for="inputfile">选择一个mp4文件（仅限AVC编码）：<br></label>
        <input type="file" name="video"/><br>
    </div>
    <button type="submit" class="btn btn-primary">上传</button>
</form>

</body>
</html>
