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
    <title>网站首页</title>
</head>
<body>

<%!
    public void jspInit() {

    }

    public void jspDestroy() {

    }
%>

<%
    boolean loginSuccess = (boolean)request.getAttribute("loginSuccess");
    String userName = (String)request.getAttribute("userName");
    String contributionName[] = (String[])request.getAttribute("contributionName");
    String contributionOwner[] = (String[])request.getAttribute("contributionOwner");
    String videoID[] = (String[])request.getAttribute("videoID");

    if(loginSuccess){
%>

<p>欢迎用户<%=userName%></p><br>

<%
    }else{
%>

<a href="login.jsp">请登录</a>

<%
    }
%>

<a href="upload.jsp">投稿</a>

<%
    for(int i=0;i<8;i++){
%>

<P>
    <a href="play?videoID=<%=videoID[i]%>"><%=contributionName[i]%></a>
    <%=contributionOwner[i]%>
</P>

<%
    }
%>

</body>
</html>
