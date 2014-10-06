<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.text">
    <t:generic>
        <jsp:attribute name="header"/>
        <jsp:attribute name="footer"/>
        <jsp:body>
            <t:logos>
                <jsp:attribute name="user_panel"><t:welcome_client/></jsp:attribute>
            </t:logos>
            <t:search_form/>
        </jsp:body>
    </t:generic>
</fmt:bundle>