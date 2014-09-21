package com.epam.bp.autobase.dao.H2;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.ManufacturerDao;
import com.epam.bp.autobase.entity.props.Manufacturer;
import com.epam.bp.autobase.pool.ConnectionPool;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class H2ManufacturerDao extends H2AbstractDao<Integer, Manufacturer> implements ManufacturerDao {
    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(super.getClass());
    public H2ManufacturerDao(ConnectionPool.ProxyConnection connection) {
        super(connection);
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO MANUFACTURER(VALUE) VALUES(?);";
    }

    @Override
    public String getReadQuery() {
        return "SELECT * FROM MANUFACTURER";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE MANUFACTURER SET VALUE = ? WHERE ID = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM MANUFACTURER WHERE ID = ?;";
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
            manufacturer.setId(rs.getInt("ID"));
            manufacturer.setValue(rs.getString("VALUE"));

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
}
