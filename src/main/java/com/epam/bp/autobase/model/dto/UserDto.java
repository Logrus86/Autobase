package com.epam.bp.autobase.model.dto;

import com.epam.bp.autobase.model.entity.User;

import java.math.BigDecimal;
import java.util.List;

public class UserDto extends BaseDto {
    private String firstname;
    private String lastname;
    private String dobString;
    private String username;
    private String password;
    private String email;
    private User.Role role;
    private BigDecimal balance;
    private List<OrderDto> orderDtoList;
    private List<VehicleDto> vehicleDtoList;

    public String getFirstname() {
        return firstname;
    }

    public UserDto setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public UserDto setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getDobString() {
        return dobString;
    }

    public UserDto setDobString(String dobString) {
        this.dobString = dobString;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public User.Role getRole() {
        return role;
    }

    public UserDto setRole(User.Role role) {
        this.role = role;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public UserDto setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public List<OrderDto> getOrderDtoList() {
        return orderDtoList;
    }

    public UserDto setOrderDtoList(List<OrderDto> orderDtoList) {
        this.orderDtoList = orderDtoList;
        return this;
    }

    public List<VehicleDto> getVehicleDtoList() {
        return vehicleDtoList;
    }

    public UserDto setVehicleDtoList(List<VehicleDto> vehicleDtoList) {
        this.vehicleDtoList = vehicleDtoList;
        return this;
    }
}
