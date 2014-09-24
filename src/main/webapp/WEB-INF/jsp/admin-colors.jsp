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
    <a class="btn btn-default" href='<c:url value="/do/admin-cars"/>'>Cars</a>
    <a class="btn btn-default" href='<c:url value="/do/admin-buses"/>'>Buses</a>
    <a class="btn btn-default" href='<c:url value="/do/admin-trucks"/>'>Trucks</a>
    <a class="btn btn-default" href='<c:url value="/do/admin-vehicles"/>'>Vehicles</a>
    <a class="btn btn-default" href='<c:url value="/do/admin-colors"/>'>Colors</a>
    <a class="btn btn-default" href='<c:url value="/do/admin-models"/>'>Models</a>
    <a class="btn btn-default" href='<c:url value="/do/admin-manufacturers"/>'>Manufacturers</a>
</div>
<div id="colors">
    <table>
        <div class="msg-error">${color_change_error}</div>
        <tr align="center">
            <td>id</td>
            <td>valueEn</td>
            <td>valueRu</td>
        </tr>
        <c:forEach items="${colorList}" var="color" varStatus="i">
            <tr>
                <form method="post" action="change_color">
                    <td><input id="N" type="number" class="form-control" value=${i.count} readonly></td>
                    <td><input required type="text" class="form-control" name="valueEn" value=${color.valueEn}></td>
                    <td><input required type="text" class="form-control" name="valueRu" value=${color.valueRu}></td>
                    <td>
                        <button class="btn btn-primary" name="save" value="${color.id}" type="submit">save changes</button>
                    </td>
                    <td>
                        <button class="btn btn-danger" name="delete" value="${color.id}" type="submit">delete</button>
                    </td>
                </form>
            </tr>
        </c:forEach>
        <tr>
            <td><button class="btn btn-success" name="add" type="button">add</button></td>
        </tr>
    </table>
</div>
</body>
</fmt:bundle>
</html>
