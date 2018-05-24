<%--
  Created by IntelliJ IDEA.
  User: Alell
  Date: 2018.05.23
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>

<%
    //这里需要检查当前用户是否已经登录，如果登录，询问用户是否退出当前账户。
    //不用，因为登录过后的index.jsp必定是隐藏了登录按钮的。
%>


<form action="login.do" method="POST">
    用户名：<input type="text" name="userName"/><br>
    密码：<input type="password" name="password"/><br>
    <input type="submit" value="登录"/>
</form>
</body>
</html>
