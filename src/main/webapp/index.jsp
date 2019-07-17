<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: anastasiavaskina
  Date: 2019-07-03
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello!</title>
</head>
<body>

<%--/register - это путь относительно домена - т.е. домен + эта строка--%>
<%--register - это путь относительного того где мы сейчас - т.е. где мы сейчас + эта строка--%>

<center>
Welcome! You must sign in :p
    <% if (!Objects.isNull(request.getAttribute("error"))) {
        response.getWriter().write("<center> Invalid email or password! Try again </center>");
    }%>
<form action="/login" method="post">
    <input type="email" name="email"/>
    <input type="password" name="password"/>
    <input type="submit" value="Sign In">
</form>
</center>

</body>
</html>
