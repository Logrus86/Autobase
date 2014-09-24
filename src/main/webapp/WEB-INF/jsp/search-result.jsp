<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:generic>
    <jsp:body>
        <t:logos>
            <jsp:attribute name="user_panel"><t:welcome_client/></jsp:attribute>
        </t:logos>
        <t:search_result_form/>
    </jsp:body>
</t:generic>