<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="ru">
<head>
    <title>Restaurants</title>
</head>
<body>
<section>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Restaurants</h2>
<a href="restaurants?action=create">Add restaurant</a>
<br><br>

    <h4>Your choose: Display currant choose</h4>
    <br>
    <form method="post" action="restaurants">

<table border="1" cellpadding="8" cellspacing="0">
    <caption>Restaurant list.</caption>
    <thead>
    <tr style="background-color: darkseagreen">
        <th>Update</th>
        <th>Delete</th>
        <th>Vote</th>
        <th>Name</th>
        <th>Num(vote)</th>
        <th>Menu</th>
        <th>Price</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="rest" items="${restaurants}">

        <jsp:useBean id="rest" type="ru.vote.model.Restaurant"/>

        <tr style="color: brown" align="center">
            <td rowspan="${rest.menuMap.size() + 1}"><a href="restaurants?action=update&id=${rest.id}">Update</a></td>
            <td rowspan="${rest.menuMap.size() + 1}"><a href="restaurants?action=delete&id=${rest.id}">Delete</a></td>

            <td rowspan="${rest.menuMap.size() + 1}">
                <label>
                    <input type="radio" name="vote" value="${rest.name}"/>
                </label>
            </td>

            <td rowspan="${rest.menuMap.size() + 1}">${rest.name}</td>

            <td rowspan="${rest.menuMap.size() + 1}">${rest.voteCount}</td>

            <c:forEach var="entry" items="${rest.menuMap}">
                <tr style="color: brown" align="center">
                <td>${entry.key}</td>
                <td>${entry.value}</td>
                </tr>
            </c:forEach>
        </tr>
    </c:forEach>
    </tbody>

    </table>
    <br>
    <button type="submit">Vote</button>
    </form>
</section>
</body>
</html>