<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:body>
        <t:logos>
            <jsp:attribute name="user_panel"><t:welcome_client/></jsp:attribute>
        </t:logos>
        <form id="registration-form" method="post" action="register">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title"><fmt:message key="default.registration"/></h3>
                </div>
                <div class="msg-error">${reg_error}</div>
                <div class="panel-body">
                    <div class="input-group">
                        <input type="text" class="form-control" name="firstname" value="${firstname}" placeholder="<fmt:message key="default.firstname"/>" required>
                        <input type="text" class="form-control" name="lastname" value="${lastname}" placeholder="<fmt:message key="default.lastname"/>" required>
                        <input type="date" class="form-control" name="dob" value="${dob}" placeholder="<fmt:message key="default.dob"/>" required>
                        <input type="text" class="form-control" name="username" value="${username}" placeholder="<fmt:message key="default.username"/>" required>
                        <input type="password" class="form-control" name="password" placeholder="<fmt:message key="default.password"/>" required title="<fmt:message key="ttip.password8"/>">
                        <input type="password" class="form-control" name="password-repeat" placeholder="<fmt:message key="default.password-repeat"/>" required title="<fmt:message key="ttip.password8"/>">
                        <input type="email" class="form-control" name="email" value="${email}" placeholder="<fmt:message key="default.email"/>" required=>
                        <button type="submit" class="btn btn-primary"><fmt:message key="default.doregister"/></button>
                    </div>
                </div>
            </div>
        </form>
    </jsp:body>
</t:generic>