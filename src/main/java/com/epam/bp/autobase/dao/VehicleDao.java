package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.model.entity.*;

import java.util.List;

public interface VehicleDao extends BaseDao<Vehicle>{

    List<Vehicle> getVehiclesByDriver(User driver) throws DaoException;

    List<Vehicle> getVehiclesByDriverId(Integer driverId) throws DaoException;

    List<Bus> getAllBuses() throws DaoException;

    List<Car> getAllCars() throws DaoException;

    List<Truck> getAllTrucks() throws DaoException;

    List<Vehicle> findByParams(Vehicle sample) throws DaoException;
}
