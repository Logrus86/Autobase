<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:body>
        <t:logos>
            <jsp:attribute name="user_panel"><t:welcome_admin/></jsp:attribute>
        </t:logos>
        <t:admin_edit_buttons/>
        <t:admin_edit_buses/>
    </jsp:body>
</t:generic>