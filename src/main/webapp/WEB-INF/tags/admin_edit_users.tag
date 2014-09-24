<%--suppress ALL --%>
<%@tag description="users edit forms admin" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:bundle basename="i18n.text">
    <div id="users">
        <table>
            <div class="msg-error">${user_change_error}</div>
            <tr align="center">
                <td>№</td>
                <td><fmt:message key="default.firstname"/></td>
                <td><fmt:message key="default.lastname"/></td>
                <td><fmt:message key="default.dob"/></td>
                <td><fmt:message key="default.username"/></td>
                <td><fmt:message key="default.password"/></td>
                <td><fmt:message key="default.email"/></td>
                <td><fmt:message key="default.role"/></td>
                <td><fmt:message key="default.balance"/></td>
            </tr>
            <c:forEach items="${userList}" var="user" varStatus="i">
                <tr>
                    <form method="post" action="change_user">
                        <td><input id="N" type="number" class="form-control" value=${i.count} readonly></td>
                        <td><input type="text" class="form-control" name="firstname" value=${user.firstname}></td>
                        <td><input type="text" class="form-control" name="lastname" value=${user.lastname}></td>
                        <td><input type="date" class="form-control" name="dob" value=${user.dob}></td>
                        <td><input required type="text" class="form-control" name="username" value=${user.username}></td>
                        <td><input required type="password" class="form-control" name="password" value=${user.password}></td>
                        <td><input type="email" class="form-control" name="email" value=${user.email}></td>
                        <td>
                            <select class="selectpicker show-menu-arrow" data-width="auto" name="role">
                                <option value="ADMIN" <c:if test="${user.role=='ADMIN'}">selected</c:if>>ADMIN</option>
                                <option value="CLIENT" <c:if test="${user.role=='CLIENT'}">selected</c:if>>CLIENT</option>
                                <option value="DRIVER" <c:if test="${user.role=='DRIVER'}">selected</c:if>>DRIVER</option>
                            </select>
                        </td>
                        <td><input type="number" class="form-control" name="balance" value=${user.balance}></td>
                        <td><button class="btn btn-primary" name="save" value="${user.id}" type="submit"><fmt:message key="default.save"/></button></td>
                        <td><button class="btn btn-danger" name="delete" value="${user.id}" type="submit"><fmt:message key="default.delete"/></button></td>
                    </form>
                </tr>
            </c:forEach>
            <tr>
                <td><button class="btn btn-success" name="add-user" type="button"><fmt:message key="default.add"/></button></td>
            </tr>
        </table>
    </div>
</fmt:bundle>