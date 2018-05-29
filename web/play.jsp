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
</head>
<body>
<%
    if(request.getAttribute("success").equals("true")){
%>
<h1><%=request.getAttribute("contributionName")%></h1>
<h2><%=request.getAttribute("contributionOwner")%></h2>
<video controls="controls" height="540" width="960">
    <source src="<%=request.getAttribute("path")%>" type="video/mp4" >
</video>
<%
    }else{
        out.print(request.getAttribute("errorMessage"));
    }
%>
</body>
</html>
