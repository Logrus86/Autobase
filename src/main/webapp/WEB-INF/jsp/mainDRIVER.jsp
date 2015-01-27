<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href='<c:url value="/static/img/favicon.ico"/>' rel="icon" type="image/x-icon">

<fmt:setLocale value="${sessionState.locale}"/>
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