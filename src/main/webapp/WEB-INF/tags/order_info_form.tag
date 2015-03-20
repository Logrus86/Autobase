<%@tag description="registration info form" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div align="center">
    <form id="order_info">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title"><fmt:message key="order.congrats">
                    <fmt:param value="${user.firstname}"/>
                    <fmt:param value="${order.id}"/>
                </fmt:message></h3>
            </div>
            <div class="panel-body">
                <div class="input-group">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="order.dateOrdered"/></span>
                                <input type="text" class="form-control" value="${order.dateOrdered}" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="order.dateStart"/></span>
                                <input type="text" class="form-control" value="${order.dateStart}" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="order.dateEnd"/></span>
                                <input type="text" class="form-control" value="${order.dateEnd}" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="order.length"/></span>
                                <input type="text" class="form-control"
                                       value="${order.dayCount} <fmt:message key="order.days"/>" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="order.vehicle"/></span>
                                <input type="text" class="form-control"
                                       value="${vehicle.manufacturer.value} ${vehicle.model.value}" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="order.charged"/></span>
                                <input type="text" class="form-control"
                                       value="${order.sum} <fmt:message key="default.currency"/>" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="order.currBalance"/></span>
                                <input type="text" class="form-control"
                                       value="${user.balance} <fmt:message key="default.currency"/>" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <fmt:message key="order.managers"/>
                        </div>
                    </div>
                    <a class="btn btn-primary" href='<c:url value="/"/>'><fmt:message key="default.backto"/>
                        autobase.com</a>
                </div>
            </div>
        </div>
    </form>
</div>