package com.epam.bp.autobase.gwt.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserDtoGwt implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private String dob;
    private Integer role;
    private BigDecimal balance;

    public Integer getId() {
        return id;
    }

    public UserDtoGwt setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDtoGwt setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDtoGwt setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDtoGwt setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public UserDtoGwt setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public UserDtoGwt setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getDob() {
        return dob;
    }

    public UserDtoGwt setDob(String dob) {
        this.dob = dob;
        return this;
    }

    public Integer getRole() {
        return role;
    }

    public UserDtoGwt setRole(Integer role) {
        this.role = role;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public UserDtoGwt setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }
}
