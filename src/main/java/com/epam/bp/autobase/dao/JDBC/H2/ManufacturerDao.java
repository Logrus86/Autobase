package com.epam.bp.autobase.dao.JDBC.H2;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.entity.Manufacturer;
import com.epam.bp.autobase.pool.ConnectionPool;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerDao extends AbstractDao<Integer, Manufacturer> implements com.epam.bp.autobase.dao.ManufacturerDao {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ManufacturerDao.class);
    public static final String ID = "ID";
    public static final String VALUE = "VALUE";
    private static final String MANUFACTURER = "MANUFACTURER";

    public ManufacturerDao(ConnectionPool.ProxyConnection connection) {
        super(connection);
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO "+MANUFACTURER+"("+VALUE+") VALUES(?);";
    }

    @Override
    public String getReadQuery() {
        return "SELECT * FROM "+MANUFACTURER;
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE "+MANUFACTURER+" SET "+VALUE+" = ? WHERE "+ID+" = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM "+MANUFACTURER+" WHERE "+ID+" = ?;";
    }

    @Override
    public List<Manufacturer> parseResultSetList(ResultSet rs) throws DaoException {
        List<Manufacturer> manufacturers = new ArrayList<>();
        try {
            while (rs.next()) {
                manufacturers.add(parseResultSetInstance(rs));
            }
        } catch (Exception e) {
            LOGGER.error("Parsing resultSet to list of manufacturers error");
            throw new DaoException("Parsing resultSet to list of manufacturers error", e);
        }
        return manufacturers;
    }

    @Override
    public Manufacturer parseResultSetInstance(ResultSet rs) throws DaoException {
        Manufacturer manufacturer = new Manufacturer();
        try {
            manufacturer.setId(rs.getInt(ID));
            manufacturer.setValue(rs.getString(VALUE));

        } catch (Exception e) {
            LOGGER.error("Parsing resultSet to manufacturer error");
            throw new DaoException("Parsing resultSet to manufacturer error", e);
        }
        return manufacturer;
    }

    @Override
    public void prepareStatementForInsert(PreparedStatement ps, Manufacturer manufacturer) throws DaoException {
        try {
            ps.setString(1, manufacturer.getValue());
        } catch (Exception e) {
            LOGGER.error("Preparing statement for Insert manufacturer error");
            throw new DaoException("Preparing statement for Insert manufacturer error", e);
        }
    }

    @Override
    public void prepareStatementForUpdate(PreparedStatement ps, Manufacturer manufacturer) throws DaoException {
        try {
            ps.setString(1, manufacturer.getValue());
            ps.setString(2, String.valueOf(manufacturer.getId()));
        } catch (Exception e) {
            LOGGER.error("Preparing statement for Update manufacturer error");
            throw new DaoException("Preparing statement for Update manufacturer error", e);
        }
    }

    @Override
    public Manufacturer getByValue(String value) throws DaoException {
        StringBuilder query = new StringBuilder();
        query.append(getReadQuery()).append(" WHERE "+VALUE+" = ?;");
        PreparedStatement ps;
        Manufacturer result;
        try {
            ps = connection.prepareStatement(query.toString());
            ps.setString(1, value);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) result = parseResultSetInstance(rs);
            else result = null;
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            throw new DaoException("Finding manufacturer by value error", e);
        }
        return result;
    }
}
