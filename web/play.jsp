<%--
  Created by IntelliJ IDEA.
  User: Alell
  Date: 2018.05.29
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%=request.getAttribute("contributionName")%></title>

    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>

    <style>
        #video{
            margin: 0px 200px 10px 200px;
        }
        #collectButton{
            margin: 20px 0px 10px 653px;
        }
    </style>
</head>
<body>

<%
    boolean loginSuccess = (boolean)request.getAttribute("loginSuccess");
    String userName = (String)request.getAttribute("userName");
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

<%
    String errorMessage = (String)request.getAttribute("errorMessage");
    if(request.getAttribute("success").equals("true")){
%>

<div class="text-center">
    <div class="page-header">
        <h1><%=request.getAttribute("contributionName")%></h1>
        <br>
        <h3>up主：<%=request.getAttribute("contributionOwner")%></h3>
    </div>
</div>

<video id="video" controls="controls" height="540" width="960">
    <source src="<%=request.getAttribute("path")%>" type="video/mp4" >
</video>

<form role="form" action="collect" method="GET">
    <div class="input-group">
        <input type="hidden" name="videoID_str" value="<%=(String)request.getAttribute("videoID_str")%>"/>
        <button id="collectButton" type="submit" class="btn btn-info">收藏</button>
    </div>
</form>

<% }else{ %>

<div class="text-center">
    <div class="page-header">
        <h2><%=errorMessage%></h2>
    </div>
</div>

<% } %>

</body>
</html>
