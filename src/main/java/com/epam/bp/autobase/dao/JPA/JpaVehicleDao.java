package com.epam.bp.autobase.dao.JPA;

import com.epam.bp.autobase.dao.VehicleDao;
import com.epam.bp.autobase.entity.Vehicle;

public class JpaVehicleDao extends JpaAbstractDao<Integer, Vehicle> implements VehicleDao {
    public JpaVehicleDao() {
        super(Vehicle.class);
    }
}
