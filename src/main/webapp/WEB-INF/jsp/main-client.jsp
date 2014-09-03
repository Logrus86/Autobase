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

        <div class="btn-group" id="userbar" align="right">
            <p>
                <fmt:message key="default.welcome"/> <a title="<fmt:message key="default.to-cabinet"/>"
                                                        href='<c:url value="/do/cabinet"/>'>${user.username}</a>!
                <fmt:message key="default.at-balance"/> <a title="<fmt:message key="default.to-cabinet-and-add"/>"
                                                           href='<c:url value="/do/cabinet"/>'>${user.balance}
                <fmt:message key="default.currency"/></a>
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
            <p id="h1sub" style="font-size: 16px;"><fmt:message key="default.service-desc"/></p>
        </div>
    </div>
</div>
<form method="post" id="search-form">
</form>
</fmt:bundle>
</html>
