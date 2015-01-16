<%@tag description="registration info form" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="reg_info">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title"><fmt:message key="default.registered"/></h3>
        </div>
        <div class="panel-body">
            <div class="input-group">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group">
                            <span class="input-group-addon"><fmt:message key="default.firstname"/>:</span>
                            <input type="text" class="form-control" value="${userService.sessionUser.firstname}" readonly>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group">
                            <span class="input-group-addon"><fmt:message key="default.lastname"/>:</span>
                            <input type="text" class="form-control" value="${userService.sessionUser.lastname}" readonly>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group">
                            <span class="input-group-addon"><fmt:message key="default.dob"/>:</span>
                            <input type="text" class="form-control" value="${userService.sessionUser.dob}" readonly>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group">
                            <span class="input-group-addon"><fmt:message key="default.username"/>:</span>
                            <input type="text" class="form-control" value="${userService.sessionUser.username}" readonly>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group">
                            <span class="input-group-addon"><fmt:message key="default.password"/>:</span>
                            <input type="text" class="form-control" value="${userService.sessionUser.password}" readonly>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group">
                            <span class="input-group-addon"><fmt:message key="default.email"/>:</span>
                            <input type="text" class="form-control" value="${userService.sessionUser.email}" readonly>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group">
                            <span class="input-group-addon"><fmt:message key="default.balance"/>:</span>
                            <input type="text" class="form-control"
                                   value="${userService.sessionUser.balance} <fmt:message key="default.currency"/>" readonly>
                        </div>
                    </div>
                </div>
                <a class="btn btn-primary" href='<c:url value="/"/>'><fmt:message key="default.backto"/>
                    autobase.com</a>
            </div>
        </div>
    </div>
</form>