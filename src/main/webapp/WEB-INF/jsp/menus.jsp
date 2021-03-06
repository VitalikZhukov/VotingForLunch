<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/vote.common.js" defer></script>
<script type="text/javascript" src="resources/js/vote.menus.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center"><spring:message code="menu.title"/></h3>
        <br>

        <script type="text/javascript">
            function setEditAccessUser() {
                return false;
            }
        </script>

        <sec:authorize access="hasRole('ADMIN')">
            <button class="btn btn-primary" onclick="add()">
                <span class="fa fa-plus"></span>
                <spring:message code="common.add"/>
            </button>
            <script type="text/javascript">
                function setEditAccessUser() {
                    return true;
                }
            </script>
        </sec:authorize>

        <br>
        <table class="table table-bordered" id="datatable">
            <thead>
            <tr>
<%--                <th>№</th>--%>
                <th><spring:message code="menu.dish"/></th>
                <th><spring:message code="menu.price"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>

<%--            <c:forEach var="menu" items="${requestScope.menus}">
                <jsp:useBean id="menu" type="ru.vote.model.Menu"/>
                <c:if test="${menu.restaurantId == param.restaurantId}">
                    <tr>
                        <td></td>
                        <td>${menu.dish}</td>
                        <td>${menu.price}</td>
                        <td><a><span class="fa fa-pencil"></span></a></td>
                        <td><a onclick="deleteRow(${menu.id})"><span class="fa fa-remove"></span></a></td>
                    </tr>
                </c:if>
            </c:forEach>--%>

        </table>
    </div>
</div>


<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalTitle"></h4>
                <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="dish" class="col-form-label"><spring:message code="menu.dish"/></label>
                        <input type="text" class="form-control" id="dish" name="dish"
                               placeholder="<spring:message code="menu.dish"/>">
                    </div>

                    <div class="form-group">
                        <label for="price" class="col-form-label"><spring:message code="menu.price"/></label>
                        <input type="text" class="form-control" id="price" name="price"
                               placeholder="<spring:message code="menu.price"/>">
                    </div>

                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>
                    <spring:message code="common.save"/>
                </button>
            </div>

        </div>
    </div>
</div>


</body>
<jsp:include page="fragments/i18n.jsp">
    <jsp:param name="page" value="menu"/>
</jsp:include>
</html>
