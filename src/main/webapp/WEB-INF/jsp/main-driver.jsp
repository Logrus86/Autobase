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
                <fmt:message key="default.welcome"/> ${user.username}! <fmt:message key="default.how-are-you"/>
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
<form id="driver-form" method="post" action="update_user">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title"><fmt:message key="default.profile-edit"/></h3>
            </div>
            <div class="msg-error">${cab_error}</div>
            <div class="panel-body">
                <div class="input-group">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.firstname"/></span>
                                <input type="text" class="form-control" name="firstname" value="${user.firstname}"
                                       required>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.lastname"/></span>
                                <input type="text" class="form-control" name="lastname" value="${user.lastname}"
                                       required>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.dob"/></span>
                                <input type="date" class="form-control" name="dob" value="${user.dob}" required>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.username"/></span>
                                <input type="text" class="form-control" name="username" value="${user.username}"
                                       required>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.password"/></span>
                                <input type="password" class="form-control" name="password" value="${user.password}"
                                       required
                                       title="<fmt:message key="ttip.password8"/>">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.password"/></span>
                                <input type="password" class="form-control" name="password-repeat"
                                       value="${user.password}" required
                                       title="<fmt:message key="ttip.password8"/>">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.email"/></span>
                                <input type="email" class="form-control" name="email" value="${user.email}"
                                       required>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary"><fmt:message key="default.dosave"/></button>
                </div>
            </div>
        </div>
    </form>
<form id="vehicle-form" method="post" action="update_vehicle">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title"><fmt:message key="default.vehicle-edit"/></h3>
            </div>
            <div class="msg-error">${upd_vh_error}</div>
            <div class="panel-body">
                <div class="input-group">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><input type="checkbox" name="operable" <c:if test="${vehicle.operable}">checked</c:if>></span>
                                <span class="form-control"><fmt:message key="default.operable"/></span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.model"/></span>
                                <select class="selectpicker show-menu-arrow" data-width="auto" data-live-search="true" name="model">
                                    <c:forEach items="${modelList}" var="model">
                                        <option <c:if test="${vehicle.model==model.value}">selected</c:if>>${model}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.manufacturer"/></span>
                                <select class="selectpicker show-menu-arrow" data-width="auto" data-live-search="true" name="manufacturer">
                                    <c:forEach items="${manufacturerList}" var="manufacturer">
                                        <option <c:if test="${vehicle.manufacturer==manufacturer.value}">selected</c:if>>${manufacturer}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.color"/></span>
                                <select class="selectpicker show-menu-arrow" data-width="auto"
                                        data-live-search="true" name="color">
                                    <c:forEach items="${colorList}" var="color">
                                        <option <c:if test="${vehicle.color==color.valueEn}">selected</c:if> value="${color.valueEn}">${color.valueRu}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.fuel"/></span>
                                <select class="selectpicker show-menu-arrow" data-width="auto" name="fuelType">
                                    <option value="PETROL" <c:if test="${vehicle.fuelType=='PETROL'}">selected</c:if>><fmt:message key="default.petrol"/></option>
                                    <option value="DIESEL" <c:if test="${vehicle.fuelType=='DIESEL'}">selected</c:if>><fmt:message key="default.diesel"/></option>
                                    <option value="GAS" <c:if test="${vehicle.fuelType=='GAS'}">selected</c:if>><fmt:message key="default.gas"/></option>
                                    <option value="GAS_PETROL" <c:if test="${vehicle.fuelType=='GAS_PETROL'}">selected</c:if>><fmt:message key="default.gas-petrol"/></option>
                                    <option value="ELECTRICITY" <c:if test="${vehicle.fuelType=='ELECTRICITY'}">selected</c:if>><fmt:message key="default.electricity"/></option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.mileage"/></span>
                                <input type="number" class="form-control" name="mileage" value="${vehicle.mileage}">
                                <span class="input-group-addon"> <fmt:message key="default.kilometers"/></span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.year-prod"/></span>
                                <input type="number" class="form-control" name="productionYear"
                                       value="${vehicle.productionYear}">
                            </div>
                        </div>
                    </div>
                    <c:if test="${vehicle.vehicleType=='Bus'}">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="input-group">
                                    <span class="input-group-addon"><fmt:message key="default.passengerSeatsNumber"/></span>
                                    <input type="number" class="form-control" name="passengerSeatsNumber" value="${vehicle.passengerSeatsNumber}">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="input-group">
                                    <span class="input-group-addon"><fmt:message key="default.standingPlacesNumber"/></span>
                                    <input type="number" class="form-control" name="standingPlacesNumber" value="${vehicle.standingPlacesNumber}">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="input-group">
                                    <span class="input-group-addon"><fmt:message key="default.doorsNumber"/></span>
                                    <select class="selectpicker show-menu-arrow" data-width="auto" name="doorsNumber">
                                        <option <c:if test="${vehicle.doorsNumber==2}">selected</c:if>>2</option>
                                        <option <c:if test="${vehicle.doorsNumber==3}">selected</c:if>>3</option>
                                        <option <c:if test="${vehicle.doorsNumber==4}">selected</c:if>>4</option>
                                        <option <c:if test="${vehicle.doorsNumber==5}">selected</c:if>>5</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${vehicle.vehicleType=='Car'}">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="input-group">
                                    <span class="input-group-addon"><fmt:message key="default.passengerSeatsNumber"/></span>
                                    <select class="selectpicker show-menu-arrow" data-width="auto" name="passengerSeatsNumber">
                                        <option <c:if test="${vehicle.passengerSeatsNumber==1}">selected</c:if>>1</option>
                                        <option <c:if test="${vehicle.passengerSeatsNumber==2}">selected</c:if>>2</option>
                                        <option <c:if test="${vehicle.passengerSeatsNumber==3}">selected</c:if>>3</option>
                                        <option <c:if test="${vehicle.passengerSeatsNumber==4}">selected</c:if>>4</option>
                                        <option <c:if test="${vehicle.passengerSeatsNumber==5}">selected</c:if>>5</option>
                                        <option <c:if test="${vehicle.passengerSeatsNumber==6}">selected</c:if>>6</option>
                                        <option <c:if test="${vehicle.passengerSeatsNumber==7}">selected</c:if>>7</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="input-group">
                                    <span class="input-group-addon"><fmt:message key="default.doorsNumber"/></span>
                                    <select class="selectpicker show-menu-arrow" data-width="auto" name="doorsNumber">
                                        <option <c:if test="${vehicle.doorsNumber==2}">selected</c:if>>2</option>
                                        <option <c:if test="${vehicle.doorsNumber==3}">selected</c:if>>3</option>
                                        <option <c:if test="${vehicle.doorsNumber==4}">selected</c:if>>4</option>
                                        <option <c:if test="${vehicle.doorsNumber==5}">selected</c:if>>5</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="input-group">
                                    <span class="input-group-addon"><input type="checkbox" name="withConditioner" <c:if test="${vehicle.withConditioner}">checked</c:if>></span>
                                    <span class="form-control"><fmt:message key="default.conditioner"/></span>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${vehicle.vehicleType=='Truck'}">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="input-group">
                                    <span class="input-group-addon"><fmt:message key="default.maxPayload"/></span>
                                    <input type="number" class="form-control" value="${vehicle.maxPayload}" name="maxPayload">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="input-group">
                                    <span class="input-group-addon"><input type="checkbox" name="enclosed" <c:if test="${vehicle.enclosed}">checked</c:if>></span>
                                    <span class="form-control"><fmt:message key="default.enclosed"/></span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="input-group">
                                    <span class="input-group-addon"><input type="checkbox" name="tipper" <c:if test="${vehicle.tipper}">checked</c:if>></span>
                                    <span class="form-control"><fmt:message key="default.tipper"/></span>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <button type="submit" class="btn btn-primary"><fmt:message key="default.dosave"/></button>
                </div>
            </div>
        </div>
        </form>
<div id="footer" align="center">
    EPAM Systems © 2014<br>
    Bobylev P.R. © 2014
</div>
</body>
</fmt:bundle>
</html>