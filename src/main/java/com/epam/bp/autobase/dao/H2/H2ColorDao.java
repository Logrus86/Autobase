package com.epam.bp.autobase.dao.H2;

import com.epam.bp.autobase.dao.ColorDao;
import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.entity.props.Color;
import com.epam.bp.autobase.pool.ConnectionPool;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class H2ColorDao extends H2AbstractDao<Integer, Color> implements ColorDao {
    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(super.getClass());
    public H2ColorDao(ConnectionPool.ProxyConnection connection) {
        super(connection);
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO COLOR(COLOR_EN, COLOR_RU) VALUES(?, ?);";
    }

    @Override
    public String getReadQuery() {
        return "SELECT * FROM COLOR";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE COLOR SET COLOR_EN = ?, COLOR_RU = ? WHERE ID = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM COLOR WHERE ID = ?;";
    }

    @Override
    public List<Color> parseResultSetList(ResultSet rs) throws DaoException {
        List<Color> colors = new ArrayList<>();
        try {
            while (rs.next()) {
                colors.add(parseResultSetInstance(rs));
            }
        } catch (Exception e) {
            LOGGER.error("Parsing resultSet to list of colors error");
            throw new DaoException("Parsing resultSet to list of colors error", e);
        }
        return colors;
    }

    @Override
    public Color parseResultSetInstance(ResultSet rs) throws DaoException {
        Color color = new Color();
        try {
            color.setId(rs.getInt("ID"));
            color.setValueEn(rs.getString("VALUE_EN"));
            color.setValueRu(rs.getString("VALUE_RU"));

        } catch (Exception e) {
            LOGGER.error("Parsing resultSet to color error");
            throw new DaoException("Parsing resultSet to color error", e);
        }
        return color;
    }

    @Override
    public void prepareStatementForInsert(PreparedStatement ps, Color color) throws DaoException {
        try {
            ps.setString(1, color.getValueEn());
            ps.setString(2, color.getValueRu());
        } catch (Exception e) {
            LOGGER.error("Preparing statement for Insert color error");
            throw new DaoException("Preparing statement for Insert color error", e);
        }
    }

    @Override
    public void prepareStatementForUpdate(PreparedStatement ps, Color color) throws DaoException {
        try {
            ps.setString(1, color.getValueEn());
            ps.setString(2, color.getValueRu());
            ps.setString(3, String.valueOf(color.getId()));
        } catch (Exception e) {
            LOGGER.error("Preparing statement for Update color error");
            throw new DaoException("Preparing statement for Update color error", e);
        }
    }
}
