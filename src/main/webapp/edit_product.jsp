<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: Eugene
  Date: 17.07.2019
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit product</title>
</head>
<body>
<center>
    <h4>
        <%=!Objects.isNull(request.getAttribute("error")) ? request.getAttribute("error") : ""%>
    </h4>
    <form action="/products/edit" method="post">
        <input type="hidden" name="id" value="<%=request.getAttribute("id")%>">
        Name <input name="name" type="text" value="<%=request.getAttribute("name")%>"/> <br>
        Description <input name="description" type="text" value="<%=request.getAttribute("description")%>"> <br>
        Price <input name="price" type="number" value="<%=request.getAttribute("price")%>"> <br>
        <button type="submit">Register</button>
    </form>
</center>
</body>
</html>
