<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href='<c:url value="/static/img/favicon.ico"/>' rel="icon" type="image/x-icon">

<fmt:setLocale value="${sessionState.locale}"/>
<fmt:bundle basename="i18n.text">
    <html>
    <head>
        <title>autobase.com</title>
    </head>
    <t:generic>
        <jsp:attribute name="header"/>
        <jsp:attribute name="footer"/>
        <jsp:body>
            <t:logos>
                <jsp:attribute name="user_panel"><t:login_register_bar/></jsp:attribute>
            </t:logos>
            <t:search_form/>
        </jsp:body>
    </t:generic>
    </html>
</fmt:bundle>

<c:if test="${userService.errorMap!=null}">
    <script language="javascript">
        showModalByDefault('#modalRegForm');
    </script>
</c:if>
