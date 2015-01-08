package com.epam.bp.autobase.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@javax.persistence.Entity
@Table(name = "VH_ORDER")
public class Order extends Entity {

    //-----------------------delete these old form later
    @NotNull
    private Integer clientId;
    @NotNull
    private Integer vehicleId;
    //--------------------------------new form:
    @ManyToOne
    private User client;
    @ManyToOne
    private Vehicle vehicle;
    //---------------------------------------------------

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dateStart;

    @NotNull
    private Integer dayCount;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestampOrdered;

    @NotNull
    private BigDecimal sum;

    @Enumerated
    private Status status;

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Integer getDayCount() {
        return dayCount;
    }

    public void setDayCount(Integer dayCount) {
        this.dayCount = dayCount;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getDateStart() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(dateStart);
    }

    public void setDateStart(String dateStart) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.dateStart = sdf.parse(dateStart);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEndString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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

    public String getTimestampOrdered() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(timestampOrdered);
    }

    public void setTimestampOrdered(String dateOrdered) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.timestampOrdered = sdf.parse(dateOrdered);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setDateOrdered(Date dateOrdered) {
        this.timestampOrdered = dateOrdered;
    }

    public enum Status {
        PENDING, PERFORMING, DONE, CANCELED
    }
}

