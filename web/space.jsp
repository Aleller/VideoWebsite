<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: Alell
  Date: 2018.05.30
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    int contributionCount = (int)request.getAttribute("contributionCount");
    String contributionName[] = (String[])request.getAttribute("contributionName");
    String videoID[] = (String[])request.getAttribute("videoID");
    String userName = (String)request.getAttribute("userName");
%>

<html>
<head>
    <title><%=userName%>的个人空间</title>
</head>
<body>
<h1><%=userName%>投稿的视频</h1>
<%
    if(contributionCount > 0){
        for(int i=0;i<contributionCount;i++){
%>

<p>
    <a href="/play?videoID=<%=videoID[i]%>"><%=contributionName[i]%></a>
</p>

<%
        }
    }else{
%>
<p>该用户没有投稿视频</p>
<%
    }
%>
</body>
</html>
