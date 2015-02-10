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
    private Boolean operable;
    private Vehicle.Fuel fuelType;
    private ColorDto colorDto;
    private ModelDto modelDto;
    private ManufacturerDto manufacturerDto;
    private UserDto driverDto;
    private Integer standingPlacesNumber;   //bus
    private Integer passengerSeatsNumber;   //bus & car
    private Integer doorsNumber;            //bus & car
    private Boolean withConditioner;    //car
    private BigDecimal maxPayload;      //truck
    private Boolean enclosed;           //truck
    private Boolean tipper;             //truck

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
        Vehicle vehicle = null;
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
        }
        vehicle.setId(getId())
                .setType(type)
                .setRentPrice(rentPrice)
                .setProductionYear(productionYear)
                .setMileage(mileage)
                .setOperable(operable)
                .setFuelType(fuelType);
        if (colorDto != null) vehicle.setColor(colorDto.buildLazyEntity());
        if (manufacturerDto != null) vehicle.setManufacturer(manufacturerDto.buildLazyEntity());
        if (modelDto != null) vehicle.setModel(modelDto.buildLazyEntity());
        return vehicle;
    }

    @Override
    public Vehicle buildFullEntity() {
        return buildLazyEntity()
                .setDriver(driverDto.buildLazyEntity());
    }

    @Override
    public Vehicle overwriteEntityFromDto(Vehicle entity) {
        if (type != null) {
            entity.setType(type);
            switch (type) {
                case BUS:
                    if (passengerSeatsNumber != null) ((Bus) entity).setPassengerSeatsNumber(passengerSeatsNumber);
                    if (standingPlacesNumber != null) ((Bus) entity).setStandingPlacesNumber(standingPlacesNumber);
                    if (doorsNumber != null) ((Bus) entity).setDoorsNumber(doorsNumber);
                    break;
                case CAR:
                    if (passengerSeatsNumber != null) ((Car) entity).setPassengerSeatsNumber(passengerSeatsNumber);
                    if (withConditioner != null) ((Car) entity).setWithConditioner(withConditioner);
                    if (doorsNumber != null) ((Car) entity).setDoorsNumber(doorsNumber);
                    break;
                case TRUCK:
                    if (enclosed != null) ((Truck) entity).setEnclosed(enclosed);
                    if (maxPayload != null) ((Truck) entity).setMaxPayload(maxPayload);
                    if (tipper != null) ((Truck) entity).setTipper(tipper);
                    break;
            }
        }
        if (rentPrice != null) entity.setRentPrice(rentPrice);
        if (productionYear != null) entity.setProductionYear(productionYear);
        if (mileage != null) entity.setMileage(mileage);
        if (operable != null) entity.setOperable(operable);
        if (fuelType != null) entity.setFuelType(fuelType);
        if (colorDto != null) entity.setColor(colorDto.buildLazyEntity());
        if (modelDto != null) entity.setModel(modelDto.buildLazyEntity());
        if (manufacturerDto != null) entity.setManufacturer(manufacturerDto.buildLazyEntity());
        if (driverDto != null) entity.setDriver(driverDto.buildLazyEntity());
        return entity;
    }
}
