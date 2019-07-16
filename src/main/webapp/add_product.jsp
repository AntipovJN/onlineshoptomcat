<%--
  Created by IntelliJ IDEA.
  User: Eugene
  Date: 16.07.2019
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new product</title>
</head>
<body>

<center>

    <h4>
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
                response.getWriter().write(error);
            }
        %>
    </h4>

    <form action="add_product" method="post">
        Email <input name="name" type="text"/> <br>
        Password <input name="description" type="text"> <br>
        Repeat password <input name="price" type="number"> <br>
        <button type="submit">Register</button>
    </form>
</center>
</body>
</html>
