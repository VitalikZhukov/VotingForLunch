<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="ru">
<head>
    <title>Restaurants</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Restaurants</h2>

<table border="2">
    <caption>Restaurant list.</caption>
    <thead>
    <tr>
        <th>Name</th>
        <th>Num(vote)</th>
        <th>Menu</th>
        <th>Price</th>
    </tr>
    </thead>
    <colgroup style="background-color: #ddd;">
        <col>
        <col style="background-color: #ddd;">
        <col>
    </colgroup>
    <tbody>
    <jsp:useBean id="restaurantList" scope="request" type="java.util.List"/>

    <c:forEach var="rest" items="${restaurantList}">

        <jsp:useBean id="rest" type="ru.vote.model.Restaurant"/>

        <tr style="color: green" align="center">
            <td rowspan="${rest.menu.size() + 1}">${rest.name}</td>

            <td rowspan="${rest.menu.size() + 1}">${rest.voteCount}</td>

            <c:forEach var="entry" items="${rest.menu}">
                <tr style="color: brown" align="center">
                <td>${entry.key}</td>
                <td>${entry.value}</td>
                </tr>
            </c:forEach>

        </tr>
    </c:forEach>
    </tbody>

</table>

</body>
</html>