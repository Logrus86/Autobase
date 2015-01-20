package com.epam.bp.autobase.entity;

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

@Entity
@NamedQueries({
        @NamedQuery(name = "User.findByCredentials", query = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password"),
        @NamedQuery(name = "User.getAll", query = "SELECT u FROM User u ORDER BY u.role, u.username"),
})
public class User implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @Pattern(regexp = "([A-Z]{1}[a-z]{0,19})|([А-Я]{1}[а-я]{0,19})", message = "{com.epam.bp.autobase.entity.user.firstname.message}")
    private String firstname;

    @NotEmpty
    @Pattern(regexp = "([A-Z]{1}[a-z]{0,19})|([А-Я]{1}[а-я]{0,19})", message = "{com.epam.bp.autobase.entity.user.lastname.message}")
    private String lastname;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Past
    private Date dob;

    @NotNull
    @Column(unique=true, nullable=false)
    @Pattern(regexp = "[a-zA-Z]{1}[\\w_]{3,19}", message = "{com.epam.bp.autobase.entity.user.username.message}")
    private String username;

    @NotEmpty
    @Pattern(regexp = "[\\w]{3,20}", message = "{com.epam.bp.autobase.entity.user.password.message}")
    private String password;

    @NotNull
    @Email(regexp = "[\\w\\u002E\\u005F]{0,20}@([a-zA-Z]+\\u002E){1,2}[a-zA-Z]{2,3}")
    @Column(unique=true, nullable=false)
    private String email;

    @NotNull
    @Enumerated
    private Role role;

    @NotNull
    private BigDecimal balance;

   /* @Nullable
    @OneToMany(mappedBy = "client")
    private List<Order> orders;*/

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

  /*  public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }*/

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
        if (dob==null) {
            return "";
        }
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

   /* public List<Order> getClientOrders() {
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
    }*/

    @Override
    public String toString() {
        return "User {ID: " + this.getId() + ", firstname: " + firstname + ", lastname: " + lastname + ", dob: " + getDob() + ", username: " + username + ", password: " + password + ", email: " + email + ", role: " + role + ", balance: " + balance + "}";
    }



    public enum Role {
        ADMIN, CLIENT, DRIVER
    }

}
