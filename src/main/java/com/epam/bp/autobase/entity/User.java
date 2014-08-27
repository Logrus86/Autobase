package com.epam.bp.autobase.entity;

import com.epam.bp.autobase.dao.Identifiable;

import java.math.BigDecimal;
import java.util.Date;

public class User implements Identifiable<Integer> {
    private int id;
    private String firstname;
    private String lastname;
    private Date dob;
    private String username;
    private String password;
    private String email;
    private Role role;
    private BigDecimal balance;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getfirstName() {
        return firstname;
    }

    public void setfirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append("User {ID: ")
                .append(id)
                .append(", firstname: ")
                .append(firstname)
                .append(", lastname: ")
                .append(lastname)
                .append(", dob: ")
                .append(dob)
                .append(", username: ")
                .append(username)
                .append(", password: ")
                .append(password)
                .append(", email: ")
                .append(email)
                .append(", role: ")
                .append(role)
                .append(", balance: ")
                .append(balance);
        return sb.toString();
    }

    @Override
    public Integer getId() {
        return id;
    }

    public enum Role {
        ADMIN, CLIENT, DRIVER
    }
}
