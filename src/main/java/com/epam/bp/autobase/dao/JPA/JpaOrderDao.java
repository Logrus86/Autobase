package com.epam.bp.autobase.dao.JPA;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.OrderDao;
import com.epam.bp.autobase.entity.Order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class JpaOrderDao extends JpaAbstractDao<Integer, Order> implements OrderDao {
    public JpaOrderDao() {
        super(Order.class);
    }

    @Override
    public List<Order> getListByClientId(Integer id) throws DaoException {
        return getListByParameter("clientId", String.valueOf(id));
    }

    @Override
    public List<Order> getListByVehicleId(Integer id) throws DaoException {
        return getListByParameter("vehicleId", String.valueOf(id));
    }

    @Override
    public Order getByDateOrdered(Date date) throws DaoException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return getListByParameter("dateOrdered", sdf.format(date)).get(0);
    }
}
