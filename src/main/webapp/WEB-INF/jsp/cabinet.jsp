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
                    <fmt:message key="default.welcome"/> <a title="<fmt:message key="default.to-cabinet"/>"
                                                            href='<c:url value="/do/cabinet"/>'>${user.username}</a>!
                    <fmt:message key="default.at-balance"/> <a title="<fmt:message key="default.to-cabinet-and-add"/>"
                                                               href='<c:url value="/do/cabinet"/>'>${user.balance}
                    <fmt:message key="default.currency"/></a>
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
    <form id="user-form" method="post" action="update_user">
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
                                <input type="text" class="form-control" name="firstname" value="${user.firstname}" required>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.lastname"/></span>
                                <input type="text" class="form-control" name="lastname" value="${user.lastname}" required>
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
                                <input type="text" class="form-control" name="username" value="${user.username}" required>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.password"/></span>
                                <input type="password" class="form-control" name="password" value="${user.password}" required
                                       title="<fmt:message key="ttip.password8"/>">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.password"/></span>
                                <input type="password" class="form-control" name="password-repeat" value="${user.password}" required
                                       title="<fmt:message key="ttip.password8"/>">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.email"/></span>
                                <input type="email" class="form-control" name="email" value="${user.email}" required>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.balance"/></span>
                                <input readonly type="number" class="form-control" name="balance" value="${user.balance}">
                                <span class="input-group-addon"><fmt:message key="default.currency"/></span>
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="button"><fmt:message key="default.do-add-funds"/></button>
                                </span>
                            </div>
                        </div>
                    </div>
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
