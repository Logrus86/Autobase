package com.epam.bp.autobase.model.dto;

import com.epam.bp.autobase.model.entity.Order;

import java.math.BigDecimal;

public class OrderDto extends AbstractDto<Order, OrderDto> {
    private UserDto clientDto;
    private VehicleDto vehicleDto;
    private String dateStartString;
    private Integer dayCount;
    private String dateOrderedString;
    private BigDecimal sum;
    private Order.Status status;

    public OrderDto() {
    }

    public OrderDto(Order entity) {
        super(entity);
        dateStartString = entity.getDateStart();
        dayCount = entity.getDayCount();
        dateOrderedString = entity.getDateOrdered();
        sum = entity.getSum();
        status = entity.getStatus();
        vehicleDto = new VehicleDto(entity.getVehicle());
    }

    public OrderDto fetchClient(Order entity) {
        clientDto = new UserDto(entity.getClient());
        return this;
    }

    public UserDto getClientDto() {
        return clientDto;
    }

    public OrderDto setClientDto(UserDto clientDto) {
        this.clientDto = clientDto;
        return this;
    }

    public VehicleDto getVehicleDto() {
        return vehicleDto;
    }

    public OrderDto setVehicleDto(VehicleDto vehicleDto) {
        this.vehicleDto = vehicleDto;
        return this;
    }

    public String getDateStartString() {
        return dateStartString;
    }

    public OrderDto setDateStartString(String dateStartString) {
        this.dateStartString = dateStartString;
        return this;
    }

    public Integer getDayCount() {
        return dayCount;
    }

    public OrderDto setDayCount(Integer dayCount) {
        this.dayCount = dayCount;
        return this;
    }

    public String getDateOrderedString() {
        return dateOrderedString;
    }

    public OrderDto setDateOrderedString(String dateOrderedString) {
        this.dateOrderedString = dateOrderedString;
        return this;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public OrderDto setSum(BigDecimal sum) {
        this.sum = sum;
        return this;
    }

    public Order.Status getStatus() {
        return status;
    }

    public OrderDto setStatus(Order.Status status) {
        this.status = status;
        return this;
    }

    @Override
    public Order buildLazyEntity() {
        return new Order()
                .setId(getId())
                .setDateStart(dateStartString)
                .setDayCount(dayCount)
                .setDateOrdered(dateOrderedString)
                .setStatus(status)
                .setSum(sum)
                .setVehicle(vehicleDto.buildLazyEntity());
    }

    @Override
    public Order buildFullEntity() {
        return buildLazyEntity()
                .setClient(clientDto.buildLazyEntity());
    }

    @Override
    public Order overwriteEntityFromDto(Order entity) {
        if (sum != null) entity.setSum(sum);
        if (status != null) entity.setStatus(status);
        if (dateOrderedString != null) entity.setDateOrdered(dateOrderedString);
        if (dateStartString != null) entity.setDateStart(dateStartString);
        if (dayCount != null) entity.setDayCount(dayCount);
        if (clientDto != null) entity.setClient(clientDto.buildLazyEntity());
        if (vehicleDto != null) entity.setVehicle(vehicleDto.buildLazyEntity());
        return entity;
    }
}
