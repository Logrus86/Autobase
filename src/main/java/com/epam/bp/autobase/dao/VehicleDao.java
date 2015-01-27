package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.model.entity.User;
import com.epam.bp.autobase.model.entity.Vehicle;

import java.util.List;

public interface VehicleDao {

    public List<Vehicle> getVehiclesByDriver(User driver) throws DaoException;

    public List<Vehicle> getVehiclesByDriver(Integer driverId) throws DaoException;

}
