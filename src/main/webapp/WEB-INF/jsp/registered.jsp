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
        <title></title>
    </head>
    <body>
    <fmt:message key="default.registered"/><br>
    <fmt:message key="default.firstname"/>: ${user.firstname}<br>
    <fmt:message key="default.lastname"/>: ${user.lastname}<br>
    <%--<fmt:message key="default.dob"/>: ${user.dob}<br>--%>
    <fmt:message key="default.dob"/>: <%= request.getParameter("dob") %><br>
    <fmt:message key="default.username"/>: ${user.username}<br>
    <fmt:message key="default.password"/>: ${user.password}<br>
    <fmt:message key="default.email"/>: ${user.email}<br>
    <fmt:message key="default.balance"/>: ${user.balance} тг.<br>

    <p><a href="/">Назад на autobase.com</a></p>
    </body>
</fmt:bundle>
</html>