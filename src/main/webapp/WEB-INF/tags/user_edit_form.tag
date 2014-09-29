<%@tag description="user edit form" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form id="user-form" method="post" action="change_user">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title"><fmt:message key="default.profile-edit"/></h3>
        </div>
        <div class="msg-error">${user_change_error}</div>
        <div class="panel-body">
            <div class="input-group">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group">
                            <span class="input-group-addon"><fmt:message key="default.firstname"/></span>
                            <input type="text" class="form-control" name="firstname" value="${user.firstname}" required>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group">
                            <span class="input-group-addon"><fmt:message key="default.lastname"/></span>
                            <input type="text" class="form-control" name="lastname" value="${user.lastname}" required>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group">
                            <span class="input-group-addon"><fmt:message key="default.dob"/></span>
                            <input type="date" class="form-control" name="dob" value="${user.dob}" required>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group">
                            <span class="input-group-addon"><fmt:message key="default.username"/></span>
                            <input type="text" class="form-control" name="username" value="${user.username}" required>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group">
                            <span class="input-group-addon"><fmt:message key="default.password"/></span>
                            <input type="password" class="form-control" name="password" value="${user.password}" required
                                   title="<fmt:message key="ttip.password8"/>">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group">
                            <span class="input-group-addon"><fmt:message key="default.password"/></span>
                            <input type="password" class="form-control" name="password-repeat" value="${user.password}" required
                                   title="<fmt:message key="ttip.password8"/>">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group">
                            <span class="input-group-addon"><fmt:message key="default.email"/></span>
                            <input type="email" class="form-control" name="email" value="${user.email}" required>
                        </div>
                    </div>
                </div>
                <c:if test="${user.role=='CLIENT'}">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group">
                            <span class="input-group-addon"><fmt:message key="default.balance"/></span>
                            <input readonly type="number" class="form-control" name="balance" value="${user.balance}">
                            <span class="input-group-addon"><fmt:message key="default.currency"/></span>
                                <%--<span class="input-group-btn">
                                    <button class="btn btn-default" type="button"><fmt:message key="default.do-add-funds"/></button>
                                </span>--%>
                        </div>
                    </div>
                </div>
                </c:if>
                <button type="submit" class="btn btn-primary"><fmt:message key="default.dosave"/></button>
            </div>
        </div>
    </div>
</form>