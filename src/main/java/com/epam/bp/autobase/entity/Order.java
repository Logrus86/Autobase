package com.epam.bp.autobase.entity;

import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.H2.DaoManager;
import com.epam.bp.autobase.dao.UserDao;
import com.epam.bp.autobase.dao.VehicleDao;

import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@javax.persistence.Entity
public class Order extends Entity {
    @NotNull
    private Integer clientId;

    @NotNull
    private Integer vehicleId;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStart;

    @NotNull
    private Integer dayCount;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dateOrdered;

    @NotNull
    private BigDecimal sum;

    @Enumerated
    private Status status;

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

    public String getDateOrdered() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(dateOrdered);
    }

    public void setDateOrdered(String dateOrdered) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.dateOrdered = sdf.parse(dateOrdered);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Vehicle getVehicle() {
        final Vehicle[] vehicle = {null};
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(daoManager1 -> {
                VehicleDao vehicleDao = daoManager1.getVehicleDao();
                vehicle[0] = vehicleDao.getById(vehicleId);
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            throw new RuntimeException("Error getting order's vehicle", e);
        }
        return vehicle[0];
    }

    public void setDateOrdered(Date dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public enum Status {
        PENDING, PERFORMING, DONE, CANCELED
    }
}

