<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: Alell
  Date: 2018.05.30
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String userName = (String)request.getAttribute("userName");

    //投稿信息
    int contributionCount = (int)request.getAttribute("contributionCount");
    String[] contributionName = (String[])request.getAttribute("contributionName");
    String[] videoID = (String[])request.getAttribute("videoID");

    //收藏信息
    int collectCount = (int)request.getAttribute("collectCount");
    String[] collectVideoID = (String[])request.getAttribute("collectVideoID");
    String[] collectContributionName = (String[])request.getAttribute(("collectContributionName"));
%>

<html>
<head>
    <title><%=userName%>的个人空间</title>

    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>

    <style>
        #contributionName{
            font-family: Consolas;
            font-size: large;
        }
        #collectContributionName{
            font-family: Consolas;
            font-size: large;
        }
    </style>
</head>
<body>

<%
    String loginUserName = (String)request.getAttribute("loginUserName");
    boolean loginSuccess = (boolean)request.getAttribute("loginSuccess");
%>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/index">视频网站</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <% if(loginSuccess){ %>
                <li class="active"><a href="/space?userName=<%=loginUserName%>"><%=loginUserName%>的个人空间</a></li>
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

<div class="panel panel-body">
    <div class="text-center">
        <div class="page-header">
            <h1><%=userName%>投稿的视频</h1>
        </div>
    </div>

    <%
        if(contributionCount > 0){
            for(int i=0; i<contributionCount; i++){
    %>

    <div class="col-sm-4">
        <div class="panel panel-default">
            <div class="panel-body">
                <a target="_blank" href="/play?videoID=<%=videoID[i]%>">
                    <img src="image/null.png">
                </a>
                <br>
                <div id="contributionName"><%=contributionName[i]%></div>
            </div>
        </div>
    </div>

    <%
        }
    }else{
    %>

    <div class="text-center">
        <div class="page-header">
            <h3><%=userName%>该用户没有投稿视频</h3>
        </div>
    </div>

    <%
        }
    %>
</div>

<div class="panel-body panel">
    <div class="text-center">
        <div class="page-header">
            <h1><%=userName%>收藏的视频</h1>
        </div>
    </div>

    <%
        for(int i=0; i<collectCount; i++){
    %>

    <div class="col-sm-4">
        <div class="panel panel-default">
            <div class="panel-body">
                <a target="_blank" href="/play?videoID=<%=collectVideoID[i]%>">
                    <img src="image/null.png">
                </a>
                <br>
                <div id="collectContributionName"><%=collectContributionName[i]%></div>
            </div>
        </div>
    </div>

    <%
        }
    %>
</div>

</body>
</html>
