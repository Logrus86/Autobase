package com.epam.bp.autobase.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@javax.persistence.Entity
public class User extends Entity {

    @NotEmpty
    @Pattern(regexp = "([A-Z]{1}[a-z]{0,19})|([А-Я]{1}[а-я]{0,19})", message = "incorrect firstname")
    private String firstname;

    @NotEmpty
    @Pattern(regexp = "([A-Z]{1}[a-z]{0,19})|([А-Я]{1}[а-я]{0,19})", message = "incorrect lastname")
    private String lastname;

    @NotEmpty
    @Temporal(TemporalType.DATE)
    private Date dob;

    @NotEmpty
    @Pattern(regexp = "[a-zA-Z]{1}[\\w_]{3,19}", message = "incorrect username")
    private String username;

    @NotEmpty
    @Pattern(regexp = "[\\w]{3,20}", message = "incorrect username")
    private String password;

    @NotEmpty
    @Email
    private String email;

    @NotNull
    @Enumerated
    private Role role;

    @NotNull
    private BigDecimal balance;

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

    @Override
    public String toString() {
        return "User {ID: " + this.getId() + ", firstname: " + firstname + ", lastname: " + lastname + ", dob: " + getDob() + ", username: " + username + ", password: " + password + ", email: " + email + ", role: " + role + ", balance: " + balance + "}";
    }

    public enum Role {
        ADMIN, CLIENT, DRIVER
    }
}
