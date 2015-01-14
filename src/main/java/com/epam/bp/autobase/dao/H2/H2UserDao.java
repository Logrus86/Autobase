package com.epam.bp.autobase.dao.H2;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.UserDao;
import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.pool.ConnectionPool;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class H2UserDao extends H2AbstractDao<Integer, User> implements UserDao {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(H2UserDao.class);

    public H2UserDao(ConnectionPool.ProxyConnection connection) {
        super(connection);
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO "+USER+"("+FIRSTNAME+", "+LASTNAME+", "+DOB+", "+USERNAME+", "+PASSWORD+", "+EMAIL+", "+ROLE+", "+BALANCE+") VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
    }

    @Override
    public String getReadQuery() {
        return "SELECT * FROM "+USER;
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE "+USER+" SET "+FIRSTNAME+" = ?, "+LASTNAME+" = ?, "+DOB+" = ?, "+USERNAME+" = ?, "+PASSWORD+" = ?, "+EMAIL+" = ?, "+ROLE+" = ?, "+BALANCE+" = ? WHERE "+ID+" = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM "+USER+" WHERE "+ID+" = ?;";
    }

    @Override
    public User parseResultSetInstance(ResultSet rs) throws DaoException {
        User user = new User();
        try {
                user.setId(rs.getInt(ID));
                user.setFirstname(rs.getString(FIRSTNAME));
                user.setLastname(rs.getString(LASTNAME));
                user.setDob(rs.getDate(DOB).toString());
                user.setUsername(rs.getString(USERNAME));
                user.setPassword(rs.getString(PASSWORD));
                user.setEmail(rs.getString(EMAIL));
                user.setRole(User.Role.valueOf(rs.getString(ROLE)));
                user.setBalance(rs.getBigDecimal(BALANCE));
        } catch (Exception e) {
            LOGGER.error("Parsing resultSet to user error");
            throw new DaoException("Parsing resultSet to user error", e);
        }
        return user;
    }

    @Override
    public List<User> parseResultSetList(ResultSet rs) throws DaoException {
        List<User> users = new ArrayList<>();
        try {
            while (rs.next()) {
                users.add(parseResultSetInstance(rs));
            }
        } catch (Exception e) {
            LOGGER.error("Parsing resultSet to list of users error");
            throw new DaoException("Parsing resultSet to list of users error", e);
        }
        return users;
    }

    @Override
    public void prepareStatementForInsert(PreparedStatement ps, User user) throws DaoException {
        try {
            ps.setString(1, user.getFirstname());
            ps.setString(2, user.getLastname());
            ps.setString(3, user.getDob());
            ps.setString(4, user.getUsername());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getEmail());
            ps.setString(7, String.valueOf(user.getRole()));
            ps.setString(8, String.valueOf(user.getBalance()));
        } catch (Exception e) {
            LOGGER.error("Preparing statement for Insert user error");
            throw new DaoException("Preparing statement for Insert user error", e);
        }
    }

    @Override
    public void prepareStatementForUpdate(PreparedStatement ps, User user) throws DaoException {
        try {
            ps.setString(1, user.getFirstname());
            ps.setString(2, user.getLastname());
            ps.setString(3, user.getDob());
            ps.setString(4, user.getUsername());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getEmail());
            ps.setString(7, String.valueOf(user.getRole()));
            ps.setString(8, String.valueOf(user.getBalance()));
            ps.setString(9, String.valueOf(user.getId()));
        } catch (Exception e) {
            LOGGER.error("Preparing statement for Update user error");
            throw new DaoException("Preparing statement for Update user error", e);
        }
    }

    @Override
    public List<User> getUsersListByUsername(String username) throws DaoException {
        StringBuilder query = new StringBuilder();
        query.append(getReadQuery()).append(" WHERE "+USERNAME+" = ?;");
        PreparedStatement ps;
        List<User> result;
        try {
            ps = connection.prepareStatement(query.toString());
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            result = parseResultSetList(rs);
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            throw new DaoException("Finding users list by username error", e);
        }
        return result;
    }

    @Override
    public User getByCredentials(String username, String password) throws DaoException {
        StringBuilder query = new StringBuilder();
        query.append(getReadQuery()).append(" WHERE "+USERNAME+" = ? AND "+PASSWORD+" = ?;");
        PreparedStatement ps;
        User result;
        try {
            ps = connection.prepareStatement(query.toString());
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) result = parseResultSetInstance(rs);
            else result = null;
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            throw new DaoException("Finding user by credentials error", e);
        }
        return result;
    }
}