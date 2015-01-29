package com.epam.bp.autobase.model.dto;

import com.epam.bp.autobase.model.entity.Order;
import com.epam.bp.autobase.model.entity.User;
import com.epam.bp.autobase.model.entity.Vehicle;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDto extends AbstractDto<User, UserDto> {
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

    public UserDto() {
    }

    public UserDto(User entity) {
        super(entity);
        firstname = entity.getFirstname();
        lastname = entity.getLastname();
        dobString = entity.getDob();
        username = entity.getUsername();
        password = entity.getPassword();
        email = entity.getEmail();
        role = entity.getRole();
        balance = entity.getBalance();
        if ((entity.getOrders() != null) && (!entity.getOrders().isEmpty())) {
            orderDtoList = new LinkedList<>();
            orderDtoList.addAll(entity.getOrders().stream().map(OrderDto::new).collect(Collectors.toList()));
        }
        if ((entity.getVehicles() != null) && (!entity.getVehicles().isEmpty())) {
            vehicleDtoList = new LinkedList<>();
            vehicleDtoList.addAll(entity.getVehicles().stream().map(VehicleDto::new).collect(Collectors.toList()));
        }
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
    public User buildEntity() {
        User user = new User()
                .setId(getId())
                .setUsername(username)
                .setPassword(password)
                .setEmail(email)
                .setFirstname(firstname)
                .setLastname(lastname)
                .setDob(dobString)
                .setRole(role)
                .setBalance(balance);
        if ((orderDtoList != null) && (!orderDtoList.isEmpty()))
            for (OrderDto orderDto : orderDtoList) user.addOrder(orderDto.buildEntity());
        if ((vehicleDtoList != null) && (!vehicleDtoList.isEmpty()))
            for (VehicleDto vehicleDto : vehicleDtoList) user.addVehicle(vehicleDto.buildEntity());
        return user;
    }
}
