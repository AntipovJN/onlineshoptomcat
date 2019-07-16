<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: anastasiavaskina
  Date: 2019-07-04
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All users</title>
</head>
<body>
    <%
        PrintWriter printWriter = response.getWriter();
        printWriter.write("<center>");
        printWriter.write("<h2> Список пользователей </h2>");
        printWriter.write("<table border=\"1\">\n" +
                "    <tr>\n" +
                "        <th>Email</th>\n" +
                "        <th>Password</th>\n" +
                "    </tr>");

        List<User> allUsers = (List<User>) request.getAttribute("allUsers");
        for (User user : allUsers) {
            printWriter.write("<tr>");
            printWriter.write("<td>" + user.getEmail() + "</td>");
            printWriter.write("<td>" + user.getPassword() + "</td>");
            printWriter.write("</tr>");
        }
        printWriter.write("<a href=\"/register\"><button>Register new user</button></a>\n" +
                "<a href=\"/products\"><button>All products</button></a>");
        printWriter.write("</center>");
    %>
</table>
</body>
</html>
