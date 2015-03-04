package com.epam.bp.autobase.model.dto;

import com.epam.bp.autobase.model.entity.Order;
import com.epam.bp.autobase.util.DateParser;

import java.math.BigDecimal;

public class OrderDto extends AbstractDto<Order, OrderDto> {
    private UserDto clientDto;
    private VehicleDto vehicleDto;
    private String dateStartString;
    private Integer dayCount;
    private String dateTimeOrderedString;
    private BigDecimal sum;
    private Order.Status status;

    public OrderDto() {
    }

    public OrderDto(Order entity) {
        super(entity);
        dateStartString = DateParser.stringFromDate(entity.getDateStart());
        dayCount = entity.getDayCount();
        dateTimeOrderedString = DateParser.stringFromDateTime(entity.getDateTimeOrdered());
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

    public String getDateTimeOrderedString() {
        return dateTimeOrderedString;
    }

    public OrderDto setDateTimeOrderedString(String dateTimeOrderedString) {
        this.dateTimeOrderedString = dateTimeOrderedString;
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
        Order order = new Order()
                .setId(getId())
                .setDateStart(DateParser.dateFromString(dateStartString))
                .setDayCount(dayCount)
                .setDateTimeOrdered(DateParser.dateTimeFromString(dateTimeOrderedString))
                .setStatus(status)
                .setSum(sum);
        if (clientDto != null) order.setClient(clientDto.buildLazyEntity());
        if (vehicleDto != null) order.setVehicle(vehicleDto.buildLazyEntity());
        return order;
    }

    @Override
    public Order buildFullEntity() {
        return buildLazyEntity();
    }

    @Override
    public Order overwriteEntityFromDto(Order entity) {
        if (sum != null) entity.setSum(sum);
        if (status != null) entity.setStatus(status);
        if (dateTimeOrderedString != null)
            entity.setDateTimeOrdered(DateParser.dateTimeFromString(dateTimeOrderedString));
        if (dateStartString != null) entity.setDateStart(DateParser.dateFromString(dateStartString));
        if (dayCount != null) entity.setDayCount(dayCount);
        if (clientDto != null) entity.setClient(clientDto.buildLazyEntity());
        if (vehicleDto != null) entity.setVehicle(vehicleDto.buildLazyEntity());
        return entity;
    }
}
