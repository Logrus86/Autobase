package com.epam.bp.autobase.dao.JDBC.H2;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.entity.Bus;
import com.epam.bp.autobase.entity.Car;
import com.epam.bp.autobase.entity.Truck;
import com.epam.bp.autobase.entity.Vehicle;
import com.epam.bp.autobase.pool.ConnectionPool;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class VehicleDao extends AbstractDao<Integer, Vehicle> implements com.epam.bp.autobase.dao.VehicleDao {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(VehicleDao.class);
    private static final String ID = "ID";


    public VehicleDao(ConnectionPool.ProxyConnection connection) {
        super(connection);
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO " + VEHICLE + "(" + VEHICLE_TYPE + ", " + MODEL_ID + ", " + MANUFACTURER_ID + ", " + PROD_YEAR + ", " + COLOR_ID + ", " + MILEAGE + ", " + FUEL_TYPE + ", " + OPERABLE + ", " + RENT + ", " + DRIVER_ID + ", " + STAND_PL_NUM + ", " + PASS_PL_NUM + ", " + DOORS_NUM + ", " + CONDITIONER + ", " + PAYLOAD + ", " + ENCLOSED + ", " + TIPPER + ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    }

    @Override
    public String getReadQuery() {
        return "SELECT * FROM " + VEHICLE;
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE " + VEHICLE + " SET " + VEHICLE_TYPE + " = ?, " + MODEL_ID + " = ?, " + MANUFACTURER_ID + " = ?, " + PROD_YEAR + " = ?, " + COLOR_ID + " = ?, " + MILEAGE + " = ?, " + FUEL_TYPE + " = ?, " + OPERABLE + " = ?, " + RENT + " = ?, " + DRIVER_ID + " = ?, " + STAND_PL_NUM + " = ?, " + PASS_PL_NUM + " = ?, " + DOORS_NUM + " = ?, " + CONDITIONER + " = ?, " + PAYLOAD + " = ?, " + ENCLOSED + " = ?, " + TIPPER + " = ? " + "WHERE " + ID + " = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM " + VEHICLE + " WHERE " + ID + " = ?;";
    }

    @Override
    public Vehicle parseResultSetInstance(ResultSet rs) throws DaoException {
        Vehicle vehicle = null;
        try {
            Vehicle.Type vhType = Vehicle.Type.valueOf(rs.getString(VEHICLE_TYPE));
            if (vhType == Vehicle.Type.BUS) {
                Integer passN = rs.getInt(PASS_PL_NUM);
                Integer standN = rs.getInt(STAND_PL_NUM);
                Integer doorsN = rs.getInt(DOORS_NUM);
                vehicle = new Bus()
                        .setPassengerSeatsNumber(passN)
                        .setStandingPlacesNumber(standN)
                        .setDoorsNumber(doorsN);
            }
            if (vhType == Vehicle.Type.CAR) {
                Integer passN = rs.getInt(PASS_PL_NUM);
                Integer doorsN = rs.getInt(DOORS_NUM);
                Boolean withConditioner = rs.getBoolean(CONDITIONER);
                vehicle = new Car()
                        .setPassengerSeatsNumber(passN)
                        .setDoorsNumber(doorsN)
                        .setWithConditioner(withConditioner);
            }
            if (vhType == Vehicle.Type.TRUCK) {
                BigDecimal payload = rs.getBigDecimal(PAYLOAD);
                Boolean enclosed = rs.getBoolean(ENCLOSED);
                Boolean tipper = rs.getBoolean(TIPPER);
                vehicle = new Truck()
                        .setMaxPayload(payload)
                        .setEnclosed(enclosed)
                        .setTipper(tipper);
            }
            if (vehicle != null) {
                Integer id = rs.getInt(ID);
                vehicle.setId(id);
                vehicle.setType(vhType);
            }

            Integer modelId = rs.getInt(MODEL_ID);
            Integer manufacturerId = rs.getInt(MANUFACTURER_ID);
            Integer colorId = rs.getInt(COLOR_ID);
            Integer prodYear = rs.getInt(PROD_YEAR);
            BigDecimal mileage = rs.getBigDecimal(MILEAGE);
            Vehicle.Fuel fuel = Vehicle.Fuel.valueOf(rs.getString(FUEL_TYPE));
            Boolean operable = rs.getBoolean(OPERABLE);
            BigDecimal rent = rs.getBigDecimal(RENT);
            Integer driverId = rs.getInt(DRIVER_ID);

            if (vehicle != null) {
                vehicle.setModelId(modelId);
                vehicle.setManufacturerId(manufacturerId);
                vehicle.setColorId(colorId);
                vehicle.setProductionYear(prodYear);
                vehicle.setMileage(mileage);
                vehicle.setFuelType(fuel);
                vehicle.setOperable(operable);
                vehicle.setRentPrice(rent);
                vehicle.setDriverId(driverId);
            }
        } catch (Exception e) {
            LOGGER.error("Parsing resultSet to vehicle error");
            throw new DaoException("Parsing resultSet to vehicle error", e);
        }
        return vehicle;
    }

    @Override
    public List<Vehicle> parseResultSetList(ResultSet rs) throws DaoException {
        List<Vehicle> vehicles = new LinkedList<>();
        try {
            while (rs.next()) vehicles.add(parseResultSetInstance(rs));
        } catch (Exception e) {
            LOGGER.error("Parsing resultSet to list of vehicles error");
            throw new DaoException("Parsing resultSet to list of vehicles error", e);
        }
        return vehicles;
    }

    @Override
    public void prepareStatementForInsert(PreparedStatement ps, Vehicle vehicle) throws DaoException {
        try {
            ps.setString(1, String.valueOf(vehicle.getType()));
            ps.setString(2, String.valueOf(vehicle.getModelId()));
            ps.setString(3, String.valueOf(vehicle.getManufacturerId()));
            ps.setString(4, String.valueOf(vehicle.getProductionYear()));
            ps.setString(5, String.valueOf(vehicle.getColorId()));
            ps.setString(6, String.valueOf(vehicle.getMileage()));
            ps.setString(7, String.valueOf(vehicle.getFuelType()));
            ps.setString(8, String.valueOf(vehicle.isOperable()));
            ps.setString(9, String.valueOf(vehicle.getRentPrice()));
            if (vehicle.getDriverId() == null) ps.setString(10, null);
            else ps.setString(10, String.valueOf(vehicle.getDriverId()));
            ps.setString(11, null);
            ps.setString(12, null);
            ps.setString(13, null);
            ps.setString(14, null);
            ps.setString(15, null);
            ps.setString(16, null);
            ps.setString(17, null);
            if (vehicle.getType() == Vehicle.Type.BUS) {
                Bus bus = (Bus) vehicle;
                ps.setString(11, String.valueOf(bus.getStandingPlacesNumber()));
                ps.setString(12, String.valueOf(bus.getPassengerSeatsNumber()));
                ps.setString(13, String.valueOf(bus.getDoorsNumber()));
            }
            if (vehicle.getType() == Vehicle.Type.CAR) {
                Car car = (Car) vehicle;
                ps.setString(12, String.valueOf(car.getPassengerSeatsNumber()));
                ps.setString(13, String.valueOf(car.getDoorsNumber()));
                ps.setString(14, String.valueOf(car.isWithConditioner()));
            }
            if (vehicle.getType() == Vehicle.Type.TRUCK) {
                Truck truck = (Truck) vehicle;
                ps.setString(15, String.valueOf(truck.getMaxPayload()));
                ps.setString(16, String.valueOf(truck.isEnclosed()));
                ps.setString(17, String.valueOf(truck.isTipper()));
            }
        } catch (Exception e) {
            LOGGER.error("Preparing statement for Insert error");
            throw new DaoException("Preparing statement for Insert error", e);
        }
    }

    @Override
    public void prepareStatementForUpdate(PreparedStatement ps, Vehicle vehicle) throws DaoException {
        try {
            prepareStatementForInsert(ps, vehicle);
            ps.setString(18, String.valueOf(vehicle.getId()));
        } catch (Exception e) {
            LOGGER.error("Preparing statement for Update error");
            throw new DaoException("Preparing statement for Update error", e);
        }
    }
}
