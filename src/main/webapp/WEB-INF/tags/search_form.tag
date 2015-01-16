<%@tag description="search form" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form method="get" id="search-form" action="search">
<input id="vhType" name="vhType" type="hidden" value="">
<div class="panel panel-primary">
<div class="panel-heading">
    <h3 class="panel-title"><fmt:message key="default.search"/></h3>
</div>
<div class="msg-error">${search_error}</div>
<div class="panel-body">
    <div class="input-group">
        <div class="bs-example bs-example-tabs">
            <ul id="tabVhTypes" class="nav nav-tabs" role="tablist">
                <li class=""><a href="#bus-tab" role="tab" data-toggle="tab"><fmt:message key="default.bus"/></a></li>
                <li class="active"><a href="#car-tab" role="tab" data-toggle="tab"><fmt:message key="default.car"/></a></li>
                <li class=""><a href="#truck-tab" role="tab" data-toggle="tab"><fmt:message key="default.truck"/></a></li>
            </ul>
            <div class="row">
                <div class="col-lg-12">
                    <div class="input-group">
                    <span class="input-group-addon"><input type="checkbox" id="isModel"> <fmt:message key="default.model"/></span>
                        <select class="selectpicker show-menu-arrow" data-width="auto" data-live-search="true" name="modelId" id="modelId">
                            <c:forEach items="${models}" var="model">
                                <option value="${model.id}">
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
                    <span class="input-group-addon"><input type="checkbox" id="isManuf"> <fmt:message key="default.manufacturer"/></span>
                        <select class="selectpicker show-menu-arrow" data-width="auto" data-live-search="true" name="manufId" id="manufId">
                            <c:forEach items="${manufacturers}" var="manufacturer">
                                <option value="${manufacturer.id}">
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
                    <span class="input-group-addon"><input type="checkbox" id="isColor"> <fmt:message key="default.color"/></span>
                        <select class="selectpicker show-menu-arrow" data-width="auto" data-live-search="true" name="colorId" id="colorId">
                            <c:forEach items="${colors}" var="color">
                                <option value="${color.id}">
                                    <c:if test="${userService.locale.language=='ru'}">${color.value_ru}</c:if>
                                    <c:if test="${userService.locale.language=='en'}">${color.value_en}</c:if>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="input-group">
                    <span class="input-group-addon"><input type="checkbox" id="isFuel"> <fmt:message  key="default.fuel"/></span>
                        <select class="selectpicker show-menu-arrow" data-width="auto" name="fuel" id="fuel">
                            <c:forEach items="${fuelTypes}" var="fuel">
                                <option value="${fuel}">
                                    <fmt:message key="default.${fuel}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="input-group">
                        <span class="input-group-addon"><input type="checkbox" id="isMileage"> <fmt:message key="default.mileage-max"/></span>
                        <input type="number" min="0" max="1000" class="form-control" name="mileage" id="mileage" placeholder="..." value="50">
                        <span class="input-group-addon"> <fmt:message key="default.kilometers"/></span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="input-group">
                        <span class="input-group-addon"><input type="checkbox" id="isNotOlder"> <fmt:message key="default.not-older"/></span>
                        <input type="number" min="1980" max="2014" class="form-control" name="notOlder" id="notOlder" placeholder="..." value="1990">
                        <span class="input-group-addon"> <fmt:message key="default.of-year-prod"/></span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="input-group">
                    <span class="input-group-addon"><input type="checkbox" id="isRent"> <fmt:message key="default.not-pricey"/></span>
                        <input type="number" min="1000" max="100000" class="form-control" name="rent" id="rent" placeholder="..." value="10000">
                        <span class="input-group-addon"> <fmt:message key="default.currency"/></span>
                    </div>
                </div>
            </div>
            <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade" id="bus-tab">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><input type="checkbox" id="isPassNbus">
                                    <fmt:message key="default.passengerSeatsNumber"/>
                                </span>
                                <input type="number" min="0" max="200" class="form-control" name="passNbus" id="passNbus" value="20">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><input type="checkbox" id="isStandN">
                                    <fmt:message key="default.standingPlacesNumber"/>
                                </span>
                                <input type="number" min="0" max="200" class="form-control" name="standN" id="standN" value="20">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><input type="checkbox" id="isDoorsBus">
                                    <fmt:message key="default.doorsNumber"/>
                                </span>
                                <select class="selectpicker show-menu-arrow" data-width="auto" name="doorsBus" id="doorsBus">
                                    <c:forEach var="i" begin="2" end="5">
                                        <option>${i}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade active in" id="car-tab">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><input type="checkbox" id="isPassNcar">
                                    <fmt:message key="default.passengerSeatsNumber"/>
                                </span>
                                <select class="selectpicker show-menu-arrow" data-width="auto" name="passNcar" id="passNcar">
                                    <c:forEach var="i" begin="1" end="7">
                                        <option>${i}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><input type="checkbox" id="isDoorsCar">
                                    <fmt:message key="default.doorsNumber"/>
                                </span>
                                <select class="selectpicker show-menu-arrow" data-width="auto" name="doorsCar" id="doorsCar">
                                    <c:forEach var="i" begin="2" end="5">
                                        <option>${i}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><input type="checkbox" name="condit"></span>
                                <span class="form-control"><fmt:message key="default.conditioner"/></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="truck-tab">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><input type="checkbox" id="isPayload">
                                    <fmt:message key="default.maxPayload"/>
                                </span>
                                <input type="number" class="form-control" placeholder="..." value="10" min="0" max="1000" name="payload" id="payload">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><input type="checkbox" name="enclosed"></span>
                                <span class="form-control"><fmt:message key="default.enclosed"/></span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><input type="checkbox" name="tipper"></span>
                                <span class="form-control"><fmt:message key="default.tipper"/></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <button type="submit" class="btn btn-primary"  onclick="prepareGetRequest()"><fmt:message key="default.dosearch"/></button>
    </div>
</div>
</div>
</form>
