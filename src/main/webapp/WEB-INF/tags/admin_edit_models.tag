<%--suppress ALL --%>
<%@tag description="models edit forms admin" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:bundle basename="i18n.text">
    <div id="models">
        <table>
            <div class="msg-error">${model_change_error}</div>
            <tr align="center">
                <td>№</td>
                <td><fmt:message key="default.value"/></td>
            </tr>
            <c:forEach items="${modelList}" var="model" varStatus="i">
                <tr>
                    <form method="post" action="change_model">
                        <td><input id="N" type="number" class="form-control" value=${i.count} readonly></td>
                        <td><input required type="text" class="form-control" name="value" value=${model.value}></td>
                        <td>
                            <button class="btn btn-primary" name="save" value="${model.id}" type="submit"><fmt:message key="default.save"/></button>
                        </td>
                        <td>
                            <button class="btn btn-danger" name="delete" value="${model.id}" type="submit"><fmt:message key="default.delete"/></button>
                        </td>
                    </form>
                </tr>
            </c:forEach>
            <tr>
                <td><button class="btn btn-success" name="add-model" type="button"><fmt:message key="default.add"/></button></td>
            </tr>
        </table>
    </div>
</fmt:bundle>