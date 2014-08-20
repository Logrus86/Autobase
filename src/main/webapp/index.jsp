<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index.jsp</title>
</head>
<body>
<%
    response.sendRedirect(request.getContextPath() + "/do/login");
//    redirect to mainpage
%>
</body>
</html>