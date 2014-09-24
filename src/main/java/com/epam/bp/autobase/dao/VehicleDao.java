package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.entity.Vehicle;

public interface VehicleDao extends BaseDao<Integer, Vehicle> {
    public Vehicle getByDriverId(Integer driverId) throws DaoException;
}
