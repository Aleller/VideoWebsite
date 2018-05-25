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
</head>
<body>
<%
    boolean successful = (boolean)(request.getAttribute("successful"));
    if(successful){
        %>
            <h1>注册成功！请关闭页面</h1>
        <%
    }else{
        %>
            <h1>注册失败！该用户名已经被使用</h1>
        <%
    }
%>
</body>
</html>
