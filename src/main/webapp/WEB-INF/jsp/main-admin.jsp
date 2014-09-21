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
        <a class="btn btn-default" href='<c:url value="/do/admin-vehicles"/>'>Vehicles</a>
        <a class="btn btn-default" href='<c:url value="/do/admin-colors"/>'>Colors</a>
        <a class="btn btn-default" href='<c:url value="/do/admin-models"/>'>Models</a>
        <a class="btn btn-default" href='<c:url value="/do/admin-manufacturers"/>'>Manufacturers</a>
    </div>
    <div id="users">
        <table>
            <tr align="center">
                <td>№</td>
                <td>firstname</td>
                <td>lastname</td>
                <td>dob</td>
                <td>username</td>
                <td>password</td>
                <td>email</td>
                <td>role</td>
                <td>balance</td>
            </tr>
            <c:forEach items="${userList}" var="user" varStatus="i">
                <tr>
                    <form method="get" action="change_user">
                        <td><input id="N" type="number" class="form-control" value=${i.count} readonly></td>
                        <td><input type="text" class="form-control" name="firstname" value=${user.firstname}></td>
                        <td><input type="text" class="form-control" name="lastname" value=${user.lastname}></td>
                        <td><input type="date" class="form-control" name="dob" value=${user.dob}></td>
                        <td><input type="text" class="form-control" name="username" value=${user.username}></td>
                        <td><input type="password" class="form-control" name="password" value=${user.password}></td>
                        <td><input type="email" class="form-control" name="email" value=${user.email}></td>
                        <td>
                            <select class="selectpicker show-menu-arrow" data-width="auto" name="role">
                                <option value="ADMIN" <c:if test="${user.role=='ADMIN'}">selected</c:if>>ADMIN</option>
                                <option value="CLIENT" <c:if test="${user.role=='CLIENT'}">selected</c:if>>CLIENT</option>
                                <option value="DRIVER" <c:if test="${user.role=='DRIVER'}">selected</c:if>>DRIVER</option>
                            </select>
                        </td>
                        <td><input type="number" class="form-control" name="balance" value=${user.balance}></td>
                        <td><button class="btn btn-primary" name="save" value="${user.id}" type="submit">save changes</button></td>
                        <td><button class="btn btn-danger" name="delete" value="${user.id}" type="submit">delete</button></td>
                    </form>
                </tr>
            </c:forEach>
            <tr>
                <td><button class="btn btn-success" name="add-user" type="button">add</button></td>
                <td><button class="btn btn-info" name="save-all-users" type="button">save all</button></td>
            </tr>
        </table>
    </div>
    <br>
    <div id="footer" align="center">
        EPAM Systems © 2014<br>
        Bobylev P.R. © 2014
    </div>
    </body>
</fmt:bundle>
</html>