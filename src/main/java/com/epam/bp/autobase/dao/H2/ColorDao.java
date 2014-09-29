package com.epam.bp.autobase.dao.H2;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.entity.Color;
import com.epam.bp.autobase.pool.ConnectionPool;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ColorDao extends AbstractDao<java.lang.Integer, Color> implements com.epam.bp.autobase.dao.ColorDao {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ColorDao.class);
    public static final String ID = "ID";
    public static final String COLOR = "COLOR";
    public static final String VALUE_EN = "VALUE_EN";
    public static final String VALUE_RU = "VALUE_RU";

    public ColorDao(ConnectionPool.ProxyConnection connection) {
        super(connection);
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO "+COLOR+"("+VALUE_EN+", "+VALUE_RU+") VALUES(?, ?);";
    }

    @Override
    public String getReadQuery() {
        return "SELECT * FROM "+COLOR;
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE "+COLOR+" SET "+VALUE_EN+" = ?, "+VALUE_RU+" = ? WHERE "+ID+" = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM "+COLOR+" WHERE "+ID+" = ?;";
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
            color.setId(rs.getInt(ID));
            color.setValueEn(rs.getString(VALUE_EN));
            color.setValueRu(rs.getString(VALUE_RU));

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

    @Override
    public Color getByValueEn(String valueEn) throws DaoException {
        StringBuilder query = new StringBuilder();
        query.append(getReadQuery()).append(" WHERE "+VALUE_EN+" = ?;");
        PreparedStatement ps;
        Color result;
        try {
            ps = connection.prepareStatement(query.toString());
            ps.setString(1, valueEn);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) result = parseResultSetInstance(rs);
            else result = null;
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            throw new DaoException("Finding color by valueEn error", e);
        }
        return result;
    }

    @Override
    public Color getByValueRu(String valueRu) throws DaoException {
        StringBuilder query = new StringBuilder();
        query.append(getReadQuery()).append(" WHERE "+VALUE_RU+" = ?;");
        PreparedStatement ps;
        Color result;
        try {
            ps = connection.prepareStatement(query.toString());
            ps.setString(1, valueRu);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) result = parseResultSetInstance(rs);
            else result = null;
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            throw new DaoException("Finding color by valueRu error", e);
        }
        return result;
    }
}
