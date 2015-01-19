<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href='<c:url value="/static/img/favicon.ico"/>' rel="icon" type="image/x-icon">

<fmt:setLocale value="${userService.locale}"/>
<fmt:bundle basename="i18n.text">
    <t:generic>
        <jsp:attribute name="hide_footer">hidden="hidden"</jsp:attribute>
        <jsp:body>
            <div align="center" id="error-box">
                <img src="<c:url value="/static/img/error.jpg"/>"/>
                <H1><fmt:message key="default.wrongturn"/> ${statuscode}</H1>

                <p>${statusCode}:
                    <c:choose>
                        <c:when test="${statusCode=='403'}"><fmt:message key="error.403"/></c:when>
                        <c:when test="${statusCode=='404'}"><fmt:message key="error.404"><fmt:param value="${message}"/></fmt:message></c:when>
                        <c:when test="${statusCode=='500'}"><fmt:message key="error.500"/><br>${message}</c:when>
                        <c:otherwise>${message}</c:otherwise>
                    </c:choose>
                </p>
                <p><a href='<c:url value="/"/>'><fmt:message key="default.backto"/> autobase.com</a></p>
            </div>
        </jsp:body>
    </t:generic>
</fmt:bundle>