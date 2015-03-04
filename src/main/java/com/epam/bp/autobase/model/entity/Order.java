package com.epam.bp.autobase.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "VH_ORDER")
@NamedQuery(name = "Order.getAll", query = "SELECT o FROM Order o ORDER BY o.id")
public class Order implements Identifiable<Order> {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @ManyToOne
    private User client;

    @NotNull
    @ManyToOne
    private Vehicle vehicle;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_START")
    private Date dateStart;

    @NotNull
    @Min(1)
    @Column(name = "DAYS_COUNT")
    private Integer dayCount;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Past
    @Column(name = "DATE_ORDERED")
    private Date dateTimeOrdered;

    @NotNull
    @Min(1000)
    @Digits(integer = 9, fraction = 2)
    private BigDecimal sum;

    @Enumerated
    private Status status;

    public Integer getId() {
        return id;
    }

    public Order setId(Integer id) {
        this.id = id;
        return this;
    }

    public User getClient() {
        return client;
    }

    public Order setClient(User client) {
        if (client.getRole().equals(User.Role.CLIENT)) {
            this.client = client;
            return this;
        }
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Order setStatus(Status status) {
        this.status = status;
        return this;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public Order setSum(BigDecimal sum) {
        this.sum = sum;
        return this;
    }

    public Integer getDayCount() {
        return dayCount;
    }

    public Order setDayCount(Integer dayCount) {
        this.dayCount = dayCount;
        return this;
    }

    public Date getDateStart() {
        return this.dateStart;
    }

    public Order setDateStart(Date dateStart) {
        this.dateStart = dateStart;
        return this;
    }

    public Date getDateTimeOrdered() {
        return this.dateTimeOrdered;
    }

    public Order setDateTimeOrdered(Date dateOrdered) {
        this.dateTimeOrdered = dateOrdered;
        return this;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Order setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client=" + client +
                ", vehicle=" + vehicle +
                ", dateStart=" + dateStart +
                ", dayCount=" + dayCount +
                ", dateOrdered=" + dateTimeOrdered +
                ", sum=" + sum +
                ", status=" + status +
                '}';
    }

    public enum Status {
        PENDING, PERFORMING, DONE, CANCELED
    }
}

