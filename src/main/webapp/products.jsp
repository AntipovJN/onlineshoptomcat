<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All products</title>
</head>
<body>
<center>
    <h2> Список товаров </h2>
    <table border="1">
        <tr>
            <th>Наименование</th>
            <th>Описание</th>
            <th>Цена</th>
            <th>Edit</th>
            <th>Remove</th>
        </tr>
        <c:forEach items="${products}" var="product">
        <tr>
            <td>${product.name}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
            <td><a href='/products/edit?id=${product.id}'>Edit </a></td>
            <td><a href='/products/remove?id=${product.id}'>Remove </a></td>
        </tr>
        </c:forEach>
        <a href="/users">
            <button>Все пользователи</button>
        </a>
        <a href="/products/add">
            <button>Добавить товар</button>
        </a>
</center>
</table>
</body>
</html>
