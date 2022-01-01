<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="ru">

<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <h3><spring:message code="restaurant.title"></h3>

    <br><br>
    <h4>Your choose: </h4>
    <br><br>

    <a href="restaurants/create"><spring:message code="restaurant.add"/></a>
    <br><br>

        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
<%--                <th><spring:message code="restaurant.vote"/></th>--%>
                <th><spring:message code="restaurant.voteCounter"/></th>
                <th><spring:message code="restaurant.name"/></th>
                <th><spring:message code="restaurant.menu"/></th>
                <th><spring:message code="restaurant.price"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>

            <c:forEach var="restaurant" items="${requestScope.restaurants}">
                <tr data-meal-excess="${restaurant.excess}">
                    <td></td>
                </tr>
            </c:forEach>

        </table>
        <br>
        <button type="submit">Vote</button>
</section>
</body>
</html>


<%--
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
    </c:forEach>--%>