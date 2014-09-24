<%--suppress ALL --%>
<%@tag description="colors edit forms admin" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:bundle basename="i18n.text">
    <div id="colors">
        <table>
            <div class="msg-error">${color_change_error}</div>
            <tr align="center">
                <td>№</td>
                <td><fmt:message key="default.valueEn"/></td>
                <td><fmt:message key="default.valueRu"/></td>
            </tr>
            <c:forEach items="${colorList}" var="color" varStatus="i">
                <tr>
                    <form method="post" action="change_color">
                        <td><input id="N" type="number" class="form-control" value=${i.count} readonly></td>
                        <td><input required type="text" class="form-control" name="valueEn" value=${color.valueEn}></td>
                        <td><input required type="text" class="form-control" name="valueRu" value=${color.valueRu}></td>
                        <td>
                            <button class="btn btn-primary" name="save" value="${color.id}" type="submit"><fmt:message key="default.save"/></button>
                        </td>
                        <td>
                            <button class="btn btn-danger" name="delete" value="${color.id}" type="submit"><fmt:message key="default.delete"/></button>
                        </td>
                    </form>
                </tr>
            </c:forEach>
            <tr>
                <td><button class="btn btn-success" name="add-color" type="button"><fmt:message key="default.add"/></button></td>
            </tr>
        </table>
    </div>
</fmt:bundle>