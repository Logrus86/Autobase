<%--suppress ALL --%>
<%@tag description="manufacturers edit forms admin" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="manufacturers" align="center">
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
                        <button class="btn btn-primary" name="save" value="${manufacturer.id}" type="submit">
                            <fmt:message key="default.save"/></button>
                    </td>
                    <td>
                        <button class="btn btn-danger" name="delete" value="${manufacturer.id}" type="submit">
                            <fmt:message key="default.delete"/></button>
                    </td>
                </form>
            </tr>
        </c:forEach>
    </table>
    <div align="center">
        <button class="btn btn-success" name="add-manufacturer" type="button" data-toggle="modal"
                data-target="#modalCreateManufacturer"><fmt:message key="default.add"/></button>
    </div>
</div>
<div class="modal fade" id="modalCreateManufacturer" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" align="center"><fmt:message key="default.add"/> <fmt:message
                        key="default.manufacturer'"/></h4>
            </div>
            <div class="msg-error" id="msg_mn">${create_error}</div>
            <div class="modal-body">
                <form id="create-color-form" class="createForm" method="post" action="create_manufacturer">
                    <div class="input-group">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.value"/></span>
                                <input required type="text" class="form-control" id="value_mn" name="value"
                                       value=${value}>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <button type="submit" class="btn btn-success"><fmt:message key="default.add"/></button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message
                        key="default.cancel"/></button>
            </div>
        </div>
    </div>
</div>