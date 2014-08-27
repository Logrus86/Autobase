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

<div class="bs-example bs-example-tabs">
    <ul id="myTab" class="nav nav-tabs" role="tablist">
        <li class=""><a href="#home" role="tab" data-toggle="tab">Home</a></li>
        <li class="active"><a href="#profile" role="tab" data-toggle="tab">Profile</a></li>
        <li class="dropdown">
            <a href="#" id="myTabDrop1" class="dropdown-toggle" data-toggle="dropdown">Dropdown <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1">
                <li class=""><a href="#dropdown1" tabindex="-1" role="tab" data-toggle="tab">@fat</a></li>
                <li class=""><a href="#dropdown2" tabindex="-1" role="tab" data-toggle="tab">@mdo</a></li>
            </ul>
        </li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade" id="home">
            <p>Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown aliqua, retro synth master ...</p>
        </div>
        <div class="tab-pane fade active in" id="profile">
            <p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. Exercitation +1 labore veli...</p>
        </div>
        <div class="tab-pane fade" id="dropdown1">
            <p>Etsy mixtape wayfarers, ethical wes anderson tofu before they sold out mcsweeney's organic lomo retro fanny pack lo-...</p>
        </div>
        <div class="tab-pane fade" id="dropdown2">
            <p>Trust fund seitan letterpress, keytar raw denim keffiyeh etsy art party before they sold out master cleanse gluten-f...</p>
        </div>
    </div>
</div>
</fmt:bundle>
</html>
