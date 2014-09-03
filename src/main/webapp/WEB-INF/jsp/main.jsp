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
        <a title="<fmt:message key="default.ordering"/>" href='<c:url value="/"/>'><img src='<c:url value="/static/img/logo.jpg"/>'/></a>

        <div class="btn-group" id="logins">
            <form method="post" action="login">
                <input type="text" name="username" placeholder="<fmt:message key="default.username"/>" required>
                <input type="password" name="password" placeholder="<fmt:message key="default.password"/>" required>
                <button class="btn btn-default" type="submit"><fmt:message key="default.dologin"/></button>
                <button type="button" class="btn btn-primary btn-md" data-toggle="modal" data-target="#myModal">
                    <fmt:message key="default.doregister"/></button>
            </form>
        </div>
        <div id="msg-error">${errormsg}</div>
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
<form method="post" id="search-form">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title"><fmt:message key="default.search"/></h3>
        </div>
        <div class="panel-body">
            <div class="input-group">
                <div class="bs-example bs-example-tabs">
                    <ul id="myTab" class="nav nav-tabs" role="tablist">
                        <li class=""><a href="#bus-tab" role="tab" data-toggle="tab"><fmt:message
                                key="default.bus"/></a></li>
                        <li class="active"><a href="#car-tab" role="tab" data-toggle="tab"><fmt:message
                                key="default.car"/></a></li>
                        <li class=""><a href="#truck-tab" role="tab" data-toggle="tab"><fmt:message
                                key="default.truck"/></a></li>
                    </ul>
                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade" id="bus-tab">
                            <p>
                                <fmt:message key="default.passengerSeatsNumber"/>
                                <select class="selectpicker show-menu-arrow" data-width="60">
                                    <option>20</option>
                                    <option>30</option>
                                    <option>40</option>
                                    <option>50</option>
                                </select><br>
                                <fmt:message key="default.standingPlacesNumber"/>
                                <select class="selectpicker show-menu-arrow" data-width="60">
                                    <option>20</option>
                                    <option>30</option>
                                    <option>40</option>
                                    <option>50</option>
                                </select><br>
                                <fmt:message key="default.doorsNumber"/>
                                <select class="selectpicker show-menu-arrow" data-width="60">
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                </select>
                            </p>
                        </div>
                        <div class="tab-pane fade active in" id="car-tab">
                            <p><fmt:message key="default.passengerSeatsNumber"/>
                                <select class="selectpicker show-menu-arrow" data-width="60">
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                    <option>6</option>
                                    <option>7</option>
                                </select><br><fmt:message key="default.doorsNumber"/>
                                <select class="selectpicker show-menu-arrow" data-width="60">
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                </select>
                                <br><input type="checkbox"> <fmt:message key="default.conditioner"/>
                            </p>
                        </div>
                        <div class="tab-pane fade" id="truck-tab">
                            <p>
                                <fmt:message key="default.maxPayload"/> <input type="number"><br>
                                <input type="checkbox"> <fmt:message key="default.enclosed"/><br>
                                <input type="checkbox"> <fmt:message key="default.tipper"/>
                            </p>
                        </div>
                    </div>
                </div>
                <p><fmt:message key="default.model"/>
                    <select class="selectpicker show-menu-arrow" data-width="auto" data-live-search="true">
                        <option>model 1</option>
                        <option>model 2</option>
                        <option>model 3</option>
                        <option>model 4</option>
                        <option>model 5</option>
                        <option>model 6</option>
                        <option>model 7</option>
                    </select><br>
                    <fmt:message key="default.manufacturer"/>
                    <select class="selectpicker show-menu-arrow" data-width="auto" data-live-search="true">
                        <option>manufacturer 1</option>
                        <option>manufacturer 2</option>
                        <option>manufacturer 3</option>
                        <option>manufacturer 4</option>
                        <option>manufacturer 5</option>
                        <option>manufacturer 6</option>
                        <option>manufacturer 7</option>
                    </select><br>
                    <fmt:message key="default.color"/>
                    <select class="selectpicker show-menu-arrow" data-width="auto">
                        <option>color 1</option>
                        <option>color 2</option>
                        <option>color 3</option>
                        <option>color 4</option>
                        <option>color 5</option>
                        <option>color 6</option>
                        <option>color 7</option>
                    </select><br>
                    <fmt:message key="default.fuel"/>
                    <select class="selectpicker show-menu-arrow" data-width="auto">
                        <option><fmt:message key="default.petrol"/></option>
                        <option><fmt:message key="default.diesel"/></option>
                        <option><fmt:message key="default.gas"/></option>
                        <option><fmt:message key="default.gas-petrol"/></option>
                        <option><fmt:message key="default.electricity"/></option>
                    </select><br>
                </p>
                <div class="row">
                    <div class="col-lg-10">
                        <div class="input-group">
                            <span class="input-group-addon"><input type="checkbox" checked> <fmt:message
                                    key="default.mileage-max"/></span>
                            <input type="text" class="form-control" name="mileage-max" placeholder="50">
                            <span class="input-group-addon"> <fmt:message key="default.kilometers"/></span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-10">
                        <div class="input-group">
                            <span class="input-group-addon"><input type="checkbox" checked> <fmt:message
                                    key="default.not-older"/></span>
                            <input type="text" class="form-control" name="production-year-max" placeholder="1990">
                            <span class="input-group-addon"> <fmt:message key="default.year-prod"/></span>
                        </div>
                    </div>
                </div>
                <br>
                <button type="submit" class="btn btn-primary"><fmt:message key="default.dosearch"/></button>
            </div>
        </div>
    </div>
</form>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title" align="center" id="myModalLabel"><fmt:message key="default.registration"/></h4>
            </div>
            <div class="modal-body">
                <form id="registration-form" method="post" action="register">
                    <div class="input-group">
                        <input type="text" class="form-control" name="firstname" placeholder="<fmt:message key="default.firstname"/>" required>
                        <input type="text" class="form-control" name="lastname" placeholder="<fmt:message key="default.lastname"/>" required>
                        <input type="date" class="form-control" name="dob" placeholder="<fmt:message key="default.dob"/>" required>
                        <input type="text" class="form-control" name="username" placeholder="<fmt:message key="default.username"/>" required>
                        <input type="password" class="form-control" name="password" placeholder="<fmt:message key="default.password"/>" required>
                        <%--<input type="password" class="form-control" name="password-repeat" placeholder="<fmt:message key="default.passwordrepeat"/>" required>--%>
                        <input type="email" class="form-control" name="email" placeholder="<fmt:message key="default.email"/>" required>
                        <button type="submit" class="btn btn-primary"><fmt:message key="default.doregister"/></button>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <br>
                <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message
                        key="default.cancel"/></button>
            </div>
        </div>
    </div>
</div>
</fmt:bundle>
</html>
