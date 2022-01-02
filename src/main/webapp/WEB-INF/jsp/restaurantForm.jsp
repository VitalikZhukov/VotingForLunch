<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <jsp:useBean id="restaurant" type="ru.vote.model.Restaurant" scope="request"/>

    <h3><spring:message code="${restaurant.isNew() ? 'restaurant.add' : 'restaurant.edit'}"/></h3>

    <form method="post" action="restaurants">
        <input type="hidden" name="id" value="${restaurant.id}">
        <dl>
            <dt><spring:message code="restaurant.name"/>:</dt>
            <dd><input type="text" value="${restaurant.name}" name="name" required></dd>
        </dl>
        <br>
        <dl>
            <dt><spring:message code="restaurant.dish1"/>:</dt>
            <dd><input type="text" value="${restaurant.menu}" name="dish1" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="restaurant.price1"/>:</dt>
            <dd><input type="text" value="${restaurant.price}" name="price1" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="restaurant.dish2"/>:</dt>
            <dd><input type="text" value="${restaurant.menu}" name="dish2" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="restaurant.price2"/>:</dt>
            <dd><input type="text" value="${restaurant.price}" name="price2" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="restaurant.dish3"/>:</dt>
            <dd><input type="text" value="${restaurant.menu}" name="dish3" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="restaurant.price3"/>:</dt>
            <dd><input type="text" value="${restaurant.price}" name="price3" required></dd>
        </dl>
        <dl>
            <button type="submit"><spring:message code="common.save"/></button>
            <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
        </dl>
    </form>
</section>
</body>
</html>
