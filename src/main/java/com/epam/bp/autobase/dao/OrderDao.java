package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.entity.Order;
import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.entity.Vehicle;

import java.util.List;

public interface OrderDao extends BaseDao<Order> {

    List<Order> getClientOrders(User client) throws DaoException;

    List<Order> getClientOrders(Integer clientId) throws DaoException;

    List<Order> getVehicleOrders(Vehicle vehicle) throws DaoException;

    List<Order> getVehicleOrders(Integer vehicleId) throws DaoException;

    Order getByDateOrdered(String dateString) throws DaoException;

}
