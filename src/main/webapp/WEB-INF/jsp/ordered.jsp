<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.text">
    <t:generic>
        <jsp:body>
            <t:logos>
                <jsp:attribute name="user_panel"><t:welcome_client/></jsp:attribute>
            </t:logos>
            <t:order_info_form/>
        </jsp:body>
    </t:generic>
</fmt:bundle>