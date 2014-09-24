<%--suppress ALL --%>
<%@tag description="manufacturers edit forms admin" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:bundle basename="i18n.text">
    <div id="manufacturers">
        <table>
            <div class="msg-error">${manuf_change_error}</div>
            <tr align="center">
                <td>№</td>
                <td><fmt:message key="default.value"/></td>
            </tr>
            <c:forEach items="${manufacturerList}" var="manufacturer" varStatus="i">
                <tr>
                    <form method="post" action="change_manufacturer">
                        <td><input id="N" type="number" class="form-control" value=${i.count} readonly></td>
                        <td><input required type="text" class="form-control" name="value" value=${manufacturer.value}></td>
                        <td>
                            <button class="btn btn-primary" name="save" value="${manufacturer.id}" type="submit"><fmt:message key="default.save"/></button>
                        </td>
                        <td>
                            <button class="btn btn-danger" name="delete" value="${manufacturer.id}" type="submit"><fmt:message key="default.delete"/></button>
                        </td>
                    </form>
                </tr>
            </c:forEach>
            <tr>
                <td><button class="btn btn-success" name="add-manufacturer" type="button"><fmt:message key="default.add"/></button></td>
            </tr>
        </table>
    </div>
</fmt:bundle>