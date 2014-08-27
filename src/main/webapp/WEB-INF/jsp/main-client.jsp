<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<script type='text/javascript' src='<c:url value="/webjars/jquery/1.11.1/jquery.js"/>'></script>
<script type='text/javascript' src='<c:url value="/webjars/bootstrap/3.2.0/js/bootstrap.js"/>'></script>
<link href='<c:url value="/webjars/bootstrap/3.2.0/css/bootstrap.css"/>' rel="stylesheet" media="screen">
<link href='<c:url value="/static/css/style.css"/>' rel="stylesheet" media="screen">
<link href='<c:url value="/static/img/favicon.ico"/>' rel="icon" type="image/png">

<fmt:bundle basename="i18n.text">
<head>
    <title>autobase.com</title>
</head>
<body>
<div id="header">
    <div class="container">
        <a class="logo-header" title="<fmt:message key="default.ordering"/>" itemprop="url"
           href="http://localhost:8080/do/main">
            <img itemprop="logo" src="/static/img/logo.jpg" alt="<fmt:message key="default.ordering"/>">
        </a>
        <div class="btn-group" id="userbar" align="right">
            <p>
                <fmt:message key="default.welcome"/> ${user.username}! На вашем счете ${user.balance} тг.
            <button type="button" class="btn btn-default" onclick='location.href="/do/quit"'><fmt:message key="default.doexit"/>
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
            <p id="h1sub" style="font-size: 16px;"><fmt:message key="default.servicedesc"/></p>
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
                <div class="btn-group" data-toggle="buttons">
                    <label class="btn btn-primary active">
                        <input type="radio" name="vehicle-type" id="bus"> <fmt:message key="default.bus"/>
                    </label>
                    <label class="btn btn-primary">
                        <input type="radio" name="vehicle-type" id="car" checked> <fmt:message key="default.car"/>
                    </label>
                    <label class="btn btn-primary">
                        <input type="radio" name="vehicle-type" id="truck"> <fmt:message key="default.truck"/>
                    </label>
                </div>
                <input type="text" class="form-control" name="manufacturer" placeholder="Марка">
                <input type="text" class="form-control" name="model" placeholder="Модель"/>
                <div class="row">
                    <div class="col-lg-6">
                        <div class="input-group">
                            <span class="input-group-addon"><input type="checkbox" checked> <fmt:message
                                    key="deafult.notolder"/></span>
                            <input type="text" class="form-control" name="production-year-max" placeholder="1990">
                        </div>
                    </div>
                </div>
                <button type="submit"><fmt:message key="default.dosearch"/></button>
            </div>
        </div>
    </div>
</form>
</fmt:bundle>
</html>
