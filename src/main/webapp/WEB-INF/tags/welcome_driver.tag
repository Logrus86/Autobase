<%@tag description="welcome driver bar" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="btn-group" id="userbar" align="right">
    <p>
        <fmt:message key="default.welcome"/> ${sessionState.sessionUser.firstname}! <fmt:message
            key="default.how-are-you"/>
        <button type="button" class="btn btn-default" onclick="location.href='<c:url value="/do/quit"/>'"><fmt:message
                key="default.doexit"/>
        </button>
    </p>
</div>