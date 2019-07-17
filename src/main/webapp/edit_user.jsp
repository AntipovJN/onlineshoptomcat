<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: Eugene
  Date: 17.07.2019
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
<%=!Objects.isNull(request.getAttribute("error"))  ? request.getAttribute("error") : ""%>
<form action="/users/edit" method="post">
    <input type="hidden" value="<%=request.getAttribute("id")%>" name="id">
    <input type="email" name="email" value="<%=request.getAttribute("email")%>"/>
    <input type="password" name="password"/>
    <input type="password" name="repeatPassword"/>
    <input type="submit" value="Edit">
</form>
</body>
</html>
