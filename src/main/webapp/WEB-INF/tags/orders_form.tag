<%--suppress ALL --%>
<%@tag description="users edit forms admin" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <c:if test="${user.role == 'CLIENT'}">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title"><fmt:message key="default.yours"/> <fmt:message key="default.orders"/></h3>
            </div>
            <div class="panel-body">
    </c:if>
    <table class="table table-bordered">
        <thead>
            <tr class="success">
                <th>#</th>
                <c:if test="${user.role == 'ADMIN'}">
                    <th><fmt:message key="order.client"/></th>
                </c:if>
                <th><fmt:message key="order.vehicle"/></th>
                <th><fmt:message key="order.sum"/></th>
                <th><fmt:message key="order.dateOrdered"/></th>
                <th><fmt:message key="order.dateStart"/></th>
                <th><fmt:message key="order.dateEnd"/></th>
                <th><fmt:message key="order.length"/></th>
                <th><fmt:message key="order.status"/></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${user.role=='CLIENT' ? user.clientOrders : ordersList}" var="order" varStatus="i">
                <tr>
                    <td>${order.id}</td>
                    <c:if test="${user.role == 'ADMIN'}">
                        <td>${order.client.lastname} ${order.client.firstname}</td>
                    </c:if>
                    <td>${order.vehicle.manufacturer.value} ${order.vehicle.model.value}</td>
                    <td>${order.sum} <fmt:message key="default.currency"/></td>
                    <td>${order.dateOrdered}</td>
                    <td>${order.dateStart}</td>
                    <td>${order.dateEndString}</td>
                    <td>${order.dayCount} <fmt:message key="order.days"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${user.role == 'CLIENT'}">
                                <fmt:message key="order.${order.status}"/>
                            </c:when>
                            <c:when test="${user.role == 'ADMIN'}">
                                <form method="post" action="order_edit">
                                    <div class="input-group">
                                        <input hidden name="orderId" value="${order.id}">
                                        <select class="selectpicker show-menu-arrow" data-width="auto" name="status" onchange="submit()">
                                            <c:forEach items="${statuses}" var="status">
                                                <option value="${status}" <c:if test="${order.status==status}">selected</c:if>>
                                                    <fmt:message key="order.${status}"/>
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </form>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:if test="${user.role == 'CLIENT'}">
            </div>
        </div>
    </c:if>
</div>