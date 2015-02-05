<%@tag description="models edit forms admin" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="models" align="center">
    <table>
        <div class="msg-error">${modelService.errorMap.update_err}</div>
        <tr align="center">
            <td>№</td>
            <td><fmt:message key="default.value"/></td>
        </tr>
        <c:forEach items="${models}" var="model" varStatus="i">
            <tr>
                <form method="post" action="change_model">
                    <td><input id="N" type="number" class="form-control" value=${i.count} readonly></td>
                    <td><input required type="text" class="form-control" name="value" value=${model.value}></td>
                    <td>
                        <button class="btn btn-primary" name="save" value="${model.id}" type="submit"><fmt:message
                                key="default.save"/></button>
                    </td>
                    <td>
                        <button class="btn btn-danger" name="delete" value="${model.id}" type="submit"><fmt:message
                                key="default.delete"/></button>
                    </td>
                </form>
            </tr>
        </c:forEach>
    </table>
    <div align="center">
        <button class="btn btn-success" name="add-model" type="button" data-toggle="modal"
                data-target="#modalCreateModel">
            <fmt:message key="default.add"/>
        </button>
    </div>
</div>
<div class="modal fade" id="modalCreateModel" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" align="center"><fmt:message key="default.add"/> <fmt:message
                        key="default.model"/></h4>
            </div>
            <div class="msg-error" id="msg_md">${modelService.errorMap.create_err}</div>
            <div class="modal-body">
                <form id="create-color-form" class="createForm" method="post" action="create_model">
                    <div class="input-group">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.value"/></span>
                                <input required type="text" class="form-control" id="value_md" name="value"
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