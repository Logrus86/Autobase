<%@tag description="colors edit forms admin" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="colors" align="center">
    <table>
        <div class="msg-error">${color_change_error}</div>
        <tr align="center">
            <td>№</td>
            <c:if test="${userService.locale.language=='ru'}">
                <td><fmt:message key="default.valueRu"/></td>
                <td><fmt:message key="default.valueEn"/></td>
            </c:if>
            <c:if test="${userService.locale.language=='en'}">
                <td><fmt:message key="default.valueEn"/></td>
                <td><fmt:message key="default.valueRu"/></td>
            </c:if>
        </tr>
        <c:forEach items="${colors}" var="color" varStatus="i">
            <tr>
                <form method="post" action="change_color">
                    <td><input id="N" type="number" class="form-control" value=${i.count} readonly></td>
                    <c:if test="${userService.locale.language=='ru'}">
                        <td><input required type="text" class="form-control" name="value_ru" value=${color.value_ru}></td>
                        <td><input required type="text" class="form-control" name="value_en" value=${color.value_en}></td>
                    </c:if>
                    <c:if test="${userService.locale.language=='en'}">
                        <td><input required type="text" class="form-control" name="value_en" value=${color.value_en}></td>
                        <td><input required type="text" class="form-control" name="value_ru" value=${color.value_ru}></td>
                    </c:if>
                    <td><button class="btn btn-primary" name="save" value="${color.id}" type="submit"><fmt:message key="default.save"/></button></td>
                    <td><button class="btn btn-danger" name="delete" value="${color.id}" type="submit"><fmt:message key="default.delete"/></button></td>
                </form>
            </tr>
        </c:forEach>
    </table>
    <div align="center">
        <button class="btn btn-success" name="add-color" type="button" data-toggle="modal" data-target="#modalCreateColor">
            <fmt:message key="default.add"/>
        </button>
    </div>
</div>

<div class="modal fade" id="modalCreateColor" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" align="center"><fmt:message key="default.add"/> <fmt:message key="default.color"/></h4>
            </div>
            <div class="msg-error" id="msg_cl">${create_error}</div>
            <div class="modal-body">
                <form id="create-color-form" class="createForm" method="post" action="create_color">
                    <div class="input-group">
                        <c:if test="${userService.locale.language=='ru'}">
                            <div class="col-lg-12">
                                <div class="msg-error">${colorService.errorMap.value_ru_msg}</div>
                                <div class="input-group">
                                    <span class="input-group-addon"><fmt:message key="default.valueRu"/></span>
                                    <input required type="text" class="form-control" id="value_ru" name="valueRu"
                                           value=${colorService.errorMap.value_ru}>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="msg-error">${colorService.errorMap.value_en_msg}</div>
                                <div class="input-group">
                                    <span class="input-group-addon"><fmt:message key="default.valueEn"/></span>
                                    <input required type="text" class="form-control" id="value_en" name="valueEn"
                                           value=${colorService.errorMap.value_en}>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${userService.locale.language=='en'}">
                            <div class="col-lg-12">
                                <div class="msg-error">${colorService.errorMap.value_en_msg}</div>
                                <div class="input-group">
                                    <span class="input-group-addon"><fmt:message key="default.valueEn"/></span>
                                    <input required type="text" class="form-control" id="value_en" name="valueEn"
                                           value=${colorService.errorMap.value_en}>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="msg-error">${colorService.errorMap.value_ru_msg}</div>
                                <div class="input-group">
                                    <span class="input-group-addon"><fmt:message key="default.valueRu"/></span>
                                    <input required type="text" class="form-control" id="value_ru" name="valueRu"
                                           value=${colorService.errorMap.value_ru}>
                                </div>
                            </div>
                        </c:if>
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