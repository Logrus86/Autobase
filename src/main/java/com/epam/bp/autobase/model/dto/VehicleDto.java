package com.epam.bp.autobase.model.dto;

import com.epam.bp.autobase.model.entity.Vehicle;

import java.math.BigDecimal;

public class VehicleDto extends AbstractDto {
    private BigDecimal rentPrice;
    private Vehicle.Type type;
    private ColorDto colorDto;
    private ModelAndManufacturerDto modelDto;
    private ModelAndManufacturerDto manufacturerDto;
    private UserDto driverDto;
    private Integer productionYear;
    private BigDecimal mileage;
    private boolean operable;
    private Vehicle.Fuel fuelType;
    private int passengerSeatsNumber;
    private int standingPlacesNumber;
    private int doorsNumber;
    private boolean withConditioner;
    private BigDecimal maxPayload;
    private boolean enclosed;
    private boolean tipper;

    public BigDecimal getRentPrice() {
        return rentPrice;
    }

    public VehicleDto setRentPrice(BigDecimal rentPrice) {
        this.rentPrice = rentPrice;
        return this;
    }

    public Vehicle.Type getType() {
        return type;
    }

    public VehicleDto setType(Vehicle.Type type) {
        this.type = type;
        return this;
    }

    public ColorDto getColorDto() {
        return colorDto;
    }

    public VehicleDto setColorDto(ColorDto colorDto) {
        this.colorDto = colorDto;
        return this;
    }

    public ModelAndManufacturerDto getModelDto() {
        return modelDto;
    }

    public VehicleDto setModelDto(ModelAndManufacturerDto modelDto) {
        this.modelDto = modelDto;
        return this;
    }

    public ModelAndManufacturerDto getManufacturerDto() {
        return manufacturerDto;
    }

    public VehicleDto setManufacturerDto(ModelAndManufacturerDto manufacturerDto) {
        this.manufacturerDto = manufacturerDto;
        return this;
    }

    public UserDto getDriverDto() {
        return driverDto;
    }

    public VehicleDto setDriverDto(UserDto driverDto) {
        this.driverDto = driverDto;
        return this;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public VehicleDto setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
        return this;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public VehicleDto setMileage(BigDecimal mileage) {
        this.mileage = mileage;
        return this;
    }

    public boolean isOperable() {
        return operable;
    }

    public VehicleDto setOperable(boolean operable) {
        this.operable = operable;
        return this;
    }

    public Vehicle.Fuel getFuelType() {
        return fuelType;
    }

    public VehicleDto setFuelType(Vehicle.Fuel fuelType) {
        this.fuelType = fuelType;
        return this;
    }

    public int getPassengerSeatsNumber() {
        return passengerSeatsNumber;
    }

    public VehicleDto setPassengerSeatsNumber(int passengerSeatsNumber) {
        this.passengerSeatsNumber = passengerSeatsNumber;
        return this;
    }

    public int getStandingPlacesNumber() {
        return standingPlacesNumber;
    }

    public VehicleDto setStandingPlacesNumber(int standingPlacesNumber) {
        this.standingPlacesNumber = standingPlacesNumber;
        return this;
    }

    public int getDoorsNumber() {
        return doorsNumber;
    }

    public VehicleDto setDoorsNumber(int doorsNumber) {
        this.doorsNumber = doorsNumber;
        return this;
    }

    public boolean isWithConditioner() {
        return withConditioner;
    }

    public VehicleDto setWithConditioner(boolean withConditioner) {
        this.withConditioner = withConditioner;
        return this;
    }

    public BigDecimal getMaxPayload() {
        return maxPayload;
    }

    public VehicleDto setMaxPayload(BigDecimal maxPayload) {
        this.maxPayload = maxPayload;
        return this;
    }

    public boolean isEnclosed() {
        return enclosed;
    }

    public VehicleDto setEnclosed(boolean enclosed) {
        this.enclosed = enclosed;
        return this;
    }

    public boolean isTipper() {
        return tipper;
    }

    public VehicleDto setTipper(boolean tipper) {
        this.tipper = tipper;
        return this;
    }
}
