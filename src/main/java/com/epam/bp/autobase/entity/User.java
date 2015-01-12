package com.epam.bp.autobase.entity;

import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.DaoManager;
import com.epam.bp.autobase.dao.OrderDao;
import com.sun.istack.internal.Nullable;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@javax.persistence.Entity
public class User implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @Pattern(regexp = "([A-Z]{1}[a-z]{0,19})|([А-Я]{1}[а-я]{0,19})", message = "Incorrect first name")
    private String firstname;

    @NotEmpty
    @Pattern(regexp = "([A-Z]{1}[a-z]{0,19})|([А-Я]{1}[а-я]{0,19})", message = "Incorrect last name")
    private String lastname;

    @NotEmpty
    @Temporal(TemporalType.DATE)
    private Date dob;

    @Column(unique=true, nullable=false)
    @Pattern(regexp = "[a-zA-Z]{1}[\\w_]{3,19}", message = "Incorrect username")
    private String username;

    @NotEmpty
    @Pattern(regexp = "[\\w]{3,20}", message = "Incorrect password")
    private String password;

    @Email
    @Column(unique=true, nullable=false)
    private String email;

    @NotNull
    @Enumerated
    private Role role;

    @NotNull
    private BigDecimal balance;

    @Nullable
    @OneToMany(mappedBy = "client")
    private List<Order> orders;

/*    @Nullable
    @OneToMany(mappedBy = "driver")
    private List<Vehicle> vehicles;

    public void addOrder(Order order){
        order.setClient(this);
        orders.add(order);
    }

    public void addVehicle(Vehicle vehicle){
        vehicle.setDriver(this);
        vehicles.add(vehicle);
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
    */

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getFirstname() {
        return firstname;
    }

    public User setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public User setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getDob() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(dob);
    }

    public User setDob(String dob) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.dob = sdf.parse(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public User setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    public List<Order> getClientOrders() {
        try {
            com.epam.bp.autobase.dao.DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(daoManager1 -> {
                OrderDao orderDao = daoManager1.getOrderDao();
                orders = orderDao.getListByClientId(this.getId());
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            throw new RuntimeException("Error getting client's order list", e);
        }
        return orders;
    }

    @Override
    public String toString() {
        return "User {ID: " + this.getId() + ", firstname: " + firstname + ", lastname: " + lastname + ", dob: " + getDob() + ", username: " + username + ", password: " + password + ", email: " + email + ", role: " + role + ", balance: " + balance + "}";
    }

    public enum Role {
        ADMIN, CLIENT, DRIVER
    }

}
