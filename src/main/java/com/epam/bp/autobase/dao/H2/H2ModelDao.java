package com.epam.bp.autobase.dao.H2;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.ModelDao;
import com.epam.bp.autobase.entity.props.Model;
import com.epam.bp.autobase.pool.ConnectionPool;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class H2ModelDao extends H2AbstractDao<Integer, Model> implements ModelDao {
    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(super.getClass());
    public H2ModelDao(ConnectionPool.ProxyConnection connection) {
        super(connection);
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO MODEL(VALUE) VALUES(?);";
    }

    @Override
    public String getReadQuery() {
        return "SELECT * FROM MODEL";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE MODEL SET VALUE = ? WHERE ID = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM MODEL WHERE ID = ?;";
    }

    @Override
    public List<Model> parseResultSetList(ResultSet rs) throws DaoException {
        List<Model> models = new ArrayList<>();
        try {
            while (rs.next()) {
                models.add(parseResultSetInstance(rs));
            }
        } catch (Exception e) {
            LOGGER.error("Parsing resultSet to list of models error");
            throw new DaoException("Parsing resultSet to list of models error", e);
        }
        return models;
    }

    @Override
    public Model parseResultSetInstance(ResultSet rs) throws DaoException {
        Model model = new Model();
        try {
            model.setId(rs.getInt("ID"));
            model.setValue(rs.getString("VALUE"));

        } catch (Exception e) {
            LOGGER.error("Parsing resultSet to model error");
            throw new DaoException("Parsing resultSet to model error", e);
        }
        return model;
    }

    @Override
    public void prepareStatementForInsert(PreparedStatement ps, Model model) throws DaoException {
        try {
            ps.setString(1, model.getValue());
        } catch (Exception e) {
            LOGGER.error("Preparing statement for Insert model error");
            throw new DaoException("Preparing statement for Insert model error", e);
        }
    }

    @Override
    public void prepareStatementForUpdate(PreparedStatement ps, Model model) throws DaoException {
        try {
            ps.setString(1, model.getValue());
            ps.setString(2, String.valueOf(model.getId()));
        } catch (Exception e) {
            LOGGER.error("Preparing statement for Update model error");
            throw new DaoException("Preparing statement for Update model error", e);
        }
    }
}
