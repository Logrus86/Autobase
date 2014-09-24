package com.epam.bp.autobase.entity;

import com.epam.bp.autobase.dao.Identifiable;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Identifiable<Integer> {
    private Integer id;
    private String firstname;
    private String lastname;
    private Date dob;
    private String username;
    private String password; //TODO save hash, not pass, md5 for example
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

    public String getDob() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(dob);
    }

    public void setDob(String dob) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.dob = sdf.parse(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setId(Integer id) {
        this.id = id;
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
                .append(getDob())
                .append(", username: ")
                .append(username)
                .append(", password: ")
                .append(password)
                .append(", email: ")
                .append(email)
                .append(", role: ")
                .append(role)
                .append(", balance: ")
                .append(balance)
                .append("}");
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
