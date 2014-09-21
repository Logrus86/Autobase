package com.epam.bp.autobase.dao.H2;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.VehicleDao;
import com.epam.bp.autobase.entity.Bus;
import com.epam.bp.autobase.entity.Car;
import com.epam.bp.autobase.entity.Truck;
import com.epam.bp.autobase.entity.Vehicle;
import com.epam.bp.autobase.pool.ConnectionPool;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class H2VehicleDao extends H2AbstractDao<Integer, Vehicle> implements VehicleDao {
    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(super.getClass());

    public H2VehicleDao(ConnectionPool.ProxyConnection connection) {
        super(connection);
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO VEHICLE(VEHICLETYPE, MODEL, MANUFACTURER, PRODUCTIONYEAR, COLOR, MILEAGE, FUELTYPE, OPERABLE, RENTPRICE, DRIVERID, STANDING_PLACES_NUMBER, PASSENGER_SEATS_NUMBER, DOORS_NUMBER, CONDITIONER, MAX_PAYLOAD, ENCLOSED, TIPPER) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    }

    @Override
    public String getReadQuery() {
        return "SELECT * FROM VEHICLE";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE VEHICLE SET VEHICLETYPE = ?, MODEL = ?, MANUFACTURER = ?, PRODUCTIONYEAR = ?, COLOR = ?, MILEAGE = ?, FUELTYPE = ?, OPERABLE = ?, RENTPRICE = ?, DRIVERID = ?, STANDING_PLACES_NUMBER = ?, PASSENGER_SEATS_NUMBER = ?, DOORS_NUMBER = ?, CONDITIONER = ?, MAX_PAYLOAD = ?, ENCLOSED = ?, TIPPER = ? WHERE ID = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM VEHICLE WHERE ID = ?;";
    }

    @Override
    public Vehicle parseResultSetInstance(ResultSet rs) throws DaoException {
        Vehicle vehicle = null;
        try {
            String vehicleType = rs.getString("VEHICLETYPE");
            if ("BUS".equals(vehicleType.toUpperCase())) {
                vehicle = new Bus()
                        .setPassengerSeatsNumber(rs.getInt("PASSENGER_SEATS_NUMBER"))
                        .setStandingPlacesNumber(rs.getInt("STANDING_PLACES_NUMBER"))
                        .setDoorsNumber(rs.getInt("DOORS_NUMBER"));
            }
            if ("CAR".equals(vehicleType.toUpperCase())) {
                vehicle = new Car()
                        .setPassengerSeatsNumber(rs.getInt("PASSENGER_SEATS_NUMBER"))
                        .setDoorsNumber(rs.getInt("DOORS_NUMBER"))
                        .setWithConditioner(rs.getBoolean("CONDITIONER"));
            }
            if ("TRUCK".equals(vehicleType.toUpperCase())) {
                vehicle = new Truck()
                        .setMaxPayload(rs.getBigDecimal("MAX_PAYLOAD"))
                        .setEnclosed(rs.getBoolean("ENCLOSED"))
                        .setTipper(rs.getBoolean("TIPPER"));
            }
            if (vehicle != null) {
                vehicle.setId(rs.getInt("ID"));
            }
            vehicle.setModel(rs.getString("MODEL"));
            vehicle.setManufacturer(rs.getString("MANUFACTURER"));
            vehicle.setProductionYear(rs.getInt("PRODUCTIONYEAR"));
            vehicle.setColor(rs.getString("COLOR"));
            vehicle.setMileage(rs.getBigDecimal("MILEAGE"));
            vehicle.setFuelType(Vehicle.Fuel.valueOf(rs.getString("FUELTYPE")));
            vehicle.setOperable(rs.getBoolean("OPERABLE"));
            vehicle.setRentPrice(rs.getBigDecimal("RENTPRICE"));
            vehicle.setDriverId(rs.getInt("DRIVERID"));
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
            ps.setString(2, vehicle.getModel());
            ps.setString(3, vehicle.getManufacturer());
            ps.setString(4, String.valueOf(vehicle.getProductionYear()));
            ps.setString(5, vehicle.getColor());
            ps.setString(6, String.valueOf(vehicle.getMileage()));
            ps.setString(7, String.valueOf(vehicle.getFuelType()));
            ps.setString(8, String.valueOf(vehicle.isOperable()));
            ps.setString(9, String.valueOf(vehicle.getRentPrice()));
            if (vehicle.getDriverId()==null) ps.setString(10, null);
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
}
