<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
        <h4><spring:message code="restaurant.choose"/> <span style="white-space: nowrap" id="restaurantName"></span></h4>
        <br>

        <script type="text/javascript">
            function setEditAccessRestaurant() {
                return false;
            }
        </script>

        <sec:authorize access="hasRole('ADMIN')">
            <button class="btn btn-primary" onclick="add()">
                <span class="fa fa-plus"></span>
                <spring:message code="common.add"/>
            </button>
            <script type="text/javascript">
                function setEditAccessRestaurant() {
                    return true;
                }
            </script>
        </sec:authorize>

        <br>

        <table class="table table-bordered" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="restaurant.vote"/></th>
                <th><spring:message code="restaurant.name"/></th>
                <th><spring:message code="restaurant.voteCounter"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
        </table>

        <br>
        <button class="btn btn-primary" onclick="voting()">
            <spring:message code="restaurant.vote"/>
        </button>
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
                        <label for="name" class="col-form-label"><spring:message code="restaurant.name"/></label>
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="<spring:message code="restaurant.name"/>">
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
    <jsp:param name="page" value="restaurant"/>
</jsp:include>
</html>