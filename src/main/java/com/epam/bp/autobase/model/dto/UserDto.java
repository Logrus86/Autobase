package com.epam.bp.autobase.model.dto;

import com.epam.bp.autobase.model.entity.Order;
import com.epam.bp.autobase.model.entity.User;
import com.epam.bp.autobase.model.entity.Vehicle;
import com.epam.bp.autobase.util.DateParser;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class UserDto extends AbstractDto<User, UserDto> {
    private String firstname;
    private String lastname;
    private String dob;
    private String username;
    private String password;
    private String password_repeat;
    private String email;
    private User.Role role;
    private BigDecimal balance;
    private List<OrderDto> orderDtoList;
    private List<VehicleDto> vehicleDtoList;

    public UserDto() {
    }

    public UserDto(User entity) {
        super(entity);
        firstname = entity.getFirstname();
        lastname = entity.getLastname();
        dob = DateParser.stringFromDate(entity.getDob());
        username = entity.getUsername();
        password = entity.getPassword();
        email = entity.getEmail();
        role = entity.getRole();
        balance = entity.getBalance();
    }

    public UserDto fetchOrders(User entity) {
        if ((entity.getOrders() != null) && (!entity.getOrders().isEmpty())) {
            orderDtoList = new LinkedList<>();
            for (Order order : entity.getOrders()) {
                orderDtoList.add(new OrderDto(order));
            }
            //orderDtoList.addAll(entity.getOrders().stream().map(OrderDto::new).collect(Collectors.toList()));
        }
        return this;
    }

    public UserDto fetchVehicles(User entity) {
        if ((entity.getVehicles() != null) && (!entity.getVehicles().isEmpty())) {
            vehicleDtoList = new LinkedList<>();
            for (Vehicle vehicle : entity.getVehicles()) {
                vehicleDtoList.add(new VehicleDto(vehicle));
            }
            // vehicleDtoList.addAll(entity.getVehicles().stream().map(VehicleDto::new).collect(Collectors.toList()));
        }
        return this;
    }

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

    public String getDob() {
        return dob;
    }

    public UserDto setDob(String dob) {
        this.dob = dob;
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

    public String getPassword_repeat() {
        return password_repeat;
    }

    public UserDto setPassword_repeat(String password_repeat) {
        this.password_repeat = password_repeat;
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

    public UserDto setRole(String role) {
        this.role = User.Role.valueOf(role);
        return this;
    }

    public UserDto setRole(User.Role role) {
        this.role = role;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public UserDto setBalance(String balance) {
        try {
            this.balance = BigDecimal.valueOf(Long.parseLong(balance));
        } catch (NumberFormatException e) {
            this.balance = BigDecimal.ZERO;
        }
        return this;
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

    public void addVehicleDtoToList(VehicleDto dto) {
        if (vehicleDtoList == null) vehicleDtoList = new LinkedList<>();
        vehicleDtoList.add(dto);
    }

    public void addVehicleToList(Vehicle vehicle) {
        if (vehicleDtoList == null) vehicleDtoList = new LinkedList<>();
        vehicleDtoList.add(new VehicleDto(vehicle));
    }

    public void addOrderDtoToList(OrderDto dto) {
        if (orderDtoList == null) orderDtoList = new LinkedList<>();
        orderDtoList.add(dto);
    }

    public void addOrderToList(Order order) {
        if (orderDtoList == null) orderDtoList = new LinkedList<>();
        orderDtoList.add(new OrderDto(order));
    }

    @Override
    public User buildLazyEntity() {
        return new User()
                .setId(getId())
                .setUsername(username)
                .setPassword(password)
                .setEmail(email)
                .setFirstname(firstname)
                .setLastname(lastname)
                .setDob(DateParser.dateFromString(dob))
                .setRole(role)
                .setBalance(balance);
    }

    @Override
    public User buildFullEntity() {
        User user = buildLazyEntity();
        if ((orderDtoList != null) && (!orderDtoList.isEmpty()))
            for (OrderDto orderDto : orderDtoList) user.addOrder(orderDto.buildLazyEntity());
        if ((vehicleDtoList != null) && (!vehicleDtoList.isEmpty()))
            for (VehicleDto vehicleDto : vehicleDtoList) user.addVehicle(vehicleDto.buildLazyEntity());
        return user;
    }

    @Override
    public User overwriteEntityFromDto(User entity) {
        if (firstname != null) entity.setFirstname(firstname);
        if (lastname != null) entity.setLastname(lastname);
        if (dob != null) entity.setDob(DateParser.dateFromString(dob));
        if (username != null) entity.setUsername(username);
        if (email != null) entity.setEmail(email);
        if (role != null) entity.setRole(role);
        if (balance != null) entity.setBalance(balance);
        if ((orderDtoList != null) && (!orderDtoList.isEmpty()))
            for (OrderDto orderDto : orderDtoList) entity.addOrder(orderDto.buildLazyEntity());
        if ((vehicleDtoList != null) && (!vehicleDtoList.isEmpty()))
            for (VehicleDto vehicleDto : vehicleDtoList) entity.addVehicle(vehicleDto.buildLazyEntity());
        return entity;
    }
}
