<%@tag description="welcome client bar" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="btn-group" id="userbar" align="right">
    <p>
       <fmt:message key="default.welcome"/>
        <a title="<fmt:message key="default.to-cabinet"/>" href='<c:url value="/do/cabinet"/>'>${user.firstname}</a>!
       <fmt:message key="default.at-balance"/> ${user.balance} <fmt:message key="default.currency"/>
       <button type="button" class="btn btn-default" onclick="location.href='<c:url value="/do/quit"/>'"><fmt:message key="default.doexit"/>
       </button>
    </p>
</div>