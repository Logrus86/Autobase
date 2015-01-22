package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.model.entity.Order;
import com.epam.bp.autobase.model.entity.User;
import com.epam.bp.autobase.model.entity.Vehicle;

import java.util.Date;
import java.util.List;

public interface OrderDao {
    public List<Order> getClientOrders(User client) throws DaoException;

    public List<Order> getClientOrders(Integer clientId) throws DaoException;

    public List<Order> getVehicleOrders(Vehicle vehicle) throws DaoException;

    public List<Order> getVehicleOrders(Integer vehicleId) throws DaoException;
    public Order getByDateOrdered(Date date) throws DaoException;
}
