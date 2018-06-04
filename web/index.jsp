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
    <title>首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" title="login">
    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>

    <style>
        .panel{
            margin: 30px;
        }
        #contributionName{
            font-family: Consolas;
            font-size: large;
        }
        #contributionOwner{
            font-family: Consolas;
            font-size: medium;
        }
    </style>
</head>
<body>

<%
    boolean loginSuccess = (boolean) request.getAttribute("loginSuccess");
    String userName = (String) request.getAttribute("userName");
    String contributionName[] = (String[]) request.getAttribute("contributionName");
    String contributionOwner[] = (String[]) request.getAttribute("contributionOwner");
    String videoID[] = (String[]) request.getAttribute("videoID");
%>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/index">视频网站</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <% if(loginSuccess){ %>
                <li class="active"><a href="/space?userName=<%=userName%>"><%=userName%>的个人空间</a></li>
                <% }else{ %>
                <li class="active"><a href="/login.jsp">请登录</a></li>
                <% } %>

                <li><a href="upload.jsp">投稿</a></li>

                <% if(loginSuccess){ %>
                <li><a href="/logout">注销</a></li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>

<div class="row">
    <% for (int i = 0; i < 8; i++) { %>
        <div class="col-sm-4">
            <div class="panel panel-default">
                <div class="panel-body">
                    <a href="play?videoID=<%=videoID[i]%>">
                        <img src="image/null.png">
                    </a>
                    <br>
                    <div id="contributionName"><%=contributionName[i]%></div>
                    <div id="contributionOwner">up主：<%=contributionOwner[i]%></div>
                </div>
            </div>
        </div>
    <% } %>
</div>

</body>
</html>
