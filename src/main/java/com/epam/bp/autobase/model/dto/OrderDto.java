package com.epam.bp.autobase.model.dto;

import com.epam.bp.autobase.model.entity.Order;

import java.math.BigDecimal;


public class OrderDto extends BaseDto {
    private UserDto clientDto;
    private VehicleDto vehicleDto;
    private String dateStartString;
    private Integer dayCount;
    private String dateOrderedString;
    private BigDecimal sum;
    private Order.Status status;

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
}
