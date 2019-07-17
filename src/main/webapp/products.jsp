<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %><%--
  Created by IntelliJ IDEA.
  User: Eugene
  Date: 16.07.2019
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All products</title>
</head>
<body>
<%
    PrintWriter printWriter = response.getWriter();
    printWriter.write("<center>");
    printWriter.write("<h2> Список товаров </h2>");
    printWriter.write("<table border=\"1\">\n" +
            "    <tr>\n" +
            "        <th>Наименование</th>\n" +
            "        <th>Описание</th>\n" +
            "        <th>Цена</th>\n" +
            "        <th>Edit</th>" +
            "        <th>Remove</th>" +
            "    </tr>");

    List<Product> allUsers = (List<Product>) request.getAttribute("allProducts");
    for (Product product : allUsers) {
        printWriter.write("<tr>");
        printWriter.write("<td>" + product.getName() + "</td>");
        printWriter.write("<td>" + product.getDescription() + "</td>");
        printWriter.write("<td>" + product.getPrice() + "</td>");
        printWriter.write("<td> <a href='/products/edit?id=" + product.getId() + "'>");
        printWriter.write("Edit </a></td>");
        printWriter.write("<td> <a href='/products/remove?id=" + product.getId() + "'>");
        printWriter.write("Remove </a></td>");
        printWriter.write("</tr>");
    }
    printWriter.write("<a href=\"/users\">\n" +
            "    <button>Все пользователи</button>\n" +
            "</a>\n" +
            "<a href=\"/products/add\">\n" +
            "    <button>Добавить товар</button>\n" +
            "</a>");
    printWriter.write("</center>");

%>
</table>
</body>
</html>
