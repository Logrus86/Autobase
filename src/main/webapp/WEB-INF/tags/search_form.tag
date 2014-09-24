<%@tag description="search form" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:bundle basename="i18n.text">
<form method="get" id="search-form" action="search">
<div class="panel panel-primary">
<div class="panel-heading">
    <h3 class="panel-title"><fmt:message key="default.search"/></h3>
</div>
<div class="msg-error">${search_error}</div>
<div class="panel-body">
<div class="input-group">
<div class="bs-example bs-example-tabs">
    <ul id="myTab" class="nav nav-tabs" role="tablist">
        <li class=""><a href="#bus-tab" role="tab" data-toggle="tab"><fmt:message key="default.bus"/></a></li>
        <li class="active"><a href="#car-tab" role="tab" data-toggle="tab"><fmt:message key="default.car"/></a></li>
        <li class=""><a href="#truck-tab" role="tab" data-toggle="tab"><fmt:message key="default.truck"/></a></li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade" id="bus-tab">
            <div class="row">
                <div class="col-lg-12">
                    <div class="input-group">
                        <span class="input-group-addon"><input type="checkbox" name="isPASSENGER_SEATS_NUMBER_BUS">
                            <fmt:message key="default.passengerSeatsNumber"/></span>
                        <input type="number" class="form-control" name="PASSENGER_SEATS_NUMBER_BUS" value="20">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="input-group">
                        <span class="input-group-addon"><input type="checkbox" name="isSTANDING_PLACES_NUMBER">
                            <fmt:message key="default.standingPlacesNumber"/></span>
                        <input type="number" class="form-control" name="STANDING_PLACES_NUMBER" value="20">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="input-group">
                        <span class="input-group-addon"><input type="checkbox" name="isDOORS_NUMBER_BUS">
                            <fmt:message key="default.doorsNumber"/></span>
                        <select class="selectpicker show-menu-arrow" data-width="auto" name="DOORS_NUMBER_BUS">
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>
        <div class="tab-pane fade active in" id="car-tab">
            <div class="row">
                <div class="col-lg-12">
                    <div class="input-group">
                        <span class="input-group-addon"><input type="checkbox" name="isPASSENGER_SEATS_NUMBER_CAR">
                            <fmt:message key="default.passengerSeatsNumber"/></span>
                        <select class="selectpicker show-menu-arrow" data-width="auto" name="PASSENGER_SEATS_NUMBER_CAR">
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                            <option>6</option>
                            <option>7</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="input-group">
                        <span class="input-group-addon"><input type="checkbox" name="isDOORS_NUMBER_CAR">
                            <fmt:message  key="default.doorsNumber"/></span>
                        <select class="selectpicker show-menu-arrow" data-width="auto" name="DOORS_NUMBER_CAR">
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="input-group">
                        <span class="input-group-addon"><input type="checkbox" name="isCONDITIONER"></span>
                        <span class="form-control"><fmt:message key="default.conditioner"/></span>
                    </div>
                </div>
            </div>
        </div>
        <div class="tab-pane fade" id="truck-tab">
            <div class="row">
                <div class="col-lg-12">
                    <div class="input-group">
                        <span class="input-group-addon"><input type="checkbox" name="isMAX_PAYLOAD">
                            <fmt:message key="default.maxPayload"/></span>
                        <input type="number" class="form-control" placeholder="..." value="10" name="MAX_PAYLOAD">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="input-group">
                        <span class="input-group-addon"><input type="checkbox" name="isENCLOSED"></span>
                        <span class="form-control"><fmt:message key="default.enclosed"/></span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="input-group">
                        <span class="input-group-addon"><input type="checkbox" name="isTIPPER"></span>
                        <span class="form-control"><fmt:message key="default.tipper"/></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="input-group">
            <span class="input-group-addon"><input type="checkbox" name="isMODEL"> <fmt:message key="default.model"/></span>
            <select class="selectpicker show-menu-arrow" data-width="auto" data-live-search="true" name="MODEL">
                <c:forEach items="${modelList}" var="model">
                    <option>${model.value}</option>
                </c:forEach>
            </select>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="input-group">
            <span class="input-group-addon"><input type="checkbox" name="isMANUFACTURER"> <fmt:message key="default.manufacturer"/></span>
            <select class="selectpicker show-menu-arrow" data-width="auto" data-live-search="true"
                    name="MANUFACTURER">
                <c:forEach items="${manufacturerList}" var="manufacturer">
                    <option>${manufacturer.value}</option>
                </c:forEach>
            </select>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="input-group">
            <span class="input-group-addon"><input type="checkbox" name="isCOLOR"> <fmt:message key="default.color"/></span>
            <select class="selectpicker show-menu-arrow" data-width="auto" data-live-search="true"
                    name="COLOR">
                <c:forEach items="${colorList}" var="color">
                    <option value="${color.valueEn}">${color.valueRu}</option>
                </c:forEach>
            </select>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="input-group">
            <span class="input-group-addon"><input type="checkbox" name="isFUELTYPE"> <fmt:message key="default.fuel"/></span>
            <select class="selectpicker show-menu-arrow" data-width="auto" name="FUELTYPE">
                <option value="PETROL"><fmt:message key="default.petrol"/></option>
                <option value="DIESEL"><fmt:message key="default.diesel"/></option>
                <option value="GAS"><fmt:message key="default.gas"/></option>
                <option value="GAS-PETROL"><fmt:message key="default.gas-petrol"/></option>
                <option value="ELECTRICITY"><fmt:message key="default.electricity"/></option>
            </select>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="input-group">
            <span class="input-group-addon"><input type="checkbox" name="isMILEAGE"> <fmt:message key="default.mileage-max"/></span>
            <input type="number" class="form-control" name="MILEAGE" placeholder="..." value="50">
            <span class="input-group-addon"> <fmt:message key="default.kilometers"/></span>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="input-group">
            <span class="input-group-addon"><input type="checkbox" name="isNOTOLDER"> <fmt:message key="default.not-older"/></span>
            <input type="number" class="form-control" name="NOTOLDER" placeholder="..." value="1990">
            <span class="input-group-addon"> <fmt:message key="default.of-year-prod"/></span>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="input-group">
            <span class="input-group-addon"><input type="checkbox" name="isRENTPRICE"> <fmt:message key="default.not-pricey"/></span>
            <input type="number" class="form-control" name="RENTPRICE" placeholder="..." value="10000">
            <span class="input-group-addon"> <fmt:message key="default.currency"/></span>
        </div>
    </div>
</div>
<br>
<button type="submit" class="btn btn-primary"><fmt:message key="default.dosearch"/></button>
</div>
</div>
</div>
</form>
</fmt:bundle>