<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<script type='text/javascript' src='<c:url value="/webjars/jquery/1.11.1/jquery.js"/>'></script>
<script type='text/javascript' src='<c:url value="/webjars/bootstrap/3.2.0/js/bootstrap.js"/>'></script>
<script type='text/javascript' src='<c:url value="/static/js/bootstrap-select.js"/>'></script>
<link href='<c:url value="/static/css/style.css"/>' rel="stylesheet" media="screen">
<link href='<c:url value="/webjars/bootstrap/3.2.0/css/bootstrap.css"/>' rel="stylesheet" media="screen">
<link href='<c:url value="/static/css/bootstrap-select.css"/>' rel="stylesheet" media="screen">
<link href='<c:url value="/static/img/favicon.ico"/>' rel="icon" type="image/png">

<fmt:bundle basename="i18n.text">
    <head>
    <title>autobase.com</title>
    </head>
<body>
<div id="header">
    <div class="container">
        <a title="<fmt:message key="default.ordering"/>" href='<c:url value="/"/>'><img
                src='<c:url value="/static/img/logo.jpg"/>'/></a>

        <div class="btn-group" id="userbar" align="right">
            <p>
                <fmt:message key="default.welcome"/> ${user.username}! <fmt:message key="default.hey-admin"/>
                <button type="button" class="btn btn-default" onclick='location.href="/do/quit"'><fmt:message
                        key="default.doexit"/>
                </button>
            </p>
        </div>
    </div>
</div>
<div id="sub" class="lifestyle_img">
    <div class="content">
        <p id="count"><fmt:message key="default.vhcount"/></p>

        <div id="title">
            <h1 style="font-size: 45px;"><fmt:message key="default.bestprices"/></h1>
        </div>
        <div id="sub-title">
            <p id="h1sub" style="font-size: 16px;"><fmt:message key="default.service-desc"/></p>
        </div>
    </div>
</div>
<div class="btn-group" align="center">
    <a class="btn btn-default" href='<c:url value="/"/>'>Users</a>
    <a class="btn btn-default" href='<c:url value="/do/admin-vehicles"/>'>Vehicles</a>
    <a class="btn btn-default" href='<c:url value="/do/admin-colors"/>'>Colors</a>
    <a class="btn btn-default" href='<c:url value="/do/admin-models"/>'>Models</a>
    <a class="btn btn-default" href='<c:url value="/do/admin-manufacturers"/>'>Manufacturers</a>
</div>
<div id="vehicles">
    <table>
        <div class="msg-error">${vh_change_error}</div>
        <tr align="center">
            <td>№</td>
            <td>type</td>
            <td>operable</td>
            <td>model</td>
            <td>manufactor</td>
            <td>production year</td>
            <td>color</td>
            <td>fuelType</td>
            <td>mileage</td>
            <td>rent</td>
            <td>driverID</td>
            <td>seats</td>
            <td>doorsN</td>
            <td>standsN</td>
            <td>cond</td>
            <td>payload</td>
            <td>closed</td>
            <td>tipper</td>
        </tr>
        <c:forEach items="${vehicleList}" var="vehicle" varStatus="i">
            <tr>
                <form method="post" action="change_vehicle">
                    <td><input id="N" type="number" class="form-control" value=${i.count} readonly></td>
                    <td>
                        <select class="selectpicker show-menu-arrow" data-width="auto" name="vehicle_type">
                            <option value="Bus" <c:if test="${vehicle.vehicleType=='Bus'}">selected</c:if>>Bus</option>
                            <option value="Car" <c:if test="${vehicle.vehicleType=='Car'}">selected</c:if>>Car</option>
                            <option value="Truck" <c:if test="${vehicle.vehicleType=='Truck'}">selected</c:if>>Truck</option>

                        </select>
                    </td>
                    <td><input type="checkbox" class="form-control" name="operable" <c:if test="${vehicle.operable}">checked</c:if>></td>
                    <td>
                        <select class="selectpicker show-menu-arrow" data-width="150px" data-live-search="true" name="model">
                            <c:forEach items="${modelList}" var="model">
                                <option <c:if test="${vehicle.model==model.value}">selected</c:if>>${model.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select class="selectpicker show-menu-arrow" data-width="150px" data-live-search="true" name="manufacturer">
                            <c:forEach items="${manufacturerList}" var="manufacturer">
                                <option <c:if test="${vehicle.manufacturer==manufacturer.value}">selected</c:if>>${manufacturer.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td><input type="number" class="form-control" name="productionYear" value="${vehicle.productionYear}"></td>
                    <td>
                        <select class="selectpicker show-menu-arrow" data-width="150px"
                                data-live-search="true" name="color">
                            <c:forEach items="${colorList}" var="color">
                                <option <c:if test="${vehicle.color==color.valueEn}">selected</c:if> value="${color.valueEn}">${color.valueRu}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select class="selectpicker show-menu-arrow" data-width="auto" name="fuelType">
                            <option value="PETROL" <c:if test="${vehicle.fuelType=='PETROL'}">selected</c:if>><fmt:message key="default.petrol"/></option>
                            <option value="DIESEL" <c:if test="${vehicle.fuelType=='DIESEL'}">selected</c:if>><fmt:message key="default.diesel"/></option>
                            <option value="GAS" <c:if test="${vehicle.fuelType=='GAS'}">selected</c:if>><fmt:message key="default.gas"/></option>
                            <option value="GAS_PETROL" <c:if test="${vehicle.fuelType=='GAS_PETROL'}">selected</c:if>><fmt:message key="default.gas-petrol"/></option>
                            <option value="ELECTRICITY" <c:if test="${vehicle.fuelType=='ELECTRICITY'}">selected</c:if>><fmt:message key="default.electricity"/></option>
                        </select>
                    </td>
                    <td><input type="number" class="form-control" name="mileage" value="${vehicle.mileage}"></td>
                    <td><input id="rent" type="number" class="form-control" name="rentPrice" value="${vehicle.rentPrice}"></td>
                    <td><select class="selectpicker show-menu-arrow" data-width="80" data-live-search="true" name="driverId">
                        <c:forEach items="${userList}" var="user">
                            <option <c:if test="${vehicle.driverId==user.id}">selected</c:if> value="${user.id}">${user.username}</option>
                        </c:forEach>
                    </select></td>
                    <c:if test="${vehicle.vehicleType=='Bus'}">
                        <td><select class="selectpicker show-menu-arrow" data-width="auto" name="doorsNumber">
                            <option <c:if test="${vehicle.doorsNumber==2}">selected</c:if>>2</option>
                            <option <c:if test="${vehicle.doorsNumber==3}">selected</c:if>>3</option>
                            <option <c:if test="${vehicle.doorsNumber==4}">selected</c:if>>4</option>
                            <option <c:if test="${vehicle.doorsNumber==5}">selected</c:if>>5</option>
                        </select></td>
                        <td><input type="number" class="form-control" name="passengerSeatsNumber" value="${vehicle.passengerSeatsNumber}"></td>
                        <td><input type="number" class="form-control" name="standingPlacesNumber" value="${vehicle.standingPlacesNumber}"></td>
                        <td><input type="text" value="-" readonly class="empty-input"></td>
                        <td><input type="text" value="-" readonly class="empty-input"></td>
                        <td><input type="text" value="-" readonly class="empty-input"></td>
                        <td><input type="text" value="-" readonly class="empty-input"></td>
                    </c:if>
                    <c:if test="${vehicle.vehicleType=='Car'}">
                        <td><select class="selectpicker show-menu-arrow" data-width="auto" name="doorsNumber">
                            <option <c:if test="${vehicle.doorsNumber==2}">selected</c:if>>2</option>
                            <option <c:if test="${vehicle.doorsNumber==3}">selected</c:if>>3</option>
                            <option <c:if test="${vehicle.doorsNumber==4}">selected</c:if>>4</option>
                            <option <c:if test="${vehicle.doorsNumber==5}">selected</c:if>>5</option>
                        </select></td>
                        <td><select class="selectpicker show-menu-arrow" data-width="auto" name="passengerSeatsNumber">
                            <option <c:if test="${vehicle.passengerSeatsNumber==1}">selected</c:if>>1</option>
                            <option <c:if test="${vehicle.passengerSeatsNumber==2}">selected</c:if>>2</option>
                            <option <c:if test="${vehicle.passengerSeatsNumber==3}">selected</c:if>>3</option>
                            <option <c:if test="${vehicle.passengerSeatsNumber==4}">selected</c:if>>4</option>
                            <option <c:if test="${vehicle.passengerSeatsNumber==5}">selected</c:if>>5</option>
                            <option <c:if test="${vehicle.passengerSeatsNumber==6}">selected</c:if>>6</option>
                            <option <c:if test="${vehicle.passengerSeatsNumber==7}">selected</c:if>>7</option>
                        </select></td>
                        <td><input type="text" value="-" readonly class="empty-input"></td>
                        <td><input type="checkbox" name="withConditioner" <c:if test="${vehicle.withConditioner}">checked</c:if>></td>
                        <td><input type="text" value="-" readonly class="empty-input"></td>
                        <td><input type="text" value="-" readonly class="empty-input"></td>
                        <td><input type="text" value="-" readonly class="empty-input"></td>
                    </c:if>
                    <c:if test="${vehicle.vehicleType=='Truck'}">
                        <td><input type="text" value="-" readonly class="empty-input"></td>
                        <td><input type="text" value="-" readonly class="empty-input"></td>
                        <td><input type="text" value="-" readonly class="empty-input"></td>
                        <td><input type="text" value="-" readonly class="empty-input"></td>
                        <td><input type="number" class="form-control" value="${vehicle.maxPayload}" name="maxPayload"></td>
                        <td><input type="checkbox" name="enclosed" <c:if test="${vehicle.enclosed}">checked</c:if>></td>
                        <td><input type="checkbox" name="tipper" <c:if test="${vehicle.tipper}">checked</c:if>></td>
                    </c:if>

                    <td><button class="btn btn-primary" name="save" value="${vehicle.id}" type="submit">save changes</button></td>
                    <td><button class="btn btn-danger" name="delete" value="${vehicle.id}" type="submit">delete</button></td>
                </form>
            </tr>
        </c:forEach>
        <tr>
            <td><button class="btn btn-success" name="add-vehicle" type="button">add</button></td>
        </tr>
    </table>
</div>
</body>
</fmt:bundle>
</html>