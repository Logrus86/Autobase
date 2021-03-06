<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href='<c:url value="/static/img/favicon.ico"/>' rel="icon" type="image/x-icon">

<fmt:setLocale value="${sessionState.locale}"/>
<fmt:bundle basename="i18n.text">
    <t:generic>
        <jsp:body>
            <t:logos>
                <jsp:attribute name="user_panel"><t:welcome_admin/></jsp:attribute>
            </t:logos>
            <t:admin_buttons/>
            <t:admin_edit_manufacturers/>
        </jsp:body>
    </t:generic>
</fmt:bundle>

<c:if test="${manufacturerService.errorMap.create_err!=null}">
    <script language="javascript">
        showModalByDefault('#modalCreateManufacturer');
    </script>
</c:if>