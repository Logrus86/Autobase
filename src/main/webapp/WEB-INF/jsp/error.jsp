<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:bundle basename="i18n.text">
    <t:generic>
        <jsp:body>
            <div align="center" id="error-box">
                <img src="<c:url value="/static/img/error.jpg"/>"/>
                <H1><fmt:message key="default.wrongturn"/> ${statuscode}</H1>
                <c:if test="${statuscode=='404'}"><p><fmt:message key="default.page404"/></p></c:if>
                <c:if test="${statuscode=='500'}"><p>Server error.</p></c:if>
                <p><a href='<c:url value="/"/>'><fmt:message key="default.backto"/> autobase.com</a></p>
            </div>
        </jsp:body>
        <jsp:attribute name="hide_footer">hidden="hidden"</jsp:attribute>
    </t:generic>
</fmt:bundle>