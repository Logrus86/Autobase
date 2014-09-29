package com.epam.bp.autobase.dao.H2;

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

public class VehicleDao extends AbstractDao<java.lang.Integer, Vehicle> implements com.epam.bp.autobase.dao.VehicleDao {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(VehicleDao.class);
    private static final String ID = "ID";
    private static final String VEHICLE = "VEHICLE";
    private static final String VEHICLETYPE = "VEHICLETYPE";
    private static final String MODEL_ID = "MODEL_ID";
    private static final String MANUFACTURER_ID = "MANUFACTURER_ID";
    private static final String PRODUCTIONYEAR = "PRODUCTIONYEAR";
    private static final String COLOR_ID = "COLOR_ID";
    private static final String MILEAGE = "MILEAGE";
    private static final String FUELTYPE = "FUELTYPE";
    private static final String OPERABLE = "OPERABLE";
    private static final String RENTPRICE = "RENTPRICE";
    private static final String DRIVER_ID = "DRIVER_ID";
    private static final String STANDING_PLACES_NUMBER = "STANDING_PLACES_NUMBER";
    private static final String PASSENGER_SEATS_NUMBER = "PASSENGER_SEATS_NUMBER";
    private static final String DOORS_NUMBER = "DOORS_NUMBER";
    private static final String CONDITIONER = "CONDITIONER";
    private static final String MAX_PAYLOAD = "MAX_PAYLOAD";
    private static final String ENCLOSED = "ENCLOSED";
    private static final String TIPPER = "TIPPER";
    private static final String CAR = "Car";
    private static final String BUS = "Bus";
    private static final String TRUCK = "Truck";

    public VehicleDao(ConnectionPool.ProxyConnection connection) {
        super(connection);
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO " + VEHICLE + "(" + VEHICLETYPE + ", " + MODEL_ID + ", " + MANUFACTURER_ID + ", " + PRODUCTIONYEAR + ", " + COLOR_ID + ", " + MILEAGE + ", " + FUELTYPE + ", " + OPERABLE + ", " + RENTPRICE + ", " + DRIVER_ID + ", " + STANDING_PLACES_NUMBER + ", " + PASSENGER_SEATS_NUMBER + ", " + DOORS_NUMBER + ", " + CONDITIONER + ", " + MAX_PAYLOAD + ", " + ENCLOSED + ", " + TIPPER + ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    }

    @Override
    public String getReadQuery() {
        return "SELECT * FROM " + VEHICLE;
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE "+VEHICLE+" SET " + VEHICLETYPE + " = ?, " + MODEL_ID + " = ?, " + MANUFACTURER_ID + " = ?, " + PRODUCTIONYEAR + " = ?, " + COLOR_ID + " = ?, " + MILEAGE + " = ?, " + FUELTYPE + " = ?, " + OPERABLE + " = ?, " + RENTPRICE + " = ?, " + DRIVER_ID + " = ?, " + STANDING_PLACES_NUMBER + " = ?, " + PASSENGER_SEATS_NUMBER + " = ?, " + DOORS_NUMBER + " = ?, " + CONDITIONER + " = ?, " + MAX_PAYLOAD + " = ?, " + ENCLOSED + " = ?, " + TIPPER + " = ? " + "WHERE "+ID+" = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM "+VEHICLE+" WHERE "+ID+" = ?;";
    }

    @Override
    public Vehicle parseResultSetInstance(ResultSet rs) throws DaoException {
        Vehicle vehicle = null;
        try {
            String vehicleType = rs.getString(VEHICLETYPE);
            if ("BUS".equals(vehicleType.toUpperCase())) {
                java.lang.Integer passN = rs.getInt(PASSENGER_SEATS_NUMBER);
                java.lang.Integer standN = rs.getInt(STANDING_PLACES_NUMBER);
                java.lang.Integer doorsN = rs.getInt(DOORS_NUMBER);
                vehicle = new Bus()
                        .setPassengerSeatsNumber(passN)
                        .setStandingPlacesNumber(standN)
                        .setDoorsNumber(doorsN);
            }
            if ("CAR".equals(vehicleType.toUpperCase())) {
                java.lang.Integer passN = rs.getInt(PASSENGER_SEATS_NUMBER);
                java.lang.Integer doorsN = rs.getInt(DOORS_NUMBER);
                Boolean withConditioner = rs.getBoolean(CONDITIONER);
                vehicle = new Car()
                        .setPassengerSeatsNumber(passN)
                        .setDoorsNumber(doorsN)
                        .setWithConditioner(withConditioner);
            }
            if ("TRUCK".equals(vehicleType.toUpperCase())) {
                BigDecimal payload = rs.getBigDecimal(MAX_PAYLOAD);
                Boolean enclosed = rs.getBoolean(ENCLOSED);
                Boolean tipper = rs.getBoolean(TIPPER);
                vehicle = new Truck()
                        .setMaxPayload(payload)
                        .setEnclosed(enclosed)
                        .setTipper(tipper);
            }
            if (vehicle != null) {
                java.lang.Integer id = rs.getInt(ID);
                vehicle.setId(id);
            }

            Integer modelId = rs.getInt(MODEL_ID);
            Integer manufacturerId = rs.getInt(MANUFACTURER_ID);
            Integer colorId = rs.getInt(COLOR_ID);
            Integer prodYear = rs.getInt(PRODUCTIONYEAR);
            BigDecimal mileage = rs.getBigDecimal(MILEAGE);
            Vehicle.Fuel fuel = Vehicle.Fuel.valueOf(rs.getString(FUELTYPE));
            Boolean operable = rs.getBoolean(OPERABLE);
            BigDecimal rent = rs.getBigDecimal(RENTPRICE);
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
            ps.setString(1, vehicle.getClass().getSimpleName());
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
            if ("Bus".equals(vehicle.getClass().getSimpleName())) {
                Bus bus = (Bus) vehicle;
                ps.setString(11, String.valueOf(bus.getStandingPlacesNumber()));
                ps.setString(12, String.valueOf(bus.getPassengerSeatsNumber()));
                ps.setString(13, String.valueOf(bus.getDoorsNumber()));
            }
            if ("Car".equals(vehicle.getClass().getSimpleName())) {
                Car car = (Car) vehicle;
                ps.setString(12, String.valueOf(car.getPassengerSeatsNumber()));
                ps.setString(13, String.valueOf(car.getDoorsNumber()));
                ps.setString(14, String.valueOf(car.isWithConditioner()));
            }
            if ("Truck".equals(vehicle.getClass().getSimpleName())) {
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

    @Override
    public Vehicle getByDriverId(java.lang.Integer driverId) throws DaoException {
        StringBuilder query = new StringBuilder();
        query.append(getReadQuery()).append(" WHERE "+DRIVER_ID+" = ?;");
        PreparedStatement ps;
        Vehicle result;
        try {
            ps = connection.prepareStatement(query.toString());
            ps.setString(1, String.valueOf(driverId));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) result = parseResultSetInstance(rs);
            else result = null;
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            throw new DaoException("Finding vehicle by driverId error", e);
        }
        return result;
    }
}
