<%@tag description="panel with login and register buttons" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:bundle basename="i18n.text">
    <div class="btn-group" id="logins">
        <form method="post" action="login">
            <input type="text" name="username" placeholder="<fmt:message key="default.username"/>" required>
            <input type="password" name="password" placeholder="<fmt:message key="default.password"/>" required>
            <button class="btn btn-default" type="submit"><fmt:message key="default.dologin"/></button>
            <button type="button" class="btn btn-primary btn-md" data-toggle="modal" data-target="#myModal">
                <fmt:message key="default.doregister"/></button>
        </form>
    </div>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" align="center" id="myModalLabel"><fmt:message key="default.registration"/></h4>
                </div>
                <div class="modal-body">
                    <form id="registration-form" method="post" action="register">
                        <div class="input-group">
                            <input type="text" class="form-control" name="firstname"
                                   placeholder="<fmt:message key="default.firstname"/>" required>
                            <input type="text" class="form-control" name="lastname"
                                   placeholder="<fmt:message key="default.lastname"/>" required>
                            <input type="date" class="form-control" name="dob"
                                   placeholder="<fmt:message key="default.dob"/>" required>
                            <input type="text" class="form-control" name="username"
                                   placeholder="<fmt:message key="default.username"/>" required>
                            <input type="password" class="form-control" name="password"
                                   placeholder="<fmt:message key="default.password"/>" required
                                   title="<fmt:message key="ttip.password8"/>">
                            <input type="password" class="form-control" name="password-repeat"
                                   placeholder="<fmt:message key="default.password-repeat"/>" required
                                   title="<fmt:message key="ttip.password8"/>">
                            <input type="email" class="form-control" name="email"
                                   placeholder="<fmt:message key="default.email"/>" required>
                            <br>
                            <button type="submit" class="btn btn-primary"><fmt:message key="default.doregister"/></button>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="default.cancel"/></button>
                </div>
            </div>
        </div>
    </div>
</fmt:bundle>