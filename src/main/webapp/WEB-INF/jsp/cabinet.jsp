<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:body>
        <t:logos>
            <jsp:attribute name="user_panel"><t:welcome_client/></jsp:attribute>
        </t:logos>
        <t:user_edit_form/>
    </jsp:body>
</t:generic>
