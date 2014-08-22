<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:bundle basename="i18n.text">
<head>
    <title></title>
</head>
<body>
<H3><fmt:message key="default.welcome"></fmt:message> <%= request.getParameter("username") %> ! <H3>
</body>
</fmt:bundle>
</html>
