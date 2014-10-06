package com.epam.bp.autobase.entity;

import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.H2.DaoManager;
import com.epam.bp.autobase.dao.OrderDao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class User extends Entity {
    private Integer id;
    private String firstname;
    private String lastname;
    private Date dob;
    private String username;
    private String password; //TODO save hash, not pass, md5 for example
    private String email;
    private Role role;
    private BigDecimal balance;
    private List<Order> orders;

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

    public void setId(Integer id) {
        this.id = id;
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
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(daoManager1 -> {
                OrderDao orderDao = daoManager1.getOrderDao();
                orders = orderDao.getListByClientId(id);
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            throw new RuntimeException("Error getting client's order list", e);
        }
        return orders;
    }

    @Override
    public String toString() {
        return "User {ID: " + id + ", firstname: " + firstname + ", lastname: " + lastname + ", dob: " + getDob() + ", username: " + username + ", password: " + password + ", email: " + email + ", role: " + role + ", balance: " + balance + "}";
    }

    @Override
    public Integer getId() {
        return id;
    }

    public enum Role {
        ADMIN, CLIENT, DRIVER
    }
}
