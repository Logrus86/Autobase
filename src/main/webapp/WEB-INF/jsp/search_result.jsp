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
                <jsp:attribute name="user_panel"><t:welcome_client/></jsp:attribute>
            </t:logos>
            <t:search_result_form/>
        </jsp:body>
    </t:generic>
</fmt:bundle>

<c:if test="${order_err!=null}">
    <script language="javascript">
        showModalByDefault('#modalOrderForm');
    </script>
</c:if>