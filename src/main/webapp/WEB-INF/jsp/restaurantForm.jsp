<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Restaurant form</title>
    <style>
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }

        dt {
            display: inline-block;
            width: 170px;
        }

        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.jsp">Home</a></h3>
    <hr>
    <h2>${param.action == 'create' ? 'Create restaurant' : 'Edit restaurant'}</h2>

    <jsp:useBean id="restaurant" type="ru.vote.model.Restaurant" scope="request"/>

    <form method="post" action="restaurants">
        <input type="hidden" name="id" value="${restaurant.id}">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" value="${restaurant.name}" name="name" required></dd>
        </dl>
        <br>
        <dl>
            <dt>Dish #1:</dt>
            <dd><input type="text" value="${restaurant.menu}" name="dish1" required></dd>
        </dl>
        <dl>
            <dt>Price #1:</dt>
            <dd><input type="text" value="${restaurant.price}" name="price1" required></dd>
        </dl>
        <dl>
            <dt>Dish #2:</dt>
            <dd><input type="text" value="${restaurant.menu}" name="dish2" required></dd>
        </dl>
        <dl>
            <dt>Price #2:</dt>
            <dd><input type="text" value="${restaurant.price}" name="price2" required></dd>
        </dl>
        <dl>
            <dt>Dish #3:</dt>
            <dd><input type="text" value="${restaurant.menu}" name="dish3" required></dd>
        </dl>
        <dl>
            <dt>Price #3:</dt>
            <dd><input type="text" value="${restaurant.price}" name="price3" required></dd>
        </dl>
        <dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
        </dl>
    </form>
</section>
</body>
</html>
