<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:bundle basename="i18n.text">
    <head>
        <title></title>
    </head>
    <body>
    <fmt:message key="default.registered"></fmt:message><br>
    <fmt:message key="default.firstname"></fmt:message>: <%= request.getParameter("firstname") %><br>
    <fmt:message key="default.lastname"></fmt:message>: <%= request.getParameter("lastname") %><br>
    <fmt:message key="default.dob"></fmt:message>: <%= request.getParameter("dob") %><br>
    <fmt:message key="default.login"></fmt:message>: <%= request.getParameter("username") %><br>
    <fmt:message key="default.password"></fmt:message>: <%= request.getParameter("password") %><br>
    <fmt:message key="default.email"></fmt:message>: <%= request.getParameter("email") %><br>
    </body>
</fmt:bundle>
</html>