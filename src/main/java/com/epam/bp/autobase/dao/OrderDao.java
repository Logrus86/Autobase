package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.entity.Order;

import java.util.Date;
import java.util.List;

public interface OrderDao extends BaseDao<Integer, Order> {
    public List<Order> getListByClientId(Integer id) throws DaoException;
    public List<Order> getListByVehicleId(Integer id) throws DaoException;
    public Order getByDateOrdered(Date date) throws DaoException;
}
