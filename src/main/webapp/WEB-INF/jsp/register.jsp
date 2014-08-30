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
    <title><fmt:message key="default.registration"/> - autobase.com</title>
</head>
<body>
<div id="header">
    <div class="container">
        <a class="logo-header" title="<fmt:message key="default.ordering"/>" itemprop="url"
           href="http://localhost:8080/do/main">
            <img itemprop="logo" src="/static/img/logo.jpg" alt="<fmt:message key="default.ordering"/>">
        </a>
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
<form method="post" id="registration-form">
    <fmt:message key="default.registration"/>
    <div class="input-group">
        <input type="text" class="form-control" name="firstname" placeholder="<fmt:message key="default.firstname"/>">
        <input type="text" class="form-control" name="lastname" placeholder="<fmt:message key="default.lastname"/>">
        <input type="date" class="form-control" name="dob" placeholder="<fmt:message key="default.dob"/>">
        <input type="text" class="form-control" name="username" placeholder="<fmt:message key="default.username"/>">
        <input type="password" class="form-control" name="password" placeholder="<fmt:message key="default.password"/>">
        <input type="password" class="form-control" name="password-two" placeholder="<fmt:message key="default.passwordrepeat"/>">
        <input type="email" class="form-control" name="email" placeholder="<fmt:message key="default.email"/>">

        <button type="submit"><fmt:message key="default.doregister"/></button>
    </div>
</form>
</fmt:bundle>
</html>

