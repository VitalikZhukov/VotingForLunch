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
        <th>Menu</th>
        <th>Price</th>
        <th>Num(vote)</th>
    </tr>
    </thead>
    <colgroup style="background-color: #ddd;">
        <col>
        <col style="background-color: #ddd;">
        <col>
    </colgroup>
    <tbody>
    <jsp:useBean id="restaurantList" scope="request" type="java.util.List"/>
<%--    <jsp:useBean id="menuMap" type="java.util.Map"/>--%>

<%--    сделать обход мапы--%>

    <c:forEach var="rest" items="${restaurantList}">

        <jsp:useBean id="rest" type="ru.vote.model.Restaurant"/>

        <tr style="color: green">
            <td>${rest.name}</td>
<%--            <c:forEach var="entry" items="${menuMap}">--%>
<%--                <td>${entry.key}</td>--%>
<%--                <td>${entry.value}</td>--%>
<%--            </c:forEach>--%>
            <td>${rest.voteCount}</td>
        </tr>
    </c:forEach>
    </tbody>

</table>

</body>
</html>