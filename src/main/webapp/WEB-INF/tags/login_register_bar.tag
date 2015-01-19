<%@tag description="panel with login and register buttons" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type='text/javascript' src='<c:url value="/static/js/bp.js"/>'></script>

<fmt:setLocale value="${userService.locale}" scope="application"/>
<fmt:bundle basename="i18n.text">
    <div class="btn-group" id="logins">
        <form method="post" action="login">
            <input type="text" name="username" placeholder="<fmt:message key="default.username"/>" required>
            <input type="password" name="password" placeholder="<fmt:message key="default.password"/>" required>
            <button class="btn btn-default" type="submit"><fmt:message key="default.dologin"/></button>
            <button type="button" class="btn btn-primary btn-md" data-toggle="modal" data-target="#modalRegForm">
                <fmt:message key="default.doregister"/></button>
        </form>
    </div>

    <div class="modal fade" id="modalRegForm" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" align="center"><fmt:message key="default.registration"/></h4>
                </div>
                <div class="modal-body">
                    <form id="registration-form" method="post" action="register">
                        <div class="input-group">
                            <div class="msg-error">${userService.errorMap.firstname_msg}</div>
                            <input type="text" class="form-control" id="firstname" name="firstname"
                                   value="${userService.errorMap.firstname}"
                                   placeholder="<fmt:message key="default.firstname"/>" required>

                            <div class="msg-error">${userService.errorMap.lastname_msg}</div>
                            <input type="text" class="form-control" id="lastname" name="lastname"
                                   value="${userService.errorMap.lastname}"
                                   placeholder="<fmt:message key="default.lastname"/>" required>

                            <div class="msg-error">${userService.errorMap.dob_msg}</div>
                            <input type="date" class="form-control" id="dob" name="dob"
                                   value="${userService.errorMap.dob}"
                                   placeholder="<fmt:message key="default.dob"/>" required>

                            <div class="msg-error">${userService.errorMap.username_msg}</div>
                            <input type="text" class="form-control" id="username" name="username"
                                   value="${userService.errorMap.username}"

                                   placeholder="<fmt:message key="default.username"/>" required>
                            <input type="password" class="form-control" name="password"
                                   placeholder="<fmt:message key="default.password"/>" required
                                   title="<fmt:message key="ttip.password8"/>" id="pass">

                            <div class="msg-error">${userService.errorMap.password_msg}</div>
                            <input type="password" class="form-control" name="password-repeat" id="pass-repeat"
                                   placeholder="<fmt:message key="default.password-repeat"/>" required
                                   title="<fmt:message key="ttip.password8"/>">

                            <div class="msg-error" id="msg">${userService.errorMap.email_msg}</div>
                            <input type="text" class="form-control" id="email_r" name="email"
                                   value="${userService.errorMap.email}"
                                   placeholder="<fmt:message key="default.email"/>" required>
                        </div>
                        <button type="submit" class="btn btn-primary"><fmt:message key="default.doregister"/></button>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message
                            key="default.cancel"/></button>
                </div>
            </div>
        </div>
    </div>
</fmt:bundle>