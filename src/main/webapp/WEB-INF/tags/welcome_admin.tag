<%@tag description="welcome admin bar" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="btn-group" id="admin_bar" align="right">
    <p>
        <fmt:message key="default.welcome"/> ${user.firstname}! <fmt:message key="default.hey-admin"/>
        <button type="button" class="btn btn-default" onclick="location.href='<c:url value="/do/quit"/>'"><fmt:message
                key="default.doexit"/>
        </button>
    </p>
</div>