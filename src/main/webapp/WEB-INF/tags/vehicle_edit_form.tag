<%@tag description="vehicle edit form" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form id="vehicle-form" method="post" action="change_vehicle">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title"><fmt:message key="default.vehicle-edit"/></h3>
        </div>
        <div class="msg-error">${vh_change_error}</div>
        <div class="panel-body">
            <div class="input-group">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group">
                            <span class="input-group-addon"><input type="checkbox" name="operable"
                                                                   <c:if test="${vehicle.operable}">checked</c:if>></span>
                            <span class="form-control"><fmt:message key="default.operable"/></span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group">
                            <span class="input-group-addon"><fmt:message key="default.model"/></span>
                            <select class="selectpicker show-menu-arrow" data-width="auto" data-live-search="true"
                                    name="model_id">
                                <c:forEach items="${modelList}" var="model">
                                    <option
                                            <c:if test="${vehicle.model.id==model.id}">selected</c:if>
                                            value="${model.id}">
                                            ${model.value}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group">
                            <span class="input-group-addon"><fmt:message key="default.manufacturer"/></span>
                            <select class="selectpicker show-menu-arrow" data-width="auto" data-live-search="true"
                                    name="manufacturer_id">
                                <c:forEach items="${manufacturerList}" var="manufacturer">
                                    <option
                                            <c:if test="${vehicle.manufacturer.id==manufacturer.id}">selected</c:if>
                                            value="${manufacturer.id}">
                                            ${manufacturer.value}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group">
                            <span class="input-group-addon"><fmt:message key="default.color"/></span>
                            <select class="selectpicker show-menu-arrow" data-width="auto"
                                    data-live-search="true" name="color_id">
                                <c:forEach items="${colorList}" var="color">
                                    <option
                                            <c:if test="${vehicle.color.id==color.id}">selected</c:if>
                                            value="${color.id}">
                                        <c:if test="${locale.language=='ru'}">${color.valueRu}</c:if>
                                        <c:if test="${locale.language=='en'}">${color.valueEn}</c:if>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group">
                            <span class="input-group-addon"><fmt:message key="default.fuel"/></span>
                            <select class="selectpicker show-menu-arrow" data-width="auto" name="fuelType">
                                <c:forEach items="${fuelTypes}" var="fuel">
                                    <option value="${fuel}" <c:if test="${vehicle.fuelType==fuel}">selected</c:if>>
                                        <fmt:message key="default.${fuel}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group">
                            <span class="input-group-addon"><fmt:message key="default.mileage"/></span>
                            <input type="number" class="form-control" name="mileage" value="${vehicle.mileage}">
                            <span class="input-group-addon"> <fmt:message key="default.kilometers"/></span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group">
                            <span class="input-group-addon"><fmt:message key="default.year_prod"/></span>
                            <input type="number" class="form-control" name="productionYear"
                                   value="${vehicle.productionYear}">
                        </div>
                    </div>
                </div>
                <c:if test="${vehicle.vehicleType=='Bus'}">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.passengerSeatsNumber"/></span>
                                <input type="number" class="form-control" name="passengerSeatsNumber"
                                       value="${vehicle.passengerSeatsNumber}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.standingPlacesNumber"/></span>
                                <input type="number" class="form-control" name="standingPlacesNumber"
                                       value="${vehicle.standingPlacesNumber}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.doorsNumber"/></span>
                                <select class="selectpicker show-menu-arrow" data-width="auto" name="doorsNumber">
                                    <option <c:if test="${vehicle.doorsNumber==2}">selected</c:if>>2</option>
                                    <option <c:if test="${vehicle.doorsNumber==3}">selected</c:if>>3</option>
                                    <option <c:if test="${vehicle.doorsNumber==4}">selected</c:if>>4</option>
                                    <option <c:if test="${vehicle.doorsNumber==5}">selected</c:if>>5</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${vehicle.vehicleType=='Car'}">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.passengerSeatsNumber"/></span>
                                <select class="selectpicker show-menu-arrow" data-width="auto"
                                        name="passengerSeatsNumber">
                                    <option <c:if test="${vehicle.passengerSeatsNumber==1}">selected</c:if>>1</option>
                                    <option <c:if test="${vehicle.passengerSeatsNumber==2}">selected</c:if>>2</option>
                                    <option <c:if test="${vehicle.passengerSeatsNumber==3}">selected</c:if>>3</option>
                                    <option <c:if test="${vehicle.passengerSeatsNumber==4}">selected</c:if>>4</option>
                                    <option <c:if test="${vehicle.passengerSeatsNumber==5}">selected</c:if>>5</option>
                                    <option <c:if test="${vehicle.passengerSeatsNumber==6}">selected</c:if>>6</option>
                                    <option <c:if test="${vehicle.passengerSeatsNumber==7}">selected</c:if>>7</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.doorsNumber"/></span>
                                <select class="selectpicker show-menu-arrow" data-width="auto" name="doorsNumber">
                                    <option <c:if test="${vehicle.doorsNumber==2}">selected</c:if>>2</option>
                                    <option <c:if test="${vehicle.doorsNumber==3}">selected</c:if>>3</option>
                                    <option <c:if test="${vehicle.doorsNumber==4}">selected</c:if>>4</option>
                                    <option <c:if test="${vehicle.doorsNumber==5}">selected</c:if>>5</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><input type="checkbox" name="withConditioner"
                                                                       <c:if test="${vehicle.withConditioner}">checked</c:if>></span>
                                <span class="form-control"><fmt:message key="default.conditioner"/></span>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${vehicle.vehicleType=='Truck'}">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.maxPayload"/></span>
                                <input type="number" class="form-control" value="${vehicle.maxPayload}"
                                       name="maxPayload">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><input type="checkbox" name="enclosed"
                                                                       <c:if test="${vehicle.enclosed}">checked</c:if>></span>
                                <span class="form-control"><fmt:message key="default.enclosed"/></span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><input type="checkbox" name="tipper"
                                                                       <c:if test="${vehicle.tipper}">checked</c:if>></span>
                                <span class="form-control"><fmt:message key="default.tipper"/></span>
                            </div>
                        </div>
                    </div>
                </c:if>
                <button type="submit" class="btn btn-primary"><fmt:message key="default.dosave"/></button>
            </div>
        </div>
    </div>
</form>