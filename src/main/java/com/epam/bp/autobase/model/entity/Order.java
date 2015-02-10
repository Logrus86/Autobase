package com.epam.bp.autobase.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "VH_ORDER")
@NamedQuery(name = "Order.getAll", query = "SELECT o FROM Order o ORDER BY o.id")
public class Order implements Identifiable<Order> {
    private static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_PATTERN = "yyyy-MM-dd";

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
    private Date dateOrdered;

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

    public String getDateStart() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        return sdf.format(dateStart);
    }

    public Order setDateStart(Date dateStart) {
        this.dateStart = dateStart;
        return this;
    }

    public Order setDateStart(String dateStart) {
        if (dateStart == null) return this;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        try {
            this.dateStart = sdf.parse(dateStart);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this;
    }

    public String getDateEndString() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        Calendar result = Calendar.getInstance();
        result.setTime(dateStart);
        result.add(Calendar.DATE, dayCount);
        return sdf.format(result.getTime());
    }

    public Date getDateEndDate() {
        Calendar result = Calendar.getInstance();
        result.setTime(dateStart);
        result.add(Calendar.DATE, dayCount);
        return result.getTime();
    }

    public String getDateOrdered() {
        SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_PATTERN);
        return sdf.format(dateOrdered);
    }

    public Order setDateOrdered(Date dateOrdered) {
        this.dateOrdered = dateOrdered;
        return this;
    }

    public Order setDateOrdered(String dateOrdered) {
        if (dateOrdered == null) return this;
        SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_PATTERN);
        try {
            this.dateOrdered = sdf.parse(dateOrdered);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
                ", dateOrdered=" + dateOrdered +
                ", sum=" + sum +
                ", status=" + status +
                '}';
    }

    public enum Status {
        PENDING, PERFORMING, DONE, CANCELED
    }
}

