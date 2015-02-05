package com.epam.bp.autobase.model.dto;

import com.epam.bp.autobase.model.entity.Bus;
import com.epam.bp.autobase.model.entity.Car;
import com.epam.bp.autobase.model.entity.Truck;
import com.epam.bp.autobase.model.entity.Vehicle;

import java.math.BigDecimal;

public class VehicleDto extends AbstractDto<Vehicle, VehicleDto> {
    private Vehicle.Type type;
    private BigDecimal rentPrice;
    private Integer productionYear;
    private BigDecimal mileage;
    private boolean operable;
    private Vehicle.Fuel fuelType;
    private ColorDto colorDto;
    private ModelDto modelDto;
    private ManufacturerDto manufacturerDto;
    private UserDto driverDto;
    private int standingPlacesNumber;   //bus
    private int passengerSeatsNumber;   //bus & car
    private int doorsNumber;            //bus & car
    private boolean withConditioner;    //car
    private BigDecimal maxPayload;      //truck
    private boolean enclosed;           //truck
    private boolean tipper;             //truck

    public VehicleDto() {
    }

    public VehicleDto(Vehicle vehicle) {
        super(vehicle);
        type = vehicle.getType();
        rentPrice = vehicle.getRentPrice();
        productionYear = vehicle.getProductionYear();
        mileage = vehicle.getMileage();
        operable = vehicle.isOperable();
        fuelType = vehicle.getFuelType();
        colorDto = new ColorDto(vehicle.getColor());
        modelDto = new ModelDto(vehicle.getModel());
        manufacturerDto = new ManufacturerDto((vehicle.getManufacturer()));
        switch (type) {
            case BUS:
                Bus bus = (Bus) vehicle;
                standingPlacesNumber = bus.getStandingPlacesNumber();
                passengerSeatsNumber = bus.getPassengerSeatsNumber();
                doorsNumber = bus.getDoorsNumber();
                break;
            case CAR:
                Car car = (Car) vehicle;
                passengerSeatsNumber = car.getPassengerSeatsNumber();
                doorsNumber = car.getDoorsNumber();
                withConditioner = car.isWithConditioner();
                break;
            case TRUCK:
                Truck truck = (Truck) vehicle;
                maxPayload = truck.getMaxPayload();
                enclosed = truck.isEnclosed();
                tipper = truck.isTipper();
                break;
        }
    }

    public VehicleDto fetchDriver(Vehicle vehicle) {
        driverDto = new UserDto(vehicle.getDriver());
        return this;
    }

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

    public ModelDto getModelDto() {
        return modelDto;
    }

    public VehicleDto setModelDto(ModelDto modelDto) {
        this.modelDto = modelDto;
        return this;
    }

    public ManufacturerDto getManufacturerDto() {
        return manufacturerDto;
    }

    public VehicleDto setManufacturerDto(ManufacturerDto manufacturerDto) {
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

    @Override
    public Vehicle buildLazyEntity() {
        Vehicle vehicle;
        switch (type) {
            case BUS:
                vehicle = new Bus()
                        .setPassengerSeatsNumber(passengerSeatsNumber)
                        .setStandingPlacesNumber(standingPlacesNumber)
                        .setDoorsNumber(doorsNumber);
                break;
            case CAR:
                vehicle = new Car()
                        .setPassengerSeatsNumber(passengerSeatsNumber)
                        .setDoorsNumber(doorsNumber)
                        .setWithConditioner(withConditioner);
                break;
            case TRUCK:
                vehicle = new Truck()
                        .setEnclosed(enclosed)
                        .setMaxPayload(maxPayload)
                        .setTipper(tipper);
                break;
            default:
                vehicle = null;
        }
        vehicle.setId(getId())
                .setType(type)
                .setRentPrice(rentPrice)
                .setProductionYear(productionYear)
                .setMileage(mileage)
                .setOperable(operable)
                .setFuelType(fuelType)
                .setColor(colorDto.buildLazyEntity())
                .setManufacturer(manufacturerDto.buildLazyEntity())
                .setModel(modelDto.buildLazyEntity());
        return vehicle;
    }

    @Override
    public Vehicle buildFullEntity() {
        return buildLazyEntity()
                .setDriver(driverDto.buildLazyEntity());
    }
}
