<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="ru">
<head>
    <title>Restaurants</title>
</head>
<body>
<section>
    <h3><a href="index.jsp">Home</a></h3>
    <hr/>
    <h2>Restaurants</h2>
    <a href="restaurants?action=create">Add restaurant</a>
    <br><br>

    <h4>Your choose: ${choose}</h4>
    <br>
    <form method="get" action="restaurants">

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

            <c:forEach var="restaurant" items="${restaurants}">

                <jsp:useBean id="restaurant" type="ru.vote.model.Restaurant"/>

                <tr style="color: brown" align="center">
                <td rowspan="4"><a href="restaurants?action=update&id=${restaurant.id}">Update</a></td>
                <td rowspan="4"><a href="restaurants?action=delete&id=${restaurant.id}">Delete</a></td>

                <td rowspan="4">
                    <label>
                        <input type="radio" name="vote" value="${restaurant.id}"/>
                    </label>
                </td>

                <td rowspan="4">${restaurant.name}</td>

                <td rowspan="4">${restaurant.voteCounter}</td>

                <c:forEach var="menu" items="${menu}">
                    <jsp:useBean id="menu" type="ru.vote.model.Menu"/>
                    <c:if test="${menu.restaurantId == restaurant.id}">
                    <tr style="color: brown" align="center">
                        <td>${menu.dish}</td>
                        <td>${menu.price}</td>
                    </tr>
                    </c:if>
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