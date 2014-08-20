<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<fmt:bundle basename="i18n.text">
    <head>
        <title>RepeatText.jsp</title>
    </head>
    <body>
    <p><fmt:message key="default.outText"></fmt:message></p>

    <p><%= request.getParameter("text")%>
    </p>
    </body>
</fmt:bundle>
</html>
