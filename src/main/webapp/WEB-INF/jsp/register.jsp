<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:bundle basename="i18n.text">
<head>
    <title></title>
</head>
<body>
<form method="post">
    <fmt:message key="default.firstname"></fmt:message><input type="text" name="firstname"/><br>
    <fmt:message key="default.lastname"></fmt:message><input type="text" name="lastname"/><br>
    <fmt:message key="default.dob"></fmt:message><input type="date" name="dob"/><br>
    <fmt:message key="default.login"></fmt:message><input type="text" name="username"/><br>
    <fmt:message key="default.password"></fmt:message><input type="password" name="password"/><br>
    <fmt:message key="default.email"></fmt:message><input type="email" name="email"/><br>
    <button type="submit"> <fmt:message key="default.doregister"></fmt:message></button>
</form>
</body>
</fmt:bundle>
</html>
