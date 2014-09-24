<%@tag description="search result form" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:bundle basename="i18n.text">
    <div>
        result:<br>
            ${search_result}<br>
        <c:forEach items="${vehicleList}" var="vehicle">
            ${vehicle.id} ${vehicle} <br>
        </c:forEach>
    </div>
</fmt:bundle>