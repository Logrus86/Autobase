<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<script type='text/javascript' src='<c:url value="/webjars/jquery/1.11.1/jquery.js"/>'></script>
<script type='text/javascript' src='<c:url value="/webjars/bootstrap/3.2.0/js/bootstrap.js"/>'></script>
<link href='<c:url value="/static/css/style.css"/>' rel="stylesheet" media="screen">
<link href='<c:url value="/webjars/bootstrap/3.2.0/css/bootstrap.css"/>' rel="stylesheet" media="screen">
<link href='<c:url value="/static/img/favicon.ico"/>' rel="icon" type="image/png">

<fmt:bundle basename="i18n.text">
<head>
    <title>autobase.com</title>
</head>
<body>
<div id="header">
    <div class="container">
        <a class="logo-header" title="<fmt:message key="default.ordering"/>" itemprop="url" href="http://localhost:8080/do/main">
            <img itemprop="logo" src="/static/img/logo.jpg" alt="<fmt:message key="default.ordering"/>"/>
        </a>
        <div class="btn-group" id="logins">
            <form method="post" action="login">
                    <input type="text" name="username" placeholder="<fmt:message key="default.username"/>">
                    <input type="password" name="password" placeholder="<fmt:message key="default.password"/>">
                    <button class="btn btn-default" type="submit"><fmt:message key="default.dologin"/></button>
                <button type="button" class="btn btn-primary btn-md" data-toggle="modal" data-target="#myModal"><fmt:message key="default.doregister"/></button>
            </form>
        </div>
        <div id="msg-error">${errormsg}</div>
    </div>
</div>
    <%--<button type="button" class="btn btn-default" onclick='location.href="/do/register"'><fmt:message key="default.doregister"/></button>--%>
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
                        <li class=""><a href="#bus-tab" role="tab" data-toggle="tab"><fmt:message key="default.bus"/></a></li>
                        <li class="active"><a href="#car-tab" role="tab" data-toggle="tab"><fmt:message key="default.car"/></a></li>
                        <li class=""><a href="#truck-tab" role="tab" data-toggle="tab"><fmt:message key="default.truck"/></a></li>
                    </ul>
                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade" id="bus-tab">
                            <p>bus fields</p>
                        </div>
                        <div class="tab-pane fade active in" id="car-tab">
                            <p>car fields</p>
                        </div>
                        <div class="tab-pane fade" id="truck-tab">
                            <p>truck fields</p>
                        </div>
                    </div>
                </div>
                <input type="text" class="form-control" name="manufacturer" placeholder="Марка">
                <input type="text" class="form-control" name="model" placeholder="Модель"/>
                <div class="row">
                    <div class="col-lg-6">
                        <div class="input-group">
                            <span class="input-group-addon"><input type="checkbox" checked> <fmt:message
                                    key="deafult.not-older"/></span>
                            <input type="text" class="form-control" name="production-year-max" placeholder="1990">
                        </div>
                    </div>
                </div>
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
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" align="center" id="myModalLabel"><fmt:message key="default.registration"/></h4>
            </div>
            <div class="modal-body">
                <form id="registration-form" method="post" action="register">
                    <div class="input-group">
                        <input type="text" class="form-control" name="firstname" placeholder="<fmt:message key="default.firstname"/>">
                        <input type="text" class="form-control" name="lastname" placeholder="<fmt:message key="default.lastname"/>">
                        <input type="date" class="form-control" name="dob" placeholder="<fmt:message key="default.dob"/>">
                        <input type="text" class="form-control" name="username" placeholder="<fmt:message key="default.username"/>">
                        <input type="password" class="form-control" name="password" placeholder="<fmt:message key="default.password"/>">
                        <input type="password" class="form-control" name="password-repeat" placeholder="<fmt:message key="default.passwordrepeat"/>">
                        <input type="email" class="form-control" name="email" placeholder="<fmt:message key="default.email"/>">
                        <button type="submit" class="btn btn-primary"><fmt:message key="default.doregister"/></button>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="default.cancel"/></button>
            </div>
        </div>
    </div>
</div>
</fmt:bundle>
</html>
