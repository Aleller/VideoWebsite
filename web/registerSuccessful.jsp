<%--
  Created by IntelliJ IDEA.
  User: Alell
  Date: 2018.05.25
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>

    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>
</head>
<body>
<%
    boolean successful = (boolean)(request.getAttribute("successful"));
    if(successful){
        %>

<div class="text-center">
    <div class="page-header">
        <h1>注册成功！请关闭页面</h1>
    </div>
</div>

        <%
    }else{
        %>

<div class="text-center">
    <div class="page-header">
        <h1>注册失败！该用户名已经被使用</h1>
    </div>
</div>

        <%
    }
%>
</body>
</html>
