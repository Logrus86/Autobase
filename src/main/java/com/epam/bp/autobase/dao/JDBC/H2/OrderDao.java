package com.epam.bp.autobase.dao.JDBC.H2;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.entity.Order;
import com.epam.bp.autobase.pool.ConnectionPool;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDao extends AbstractDao<Integer, Order> implements com.epam.bp.autobase.dao.OrderDao {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OrderDao.class);
    public static final String ID = "ID";
    public static final String VH_ORDER = "VH_ORDER";
    public static final String CLIENT_ID = "CLIENT_ID";
    public static final String VEHICLE_ID = "VEHICLE_ID";
    public static final String DATE_START = "DATE_START";
    public static final String DAYS_COUNT = "DAYS_COUNT";
    public static final String DATE_ORDERED = "DATE_ORDERED";
    public static final String SUM = "SUM";
    public static final String STATUS = "STATUS";
    private static final String ORDER_BY_ID = "ORDER BY ID";

    public OrderDao(ConnectionPool.ProxyConnection connection) {
        super(connection);
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO "+ VH_ORDER +"("+CLIENT_ID+", "+VEHICLE_ID+", "+DATE_START+", "+DAYS_COUNT+", "+DATE_ORDERED+", "+SUM+", "+STATUS+") VALUES(?, ?, ?, ?, ?, ?, ?);";
    }

    @Override
    public String getReadQuery() {
        return "SELECT * FROM "+ VH_ORDER;
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE "+ VH_ORDER +" SET "+CLIENT_ID+" = ?, "+VEHICLE_ID+" = ?, "+DATE_START+" = ?, "+DAYS_COUNT+" = ?, "+DATE_ORDERED+" = ?, "+SUM+" = ?, "+STATUS+" = ? WHERE "+ID+" = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM "+ VH_ORDER +" WHERE "+ID+" = ?;";
    }

    @Override
    public List<Order> parseResultSetList(ResultSet rs) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try {
            while (rs.next()) {
                orders.add(parseResultSetInstance(rs));
            }
        } catch (Exception e) {
            LOGGER.error("Parsing resultSet to list of orders error");
            throw new DaoException("Parsing resultSet to list of orders error", e);
        }
        return orders;
    }

    @Override
    public Order parseResultSetInstance(ResultSet rs) throws DaoException {
        Order order = new Order();
        try {
            order.setId(rs.getInt(ID));
            order.setClientId(rs.getInt(CLIENT_ID));
            order.setVehicleId(rs.getInt(VEHICLE_ID));
            order.setDateStart(rs.getDate(DATE_START));
            order.setDayCount(rs.getInt(DAYS_COUNT));
            order.setDateOrdered(rs.getTimestamp(DATE_ORDERED));
            order.setSum(BigDecimal.valueOf(rs.getInt(SUM)));
            order.setStatus(Order.Status.valueOf(rs.getString(STATUS)));
        } catch (Exception e) {
            LOGGER.error("Parsing resultSet to order error");
            throw new DaoException("Parsing resultSet to order error", e);
        }
        return order;
    }

    @Override
    public void prepareStatementForInsert(PreparedStatement ps, Order order) throws DaoException {
        try {
            ps.setString(1, String.valueOf(order.getClientId()));
            ps.setString(2, String.valueOf(order.getVehicleId()));
            ps.setString(3, order.getDateStart());
            ps.setString(4, String.valueOf(order.getDayCount()));
            ps.setString(5, order.getDateOrdered());
            ps.setString(6, String.valueOf(order.getSum()));
            ps.setString(7, String.valueOf(order.getStatus()));
        } catch (Exception e) {
            LOGGER.error("Preparing statement for Insert order error");
            throw new DaoException("Preparing statement for Insert order error", e);
        }
    }

    @Override
    public void prepareStatementForUpdate(PreparedStatement ps, Order order) throws DaoException {
        try {
            ps.setString(1, String.valueOf(order.getClientId()));
            ps.setString(2, String.valueOf(order.getVehicleId()));
            ps.setString(3, order.getDateStart());
            ps.setString(4, String.valueOf(order.getDayCount()));
            ps.setString(5, order.getDateOrdered());
            ps.setString(6, String.valueOf(order.getSum()));
            ps.setString(7, String.valueOf(order.getStatus()));
            ps.setString(8, String.valueOf(order.getId()));
        } catch (Exception e) {
            LOGGER.error("Preparing statement for Update order error");
            throw new DaoException("Preparing statement for Update order error", e);
        }
    }

    @Override
    public List<Order> getListByClientId(Integer id) throws DaoException {
        StringBuilder query = new StringBuilder();
        query.append(getReadQuery()).append(" WHERE "+CLIENT_ID+" = ? "+ORDER_BY_ID+";");
        PreparedStatement ps;
        List<Order> result;
        try {
            ps = connection.prepareStatement(query.toString());
            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            result = parseResultSetList(rs);
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            throw new DaoException("Finding orders list by clientId error", e);
        }
        return result;
    }

    @Override
    public List<Order> getListByVehicleId(Integer id) throws DaoException {
        StringBuilder query = new StringBuilder();
        query.append(getReadQuery()).append(" WHERE "+VEHICLE_ID+" = ? "+ORDER_BY_ID+";");
        PreparedStatement ps;
        List<Order> result;
        try {
            ps = connection.prepareStatement(query.toString());
            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            result = parseResultSetList(rs);
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            throw new DaoException("Finding orders list by vehicleId error", e);
        }
        return result;
    }

    @Override
    public Order getByDateOrdered(Date date) throws DaoException {
        StringBuilder query = new StringBuilder();
        query.append(getReadQuery()).append(" WHERE "+DATE_ORDERED+" = ?;");
        PreparedStatement ps;
        Order result;
        try {
            ps = connection.prepareStatement(query.toString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ps.setString(1, sdf.format(date));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) result = parseResultSetInstance(rs);
            else result = null;
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            throw new DaoException("Finding order by dateOrdered error", e);
        }
        return result;
    }
}
