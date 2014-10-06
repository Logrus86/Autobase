<%@tag description="search result form" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty foundedList}">
    <div align="center" class="msg-error">
        <fmt:message key="default.nothing-found"/>
    </div>
</c:if>

<div>
    <table class="table table-bordered">
        <thead>
        <tr class="success">
            <th>#</th>
            <th><fmt:message key="default.model"/></th>
            <th><fmt:message key="default.manufacturer"/></th>
            <th><fmt:message key="default.year_prod"/></th>
            <th><fmt:message key="default.color"/></th>
            <th><fmt:message key="default.fuel"/></th>
            <th><fmt:message key="default.mileage"/>, <fmt:message key="default.kilometers"/></th>
            <th><fmt:message key="default.rentPrice"/>, <fmt:message key="default.currency"/> <fmt:message
                    key="default.perDay"/></th>
            <th><fmt:message key="default.driver"/></th>
            <c:choose>
                <c:when test="${foundedList[0].type == vehicleTypes[0]}">
                    <th><fmt:message key="default.doorsN"/></th>
                    <th><fmt:message key="default.passN"/></th>
                    <th><fmt:message key="default.standN"/></th>
                </c:when>
                <c:when test="${foundedList[0].type == vehicleTypes[1]}">
                    <th><fmt:message key="default.passN"/></th>
                    <th><fmt:message key="default.doorsN"/></th>
                    <th><fmt:message key="default.conditioner"/></th>
                </c:when>
                <c:when test="${foundedList[0].type == vehicleTypes[2]}">
                    <th><fmt:message key="default.maxPayload"/></th>
                    <th><fmt:message key="default.enclosed"/></th>
                    <th><fmt:message key="default.tipper"/></th>
                </c:when>
            </c:choose>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${foundedList}" var="vehicle" varStatus="i">
            <tr>
                <td>${i.count}</td>
                <td>${vehicle.model.value}</td>
                <td>${vehicle.manufacturer.value}</td>
                <td>${vehicle.productionYear}</td>
                <td><c:if test="${locale.language=='ru'}">${vehicle.color.valueRu}</c:if>
                    <c:if test="${locale.language=='en'}">${vehicle.color.valueEn}</c:if>
                </td>
                <td><fmt:message key="default.${vehicle.fuelType}"/></td>
                <td>${vehicle.mileage}</td>
                <td>${vehicle.rentPrice}</td>
                <td>${vehicle.driver.lastname} ${vehicle.driver.firstname}</td>
                <c:choose>
                    <c:when test="${foundedList[0].type == vehicleTypes[0]}">
                        <td>${vehicle.doorsNumber}</td>
                        <td>${vehicle.passengerSeatsNumber}</td>
                        <td>${vehicle.standingPlacesNumber}</td>
                    </c:when>
                    <c:when test="${foundedList[0].type == vehicleTypes[1]}">
                        <td>${vehicle.doorsNumber}</td>
                        <td>${vehicle.passengerSeatsNumber}</td>
                        <td>
                            <c:choose>
                                <c:when test="${vehicle.withConditioner}">
                                    <fmt:message key="default.yes"/>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="default.no"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </c:when>
                    <c:when test="${foundedList[0].type == vehicleTypes[2]}">
                        <td>${vehicle.maxPayload}</td>
                        <td>
                            <c:choose>
                                <c:when test="${vehicle.enclosed}">
                                    <fmt:message key="default.yes"/>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="default.no"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${vehicle.tipper}">
                                    <fmt:message key="default.yes"/>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="default.no"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </c:when>
                </c:choose>
                <td>
                    <button class="btn btn-info" data-toggle="modal"
                            <c:choose>
                                <c:when test="${user != null}">
                                    data-target="#modalOrderForm"
                                    onclick="orderDataToModalForm(${vehicle.id},${vehicle.rentPrice},${user.balance})"
                                </c:when>
                                <c:otherwise>
                                    data-target="#modalRegForm"
                                </c:otherwise>
                            </c:choose>>
                        <fmt:message key="default.doorder"/>
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="modal fade" id="modalOrderForm" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" align="center"><fmt:message key="default.order"/></h4>
            </div>
            <div class="msg-error" id="msg_o">${order_err}</div>
            <div class="modal-body">
                <form id="order-form" method="post" action="order">
                    <input id="vhId" name="vhId" type="hidden" value="">
                    <div class="input-group">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.your_balance"/></span>
                                <input type="text" class="form-control" value="${user.balance}" readonly>
                                <span class="input-group-addon"><fmt:message key="default.currency"/></span>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.rentPrice"/>
                                    <fmt:message key="default.perDay"/></span>
                                <input type="text" id="vhRent" class="form-control" value="" readonly>
                                <span class="input-group-addon"><fmt:message key="default.currency"/></span>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="order.dateStart"/></span>
                                <input type="date" id="dateStart" name="dateStart" class="form-control"
                                       min='<fmt:formatDate pattern="yyyy-MM-dd" value='${nowdate}'/>' max="2014-12-31"
                                       value='<fmt:formatDate pattern="yyyy-MM-dd" value='${nowdate}'/>'>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.dayCount"/></span>
                                <input type="number" id="dayCount" min="1" class="form-control" name="dayCount"
                                       required>
                            </div>
                        </div>
                        <div class="col-lg-12" align="center">
                            <button type="submit" class="btn btn-primary"><fmt:message key="default.doorder"/></button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message
                        key="default.cancel"/></button>
            </div>
        </div>
    </div>
</div>

