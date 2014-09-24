<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:body>
        <t:logos>
            <jsp:attribute name="user_panel"><t:login_bar/></jsp:attribute>
        </t:logos>
        <t:search_form/>
    </jsp:body>
</t:generic>