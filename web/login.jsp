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

    <meta name="viewport" content="width=device-width, initial-scale=1" title="login">
    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>

    <style type="text/css">
        #loginForm{
            margin: 10px 200px 10px 200px;
            padding: 10px 200px 10px 200px;
        }
        #registerForm{
            margin: 10px 200px 10px 200px;
            padding: 10px 200px 10px 200px;
        }
    </style>
</head>
<body>

<div class="text-center">
    <div class="page-header">
        <h1>登录</h1>
    </div>
</div>

<div class="panel panel-default" id="loginForm">
    <form role="form" action="login.do" method="POST">
        <div class="input-group">
            <span class="input-group-addon">用户名：</span>
            <input type="text" class="form-control" name="userName">
        </div>
        <br>
        <div class="input-group">
            <span class="input-group-addon">密码：</span>
            <input type="password" class="form-control" name="password">
        </div>
        <br>
        <div class="input-group">
            <button id="loginButton" type="submit" class="btn btn-default">登录</button>
        </div>
    </form>
</div>

<div class="panel panel-default" id="registerForm">
    <div class="text-center">
        <div class="page-header">
            <h2>还没有账号？马上注册</h2>
        </div>
    </div>

    <form role="form" action="register.do" method="POST">
        <div class="input-group">
            <span class="input-group-addon">用户名：</span>
            <input type="text" class="form-control" name="userName">
        </div>
        <br>
        <div class="input-group">
            <span class="input-group-addon">密码：</span>
            <input type="password" class="form-control" name="password_1">
        </div>
        <br>
        <div class="input-group">
            <span class="input-group-addon">重复密码：</span>
            <input type="password" class="form-control" name="password_2">
        </div>
        <br>
        <div class="input-group">
            <button id="registerButton" type="submit" class="btn btn-default">注册</button>
        </div>
    </form>
</div>


</body>
</html>
