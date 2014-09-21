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
        <div class="msg-error">${errormsg}</div>
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
<div>
    result:<br>
        ${search_result}<br>
    <c:forEach items="${vehicleList}" var="vehicle">
        ${vehicle.id} ${vehicle} <br>
    </c:forEach>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" align="center" id="myModalLabel"><fmt:message key="default.registration"/></h4>
            </div>
            <div class="modal-body">
                <form id="registration-form" method="post" action="register">
                    <div class="input-group">
                        <input type="text" class="form-control" name="firstname" placeholder="<fmt:message key="default.firstname"/>" required>
                        <input type="text" class="form-control" name="lastname" placeholder="<fmt:message key="default.lastname"/>" required>
                        <input type="date" class="form-control" name="dob" placeholder="<fmt:message key="default.dob"/>" required>
                        <input type="text" class="form-control" name="username" placeholder="<fmt:message key="default.username"/>" required>
                        <input type="password" class="form-control" name="password" placeholder="<fmt:message key="default.password"/>" required title="<fmt:message key="ttip.password8"/>">
                        <input type="password" class="form-control" name="password-repeat" placeholder="<fmt:message key="default.passwordrepeat"/>" required title="<fmt:message key="ttip.password8"/>">
                        <input type="email" class="form-control" name="email" placeholder="<fmt:message key="default.email"/>" required>
                        <br>
                        <button type="submit" class="btn btn-primary"><fmt:message key="default.doregister"/></button>
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
<div id="footer" align="center">
    EPAM Systems © 2014<br>
    Bobylev P.R. © 2014
</div>
</body>
</fmt:bundle>
</html>
