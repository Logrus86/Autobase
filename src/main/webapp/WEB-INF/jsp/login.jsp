<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:bundle basename="i18n.text">
<head>
    <title></title>
</head>
<body>
<form method="post">
    <fmt:message key="default.login"></fmt:message><input type="text" name="username"/>
    <fmt:message key="default.password"></fmt:message><input type="password" name="password"/>
    <button type="submit"><fmt:message key="default.dologin"></fmt:message></button>
</form>
<form action="register">
    <button><fmt:message key="default.doregister"></fmt:message></button>
</form>
</body>
</fmt:bundle>
</html>
