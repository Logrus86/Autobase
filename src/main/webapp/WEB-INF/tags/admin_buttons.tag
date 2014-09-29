<%@tag description="button group admin edit" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div align="center">
    <div class="btn-group" id="btn-group-admin-edit">
        <a class="btn btn-default" href='<c:url value="/"/>'><fmt:message key="default.users"/></a>
        <a class="btn btn-default" href='<c:url value="/do/admin-cars"/>'><fmt:message key="default.cars"/></a>
        <a class="btn btn-default" href='<c:url value="/do/admin-buses"/>'><fmt:message key="default.buses"/></a>
        <a class="btn btn-default" href='<c:url value="/do/admin-trucks"/>'><fmt:message key="default.trucks"/></a>
        <a class="btn btn-default" href='<c:url value="/do/admin-colors"/>'><fmt:message key="default.colors"/></a>
        <a class="btn btn-default" href='<c:url value="/do/admin-models"/>'><fmt:message key="default.models"/></a>
        <a class="btn btn-default" href='<c:url value="/do/admin-manufacturers"/>'><fmt:message
                key="default.manufacturers"/></a>
    </div>
</div>
