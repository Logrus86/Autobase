package com.epam.bp.autobase.model.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "User.findByCredentials", query = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password"),
        @NamedQuery(name = "User.getAll", query = "SELECT u FROM User u ORDER BY u.role, u.username"),
})
public class User implements Identifiable<User> {

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @Pattern(regexp = "([A-Z][a-z]{0,19})|([А-Я][а-я]{0,19})", message = "{com.epam.bp.autobase.model.entity.user.firstname.message}")
    private String firstname;

    @NotEmpty
    @Pattern(regexp = "([A-Z]('[A-Z])?[a-z]{0,19})|([А-Я][а-я]{0,19})", message = "{com.epam.bp.autobase.model.entity.user.lastname.message}")
    private String lastname;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Past
    private Date dob;

    @NotNull
    @Column(unique = true, nullable = false)
    @Pattern(regexp = "[a-zA-Z]{1}[\\w_]{3,19}", message = "{com.epam.bp.autobase.model.entity.user.username.message}")
    private String username;

    @NotEmpty
    @Pattern(regexp = "[\\w]{3,20}", message = "{com.epam.bp.autobase.model.entity.user.password.message}")
    private String password;

    @NotNull
    @Email(regexp = "[\\w\\u002E\\u005F]{0,40}@([a-zA-Z]+\\u002E){1,2}[a-zA-Z]{2,3}")
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull
    @Enumerated
    private Role role;

    private BigDecimal balance;

    @OrderBy
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Order> orders;

    @OrderBy
    @OneToMany(mappedBy = "driver", fetch = FetchType.EAGER)
    private List<Vehicle> vehicles;

    public void addOrder(Order order) {
        order.setClient(this);
        if (orders == null) orders = new LinkedList<>();
        orders.add(order);
    }

    public void addVehicle(Vehicle vehicle) {
        if ((vehicles == null)) vehicles = new LinkedList<>();
        vehicle.setDriver(this);
        vehicles.add(vehicle);
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public User setDob(Date dob) {
        this.dob = dob;
        return this;
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

    public Date getDob() {
        return dob;
    }

    public User setDob(String dob) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
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

    public User setRole(String role) {
        this.role = Role.valueOf(role);
        return this;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    @Override
    public String toString() {
        return "User {ID: " + this.getId() + ", firstname: " + firstname + ", lastname: " + lastname + ", dob: " + getDob() + ", username: " + username + ", password: " + password + ", email: " + email + ", role: " + role + ", balance: " + balance + "}";
    }

    public enum Role {
        ADMIN, CLIENT, DRIVER
    }

    @Override
    public boolean equals(Object object) {
        if (!object.getClass().equals(User.class)) return false;
        if (!firstname.equals(((User) object).getFirstname())) return false;
        if (!lastname.equals(((User) object).getLastname())) return false;
        if (!username.equals(((User) object).getUsername())) return false;
        if (!password.equals(((User) object).getPassword())) return false;
        if (!dob.equals(((User) object).getDob())) return false;
        if (!email.equals(((User) object).getEmail())) return false;
        if (!role.equals(((User) object).getRole())) return false;
        if (!balance.equals(((User) object).getBalance())) return false;
        return true;
    }
}
