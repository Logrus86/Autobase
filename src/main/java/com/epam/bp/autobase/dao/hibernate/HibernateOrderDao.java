package com.epam.bp.autobase.dao.hibernate;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.OrderDao;
import com.epam.bp.autobase.entity.Order;
import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.entity.Vehicle;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Hibernate
@RequestScoped
public class HibernateOrderDao extends AbstractHibernateDao<Order> implements OrderDao {

    @Inject
    EntityManager em;

    public HibernateOrderDao() {
        super(Order.class);
    }

    @Transactional
    @Override
    public void create(Order entity) throws DaoException {
        super.create(entity, em);
    }

    @Override
    public Order getById(Integer id) throws DaoException {
        return super.getById(id, em);
    }

    @Override
    public List<Order> getAll() throws DaoException {
        return super.getAll(em);
    }

    @Transactional
    @Override
    public void update(Order entity) throws DaoException {
        super.update(entity, em);
    }

    @Transactional
    @Override
    public void delete(Integer id) throws DaoException {
        super.delete(id, em);
    }

    @Transactional
    @Override
    public void delete(Order entity) throws DaoException {
        super.delete(entity, em);
    }

    @Override
    public boolean checkValueExists(String field, String value) throws DaoException {
        return super.checkValueExists(field, value, em);
    }

    @Override
    public List<Order> getListByValue(String field, String value) throws DaoException {
        return super.getListByValue(field, value, em);
    }

    @Override
    public Order getByFieldValue(String field, String value) throws DaoException {
        return super.getByFieldValue(field, value, em);
    }

    @Override
    public List<Order> getClientOrders(User client) throws DaoException {
        return getListByValue("CLIENT_ID", String.valueOf(client.getId()));
    }

    @Override
    public List<Order> getClientOrders(Integer clientId) throws DaoException {
        return getListByValue("CLIENT_ID", String.valueOf(clientId));
    }

    @Override
    public List<Order> getVehicleOrders(Vehicle vehicle) throws DaoException {
        return getListByValue("VEHICLE_ID", String.valueOf(vehicle.getId()));
    }

    @Override
    public List<Order> getVehicleOrders(Integer vehicleId) throws DaoException {
        return getListByValue("VEHICLE_ID", String.valueOf(vehicleId));
    }

    @Override
    public Order getByDateOrdered(String dateString) throws DaoException {
        return getByFieldValue("DATE_ORDERED", dateString);
    }
}
