<%@tag description="users edit forms admin" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="users">
    <table>
        <div class="msg-error">${user_change_error}</div>
        <tr align="center">
            <td>№</td>
            <td><fmt:message key="default.username"/></td>
            <td><fmt:message key="default.password"/></td>
            <td><fmt:message key="default.firstname"/></td>
            <td><fmt:message key="default.lastname"/></td>
            <td><fmt:message key="default.dob"/></td>
            <td><fmt:message key="default.email"/></td>
            <td><fmt:message key="default.role"/></td>
            <td><fmt:message key="default.balance"/>, <fmt:message key="default.currency"/></td>
        </tr>
        <c:forEach items="${userList}" var="user" varStatus="i">
                <form method="post" action="change_user"><tr>
                    <td><input id="N" type="number" class="form-control" value="${i.count}" readonly></td>
                    <td><input required type="text" class="form-control" name="username" value="${user.username}"></td>
                    <td><input required type="password" class="form-control" name="password" value="${user.password}"></td>
                    <td><input required type="text" class="form-control" name="firstname" value="${user.firstname}"></td>
                    <td><input required type="text" class="form-control" name="lastname" value="${user.lastname}"></td>
                    <td><input required type="date" class="form-control" name="dob" value="${user.dob}"></td>
                    <td><input required type="email" class="form-control" name="email" value="${user.email}"></td>
                    <td>
                        <select class="selectpicker show-menu-arrow" data-width="auto" name="role">
                            <c:forEach items="${roles}" var="role">
                                <option value="${role}" <c:if test="${user.role==role}">selected</c:if>>${role}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td><input type="number" class="form-control" name="balance"
                               <c:if test="${user.role!='CLIENT'}">readonly="" </c:if> value="${user.balance}">
                    </td>
                    <td>
                        <button class="btn btn-primary" name="save" value="${user.id}" type="submit">
                            <fmt:message key="default.save"/></button>
                    </td>
                    <td>
                        <button class="btn btn-danger" name="delete" value="${user.id}" type="submit">
                            <fmt:message key="default.delete"/></button>
                    </td>  </tr>
                </form>

        </c:forEach>
    </table>
    <div align="center">
        <button class="btn btn-success" name="add-user" type="button" data-toggle="modal"
                data-target="#modalCreateUser"> <fmt:message key="default.add"/></button>
    </div>
</div>

<div class="modal fade" id="modalCreateUser" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" align="center"><fmt:message key="default.add"/> <fmt:message key="default.user'"/></h4>
            </div>
            <div class="msg-error" id="msg_u">${create_error}</div>
            <div class="modal-body">
                <form id="create-user-form" class="createForm" method="post" action="create_user">
                    <div class="input-group">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.firstname"/></span>
                                <input type="text" class="form-control" id="firstname_u" name="firstname"
                                       value="${firstname}" placeholder="..." required>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.lastname"/></span>
                                <input type="text" class="form-control" id="lastname_u" name="lastname"
                                       value="${lastname}" placeholder="..." required>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.dob"/></span>
                                <input type="date" class="form-control" id="dob_u" value="${dob}" name="dob" required>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.username"/></span>
                                <input type="text" class="form-control" id="username_u" name="username"
                                       value="${username}" placeholder="..." required>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.password"/></span>
                                <input type="password" class="form-control" name="password" required value="">
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.password-repeat"/></span>
                                <input type="password" class="form-control" name="password-repeat" required>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.email"/></span>
                                <input type="email" class="form-control" id="email_u" name="email" value="${email}"
                                       placeholder="..." required>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.role"/></span>
                                <select class="selectpicker show-menu-arrow" data-width="auto" name="role">
                                    <c:forEach items="${roles}" var="role1">
                                        <option value="${role1}" <c:if test="${role1==role}">selected</c:if>>
                                            ${role1}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.balance"/></span>
                                <input type="number" id="balance_u" class="form-control" name="balance"
                                       value="${balance}" placeholder="...">
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <button type="submit" class="btn btn-success"><fmt:message key="default.add"/></button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="default.cancel"/></button>
            </div>
        </div>
    </div>
</div>