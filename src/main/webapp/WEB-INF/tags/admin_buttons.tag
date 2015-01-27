<%@tag description="button group admin edit" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div align="center">
    <div class="btn-group" id="btn-group-admin-edit">
        <a id="bt_orders" class="btn btn-default" href='<c:url value="/do/admin_orders"/>'><fmt:message
                key="default.orders"/></a>
        <a id="bt_users" class="btn btn-default" href='<c:url value="/do/admin_users"/>'><fmt:message
                key="default.users"/></a>
        <a id="bt_cars" class="btn btn-default" href='<c:url value="/do/admin_cars"/>'><fmt:message
                key="default.cars"/></a>
        <a id="bt_buses" class="btn btn-default" href='<c:url value="/do/admin_buses"/>'><fmt:message
                key="default.buses"/></a>
        <a id="bt_trucks" class="btn btn-default" href='<c:url value="/do/admin_trucks"/>'><fmt:message
                key="default.trucks"/></a>
        <a id="bt_colors" class="btn btn-default" href='<c:url value="/do/admin_colors"/>'><fmt:message
                key="default.colors"/></a>
        <a id="bt_models" class="btn btn-default" href='<c:url value="/do/admin_models"/>'><fmt:message
                key="default.models"/></a>
        <a id="bt_manufacturers" class="btn btn-default" href='<c:url value="/do/admin_manufacturers"/>'><fmt:message
                key="default.manufacturers"/></a>
    </div>
</div>