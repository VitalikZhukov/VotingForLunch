<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages.app"/>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<br>
<section>
    <form method="post" action="users">
        <fmt:message key="app.login"/>: <select name="userId">
        <option value="10000" selected>User1</option>
        <option value="10001" selected>User2</option>
        <option value="10002">Admin</option>
    </select>
        <button type="submit"><fmt:message key="common.select"/></button>
    </form>
</section>

</body>
</html>