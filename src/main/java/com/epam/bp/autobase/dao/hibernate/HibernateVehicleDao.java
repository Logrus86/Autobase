package com.epam.bp.autobase.dao.hibernate;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.VehicleDao;
import com.epam.bp.autobase.model.entity.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Hibernate
@RequestScoped
public class HibernateVehicleDao extends AbstractHibernateDao<Vehicle> implements VehicleDao {

    @Inject
    EntityManager em;

    public HibernateVehicleDao() {
        super(Vehicle.class);
    }

    @Override
    @Transactional
    public void create(Vehicle entity) throws DaoException {
        super.create(entity, em);
    }

    @Override
    public Vehicle getById(Integer id) throws DaoException {
        return super.getById(id, em);
    }

    @Override
    public List<Vehicle> getAll() throws DaoException {
        return super.getAll(em);
    }

    @Override
    @Transactional
    public void update(Vehicle entity) throws DaoException {
        super.update(entity, em);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws DaoException {
        super.delete(id, em);
    }

    @Override
    @Transactional
    public void delete(Vehicle entity) throws DaoException {
        super.delete(entity, em);
    }

    @Override
    public List<Vehicle> getListByValue(String field, String value) throws DaoException {
        return super.getListByValue(field, value, em);
    }

    @Override
    public Vehicle getByFieldValue(String field, String value) throws DaoException {
        return super.getByFieldValue(field, value, em);
    }

    @Override
    public boolean checkValueExists(String field, String value) throws DaoException {
        return super.checkValueExists(field, value, em);
    }

    @Override
    public List<Vehicle> getVehiclesByDriver(User driver) throws DaoException {
        return getListByValue("driver", String.valueOf(driver));
    }

    @Override
    public List<Vehicle> getVehiclesByDriverId(Integer driverId) throws DaoException {
        return getListByValue("driver", String.valueOf(driverId));
    }

    @Override
    public List<Bus> getAllBuses() throws DaoException {
        TypedQuery<Bus> query = em.createNamedQuery("Bus.getAll", Bus.class);
        return query.getResultList();
    }

    @Override
    public List<Car> getAllCars() throws DaoException {
        TypedQuery<Car> query = em.createNamedQuery("Car.getAll", Car.class);
        return query.getResultList();
    }

    @Override
    public List<Truck> getAllTrucks() throws DaoException {
        TypedQuery<Truck> query = em.createNamedQuery("Truck.getAll", Truck.class);
        return query.getResultList();
    }

    @Override
    public List<Vehicle> findByParams(Vehicle example) throws DaoException {
        try {
            Session session = (Session) em.getDelegate();
            Criteria criteria = session.createCriteria(Vehicle.class);
            if (example.getMileage() != null) {
                criteria.add(Restrictions.le("mileage",example.getMileage()));
                example.setMileage(null);
            }
            if (example.getRentPrice() != null) {
                criteria.add(Restrictions.le("rentPrice",example.getRentPrice()));
                example.setRentPrice(null);
            }
            if (example.getType().equals(Vehicle.Type.BUS)) {
                Bus bus = (Bus) example;
                if (bus.getPassengerSeatsNumber() != null) {
                    criteria.add(Restrictions.ge("passengerSeatsNumber", bus.getPassengerSeatsNumber()));
                    bus.setPassengerSeatsNumber(null);
                }
                if (bus.getStandingPlacesNumber() != null) {
                    criteria.add(Restrictions.ge("standingPlacesNumber", bus.getStandingPlacesNumber()));
                    bus.setStandingPlacesNumber(null);
                }
            } else {
                if (example.getType().equals(Vehicle.Type.CAR)) {
                    Car car = (Car) example;
                    if (car.getPassengerSeatsNumber() != null) {
                        criteria.add(Restrictions.ge("passengerSeatsNumber", car.getPassengerSeatsNumber()));
                        car.setPassengerSeatsNumber(null);
                    }
                }
                else {
                    Truck truck = (Truck) example;
                    if (truck.getMaxPayload() != null) {
                        criteria.add(Restrictions.le("maxPayload",truck.getMaxPayload()));
                        truck.setMaxPayload(null);
                    }
                }
            }
            criteria.add(Example.create(example));
            return criteria.list();
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e.getCause());
        }
    }
}

