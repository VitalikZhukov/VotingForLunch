<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/vote.common.js" defer></script>
<script type="text/javascript" src="resources/js/vote.restaurants.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
    <h3 class="text-center"><spring:message code="restaurant.title"/></h3>
        <br>
    <h4><spring:message code="restaurant.choose"/> </h4>
        <br>
        <button class="btn btn-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            <spring:message code="common.add"/>
        </button>
        <br>

        <form>
        <table class="table table-bordered" id="datatable">
            <thead>
            <tr>
                <th></th>
                <th></th>
                <th><spring:message code="restaurant.vote"/></th>
                <th><spring:message code="restaurant.name"/></th>
                <th><spring:message code="restaurant.voteCounter"/></th>
                <th><spring:message code="restaurant.menu"/></th>
                <th><spring:message code="restaurant.price"/></th>
            </tr>
            </thead>

            <c:forEach var="restaurant" items="${requestScope.restaurants}">
                <jsp:useBean id="restaurant" type="ru.vote.model.Restaurant"/>
                <tr>
                    <td rowspan="3"><a><span class="fa fa-pencil"></span></a></td>
                    <td rowspan="3"><a onclick="deleteRow(${restaurant.id})"><span class="fa fa-remove"></span></a></td>
                    <td rowspan="3"><label><input type="radio" name="vote" value="${restaurant.id}"/></label></td>
                    <td rowspan="3">${restaurant.name}</td>
                    <td rowspan="3">${restaurant.voteCounter}</td>
                    <c:forEach var="menu" items="${requestScope.menus}">
                        <jsp:useBean id="menu" type="ru.vote.model.Menu"/>
                        <c:if test="${menu.restaurantId == restaurant.id}">
                                <td>${menu.dish}</td>
                                <td>${menu.price}</td>
                            </tr>
                        </c:if>
                    </c:forEach>--%>
                </tr>
            </c:forEach>
        </table>
        <br>
        <button type="submit"><spring:message code="restaurant.vote"/></button>
        </form>
    </div>
</div>


<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalTitle"><spring:message code="restaurant.add"/></h4>
                <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="col-form-label"><spring:message code="restaurant.name"/></label>
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="<spring:message code="restaurant.name"/>">
                    </div>

                    <div class="form-group">
                        <label for="dish1" class="col-form-label"><spring:message code="restaurant.dish1"/></label>
                        <input type="text" class="form-control" id="dish1" name="dish1"
                               placeholder="<spring:message code="restaurant.dish1"/>">
                    </div>

                    <div class="form-group">
                        <label for="price1" class="col-form-label"><spring:message code="restaurant.price1"/></label>
                        <input type="text" class="form-control" id="price1" name="price1"
                               placeholder="<spring:message code="restaurant.price1"/>">
                    </div>

                    <div class="form-group">
                        <label for="dish2" class="col-form-label"><spring:message code="restaurant.dish2"/></label>
                        <input type="text" class="form-control" id="dish2" name="dish2"
                               placeholder="<spring:message code="restaurant.dish2"/>">
                    </div>

                    <div class="form-group">
                        <label for="price2" class="col-form-label"><spring:message code="restaurant.price2"/></label>
                        <input type="text" class="form-control" id="price2" name="price2"
                               placeholder="<spring:message code="restaurant.price2"/>">
                    </div>

                    <div class="form-group">
                        <label for="dish3" class="col-form-label"><spring:message code="restaurant.dish3"/></label>
                        <input type="text" class="form-control" id="dish3" name="dish3"
                               placeholder="<spring:message code="restaurant.dish3"/>">
                    </div>

                    <div class="form-group">
                        <label for="price3" class="col-form-label"><spring:message code="restaurant.price3"/></label>
                        <input type="text" class="form-control" id="price3" name="price3"
                               placeholder="<spring:message code="restaurant.price3"/>">
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
</html>