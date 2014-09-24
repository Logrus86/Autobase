<%--suppress ALL --%>
<%@tag description="cars edit forms admin" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:bundle basename="i18n.text">
    <div id="vehicles">
        <table>
            <div class="msg-error">${vh_change_error}</div>
            <tr align="center">
                <td>№</td>
                <td><fmt:message key="default.operable"/></td>
                <td><fmt:message key="default.model"/></td>
                <td><fmt:message key="default.manufacturer"/></td>
                <td><fmt:message key="default.year-prod"/></td>
                <td><fmt:message key="default.color"/></td>
                <td><fmt:message key="default.fuel"/></td>
                <td><fmt:message key="default.mileage"/></td>
                <td><fmt:message key="default.rentPrice"/></td>
                <td><fmt:message key="default.driver"/></td>
                <td><fmt:message key="default.passN"/></td>
                <td><fmt:message key="default.doorsN"/></td>
                <td><fmt:message key="default.conditioner"/></td>
            </tr>
            <c:forEach items="${carList}" var="vehicle" varStatus="i">
                <tr>
                    <form method="post" action="change_vehicle">
                        <td><input id="N" type="number" class="form-control" value=${i.count} readonly></td>
                        <td><input type="checkbox" class="form-control" name="operable" <c:if test="${vehicle.operable}">checked</c:if>></td>
                        <td>
                            <select class="selectpicker show-menu-arrow" data-width="150px" data-live-search="true" name="model">
                                <c:forEach items="${modelList}" var="model">
                                    <option <c:if test="${vehicle.model==model.value}">selected</c:if>>${model.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select class="selectpicker show-menu-arrow" data-width="150px" data-live-search="true" name="manufacturer">
                                <c:forEach items="${manufacturerList}" var="manufacturer">
                                    <option <c:if test="${vehicle.manufacturer==manufacturer.value}">selected</c:if>>${manufacturer.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td><input type="number" class="form-control" name="productionYear" value="${vehicle.productionYear}"></td>
                        <td>
                            <select class="selectpicker show-menu-arrow" data-width="150px"
                                    data-live-search="true" name="color">
                                <c:forEach items="${colorList}" var="color">
                                    <option <c:if test="${vehicle.color==color.valueEn}">selected</c:if> value="${color.valueEn}">${color.valueRu}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select class="selectpicker show-menu-arrow" data-width="auto" name="fuelType">
                                <option value="PETROL" <c:if test="${vehicle.fuelType=='PETROL'}">selected</c:if>><fmt:message key="default.petrol"/></option>
                                <option value="DIESEL" <c:if test="${vehicle.fuelType=='DIESEL'}">selected</c:if>><fmt:message key="default.diesel"/></option>
                                <option value="GAS" <c:if test="${vehicle.fuelType=='GAS'}">selected</c:if>><fmt:message key="default.gas"/></option>
                                <option value="GAS_PETROL" <c:if test="${vehicle.fuelType=='GAS_PETROL'}">selected</c:if>><fmt:message key="default.gas-petrol"/></option>
                                <option value="ELECTRICITY" <c:if test="${vehicle.fuelType=='ELECTRICITY'}">selected</c:if>><fmt:message key="default.electricity"/></option>
                            </select>
                        </td>
                        <td><input type="number" class="form-control" name="mileage" value="${vehicle.mileage}"></td>
                        <td><input id="rent" type="number" class="form-control" name="rentPrice" value="${vehicle.rentPrice}"></td>
                        <td><select class="selectpicker show-menu-arrow" data-width="80" data-live-search="true" name="driverId">
                            <c:forEach items="${userList}" var="user">
                                <option <c:if test="${vehicle.driverId==user.id}">selected</c:if> value="${user.id}">${user.username}</option>
                            </c:forEach>
                        </select></td>
                        <td><select class="selectpicker show-menu-arrow" data-width="auto" name="doorsNumber">
                            <option <c:if test="${vehicle.doorsNumber==2}">selected</c:if>>2</option>
                            <option <c:if test="${vehicle.doorsNumber==3}">selected</c:if>>3</option>
                            <option <c:if test="${vehicle.doorsNumber==4}">selected</c:if>>4</option>
                            <option <c:if test="${vehicle.doorsNumber==5}">selected</c:if>>5</option>
                        </select></td>
                        <td><select class="selectpicker show-menu-arrow" data-width="auto" name="passengerSeatsNumber">
                            <option <c:if test="${vehicle.passengerSeatsNumber==1}">selected</c:if>>1</option>
                            <option <c:if test="${vehicle.passengerSeatsNumber==2}">selected</c:if>>2</option>
                            <option <c:if test="${vehicle.passengerSeatsNumber==3}">selected</c:if>>3</option>
                            <option <c:if test="${vehicle.passengerSeatsNumber==4}">selected</c:if>>4</option>
                            <option <c:if test="${vehicle.passengerSeatsNumber==5}">selected</c:if>>5</option>
                            <option <c:if test="${vehicle.passengerSeatsNumber==6}">selected</c:if>>6</option>
                            <option <c:if test="${vehicle.passengerSeatsNumber==7}">selected</c:if>>7</option>
                        </select></td>
                        <td><input type="checkbox" class="form-control" name="withConditioner" <c:if test="${vehicle.withConditioner}">checked</c:if>></td>
                        <td><button class="btn btn-primary" name="save" value="${vehicle.id}" type="submit"><fmt:message key="default.save"/></button></td>
                        <td><button class="btn btn-danger" name="delete" value="${vehicle.id}" type="submit"><fmt:message key="default.delete"/></button></td>
                    </form>
                </tr>
            </c:forEach>
            <tr>
                <td><button class="btn btn-success" name="add-car" type="button"><fmt:message key="default.add"/></button></td>
            </tr>
        </table>
    </div>
</fmt:bundle>