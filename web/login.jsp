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
    //不检查当前用户是否已经登录，如果登录，询问用户是否退出当前账户。
    //因为登录过后的index.jsp必定是隐藏了登录按钮的。
%>

<p>登录</p>
<form action="login.do" method="POST">
    用户名：<input type="text" name="userName"/><br>
    密码：<input type="password" name="password"/><br>
    <input type="submit" value="登录"/>
</form>

<p>注册</p>
<form action="register.do" method="POST">
    用户名：<input type="text" name="userName"/><br>
    密码：<input type="password" name="password_1"/><br>
    重复密码：<input type="password" name="password_2"/><br>
    <input type="submit" value="注册"/>
</form>
</body>
</html>
