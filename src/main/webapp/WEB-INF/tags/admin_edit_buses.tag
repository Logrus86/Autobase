<%@tag description="buses edit forms admin" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="buses">
    <table>
        <div class="msg-error">${vh_change_error}</div>
        <tr align="center">
            <td>№</td>
            <td><fmt:message key="default.operable"/></td>
            <td><fmt:message key="default.model"/></td>
            <td><fmt:message key="default.manufacturer"/></td>
            <td><fmt:message key="default.year_prod"/></td>
            <td><fmt:message key="default.color"/></td>
            <td><fmt:message key="default.fuel"/></td>
            <td><fmt:message key="default.mileage"/></td>
            <td><fmt:message key="default.rentPrice"/></td>
            <td><fmt:message key="default.driver"/></td>
            <td><fmt:message key="default.doorsN"/></td>
            <td><fmt:message key="default.passN"/></td>
            <td><fmt:message key="default.standN"/></td>
        </tr>
        <c:forEach items="${busList}" var="vehicle" varStatus="i">
            <tr>
                <form method="post" action="change_vehicle">
                    <td><input id="N" type="number" class="form-control" value="${i.count}" readonly></td>
                    <td><input type="checkbox" class="form-control" name="operable" <c:if test="${vehicle.operable}">checked</c:if>></td>
                    <td>
                        <select class="selectpicker show-menu-arrow" data-width="110" data-live-search="true" name="model_id">
                            <c:forEach items="${models}" var="model">
                                <option <c:if test="${vehicle.model.id==model.id}">selected</c:if> value="${model.id}">
                                    ${model.value}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select class="selectpicker show-menu-arrow" data-width="110" data-live-search="true" name="manufacturer_id">
                            <c:forEach items="${manufacturers}" var="manufacturer">
                                <option <c:if test="${vehicle.manufacturer.id==manufacturer.id}">selected</c:if> value="${manufacturer.id}">
                                    ${manufacturer.value}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                    <td id="yearA"><input type="number" min="1980" max="2014" class="form-control" name="productionYear" data-width="80"
                               value="${vehicle.productionYear}"></td>
                    <td>
                        <select class="selectpicker show-menu-arrow" data-width="120" data-live-search="true" name="color_id">
                            <c:forEach items="${colors}" var="color">
                                <option <c:if test="${vehicle.color.id==color.id}">selected</c:if> value="${color.id}">
                                    <c:if test="${locale.language=='ru'}">${color.value_ru}</c:if>
                                    <c:if test="${locale.language=='en'}">${color.value_en}</c:if>
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select class="selectpicker show-menu-arrow" data-width="auto" name="fuelType">
                            <c:forEach items="${fuelTypes}" var="fuel">
                                <option value="${fuel}" <c:if test="${vehicle.fuelType==fuel}">selected</c:if>>
                                    <fmt:message key="default.${fuel}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                    <td id="mileageA"><input type="number" min="0" max="1000" class="form-control" name="mileage" value="${vehicle.mileage}" data-width="70"></td>
                    <td><input id="rentA" type="number" min="1000" max="100000" class="form-control" name="rentPrice" value="${vehicle.rentPrice}"></td>
                    <td><select class="selectpicker show-menu-arrow" data-width="160" data-live-search="true" name="driverId">
                        <c:forEach items="${userList}" var="user">
                            <c:if test="${user.role=='DRIVER'}">
                                <option <c:if test="${vehicle.driverId==user.id}">selected</c:if> value="${user.id}">
                                    ${user.lastname} ${user.firstname}
                                </option>
                            </c:if>
                        </c:forEach>
                    </select></td>
                    <td><select class="selectpicker show-menu-arrow" data-width="auto" name="doorsNumber">
                        <c:forEach var="i" begin="2" end="5">
                            <option <c:if test="${vehicle.doorsNumber==i}">selected</c:if>>
                                ${i}
                            </option>
                        </c:forEach>
                    </select></td>
                    <td><input id="passNa" type="number" class="form-control" name="passengerSeatsNumber" value="${vehicle.passengerSeatsNumber}"></td>
                    <td><input id="standNa" type="number" class="form-control" name="standingPlacesNumber" value="${vehicle.standingPlacesNumber}"></td>
                    <td><button class="btn btn-primary" name="save" value="${vehicle.id}" type="submit">
                            <fmt:message key="default.save"/>
                        </button>
                    </td>
                    <td><button class="btn btn-danger" name="delete" value="${vehicle.id}" type="submit">
                            <fmt:message key="default.delete"/>
                        </button>
                    </td>
                </form>
            </tr>
        </c:forEach>
    </table>
    <div align="center">
        <button class="btn btn-success" name="add-bus" type="button" data-toggle="modal" data-target="#modalCreateBus">
            <fmt:message key="default.add"/>
        </button>
    </div>
</div>

<div class="modal fade" id="modalCreateBus" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" align="center"><fmt:message key="default.add"/> <fmt:message key="default.bus"/></h4>
            </div>
            <div class="msg-error" id="msg_b">${create_error}</div>
            <div class="modal-body">
                <form id="create-bus-form" class="createForm" method="post" action="create_bus">
                    <div class="input-group">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.model"/></span>
                                <select class="selectpicker show-menu-arrow" data-width="auto" data-live-search="true" name="model_id" id="model">
                                    <c:forEach items="${models}" var="model">
                                        <option <c:if test="${model_id==model.id}">selected</c:if> value="${model.id}">
                                            ${model.value}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.manufacturer"/></span>
                                <select class="selectpicker show-menu-arrow" data-width="auto" data-live-search="true" name="manufacturer_id">
                                    <c:forEach items="${manufacturers}" var="manufacturer">
                                        <option <c:if test="${manufacturer_id==manufacturer.id}">selected</c:if>
                                            value="${manufacturer.id}"> ${manufacturer.value}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.year_prod"/></span>
                                <input type="number" class="form-control" id="year_prod_b" name="year_prod" min="1980" max="2014"
                                       value="${year_prod}" placeholder="..." required>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.color"/></span>
                                <select class="selectpicker show-menu-arrow" data-width="auto" data-live-search="true" name="color_id">
                                    <c:forEach items="${colors}" var="color">
                                        <option value="${color.id}" <c:if test="${color_id==color.id}">selected</c:if>>
                                            <c:if test="${locale.language=='ru'}">${color.value_ru}</c:if>
                                            <c:if test="${locale.language=='en'}">${color.value_en}</c:if>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.fuel"/></span>
                                <select class="selectpicker show-menu-arrow" data-width="auto" name="fuelType">
                                    <c:forEach items="${fuelTypes}" var="fuel">
                                        <option <c:if test="${fuelType==fuel}">selected</c:if> value="${fuel}">
                                            <fmt:message key="default.${fuel}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <fmt:message key="default.mileage"/>, <fmt:message key="default.kilometers"/>
                                </span>
                                <input type="number" class="form-control" id="mileage_b" min="0" max="1000" value="${mileage}"
                                       name="mileage" placeholder="..." required>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <fmt:message key="default.rentPrice"/>, <fmt:message key="default.currency"/>
                                </span>
                                <input type="number" class="form-control" id="rentPrice_b" value="${rentPrice}"
                                       name="rentPrice" min="1000" max="100000" placeholder="..." required>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.driver"/></span>
                                <select class="selectpicker show-menu-arrow" data-width="80" data-live-search="true" name="driverId">
                                    <c:forEach items="${userList}" var="user">
                                        <c:if test="${user.role=='DRIVER'}">
                                            <option <c:if test="${user.id==driverId}">selected</c:if> value="${user.id}">
                                                ${user.lastname} ${user.firstname}
                                            </option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.doorsNumber"/></span>
                                <select class="selectpicker show-menu-arrow" data-width="auto" name="doorsN">
                                    <c:forEach var="i" begin="2" end="5">
                                        <option <c:if test="${doorsN=='i'}">selected</c:if>>${i}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.passengerSeatsNumber"/></span>
                                <input type="number" class="form-control" id="passN_b" value="${passN}" min="0" max="200"
                                       name="passN" placeholder="..." required>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.standingPlacesNumber"/></span>
                                <input type="number" class="form-control" id="standN_b" value="${standN}" min="0" max="200"
                                       name="standN" placeholder="..." required min="0" max="200">
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