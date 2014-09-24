<%@tag description="welcome client bar" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:bundle basename="i18n.text">
    <div class="btn-group" id="userbar" align="right">
        <p>
            <fmt:message key="default.welcome"/> <a title="<fmt:message key="default.to-cabinet"/>"
                                                    href='<c:url value="/do/cabinet"/>'>${user.username}</a>!
            <fmt:message key="default.at-balance"/> <a title="<fmt:message key="default.to-cabinet-and-add"/>"
                                                       href='<c:url value="/do/cabinet"/>'>${user.balance}
            <fmt:message key="default.currency"/></a>
            <button type="button" class="btn btn-default" onclick='location.href="/do/quit"'><fmt:message
                    key="default.doexit"/>
            </button>
        </p>
    </div>
</fmt:bundle>