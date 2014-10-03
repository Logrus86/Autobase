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
                <jsp:attribute name="user_panel"><t:welcome_driver/></jsp:attribute>
            </t:logos>
            <div class="row">
                <div class="col-md-6" id="driver_div"><t:user_edit_form/></div>
                <div class="col-md-6"><t:vehicles_edit_form/></div>
            </div>
        </jsp:body>
    </t:generic>
</fmt:bundle>