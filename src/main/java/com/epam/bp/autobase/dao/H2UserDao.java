package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.pool.ConnectionPool;
import com.epam.bp.autobase.util.DateParser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class H2UserDao extends AbstractJDBCDao<Integer, User> implements UserDao {
    // TODO userDao interface, ????? ?????????? userDao (H2, oracle) ?????????? daoFactory, ?????????? ?? ?????????
    // TODO AbstractDbDao interface
    // TODO DaoException
    public H2UserDao(ConnectionPool.ProxyConnection connection) {
        super(connection);
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO USER(FIRSTNAME, LASTNAME, DOB, USERNAME, PASSWORD, EMAIL, ROLE, BALANCE) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    public String getReadQuery() {
        return "SELECT * FROM USER";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE USER SET FIRSTNAME = ?, LASTNAME = ?, DOB = ?, USERNAME = ?, PASSWORD = ?, EMAIL = ?, ROLE = ?, BALANCE = ? WHERE ID = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM USER WHERE id= ?";
    }

    @Override
    public List<User> parseResultSetList(ResultSet rs) {
        List<User> users = new LinkedList<>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("ID"));
                user.setFirstname(rs.getString("FIRSTNAME"));
                user.setLastname(rs.getString("LASTNAME"));
                user.setDob(rs.getDate("DOB"));
                user.setUsername(rs.getString("USERNAME"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setEmail(rs.getString("EMAIL"));
                user.setRole(User.Role.valueOf(rs.getString("ROLE")));
                user.setBalance(rs.getBigDecimal("BALANCE"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User parseResultSetInstance(ResultSet rs) {
        User user = new User();
        try {
            while (rs.next()) {
                user.setId(rs.getInt("ID"));
                user.setFirstname(rs.getString("FIRSTNAME"));
                user.setLastname(rs.getString("LASTNAME"));
                user.setDob(rs.getDate("DOB"));
                user.setUsername(rs.getString("USERNAME"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setEmail(rs.getString("EMAIL"));
                user.setRole(User.Role.valueOf(rs.getString("ROLE")));
                user.setBalance(rs.getBigDecimal("BALANCE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement ps, User user) {
        try {
            ps.setString(1, user.getfirstName());
            ps.setString(2, user.getLastname());
            ps.setString(3, DateParser.DateToString(user.getDob()));
            ps.setString(4, user.getUsername());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getEmail());
            ps.setString(7, String.valueOf(user.getRole()));
            ps.setString(8, String.valueOf(user.getBalance()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement ps, User user) {
        try {
            ps.setString(1, String.valueOf(user.getId()));
            ps.setString(2, user.getfirstName());
            ps.setString(3, user.getLastname());
            ps.setString(4, DateParser.DateToString(user.getDob()));
            ps.setString(5, user.getUsername());
            ps.setString(6, user.getPassword());
            ps.setString(7, user.getEmail());
            ps.setString(8, String.valueOf(user.getRole()));
            ps.setString(9, String.valueOf(user.getBalance()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findByCredentials(String username, String password) throws SQLException, ClassNotFoundException, InterruptedException {
        String query = getReadQuery() + " WHERE USERNAME = ? AND PASSWORD = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, username.toUpperCase());
        ps.setString(2, password.toUpperCase());
        ResultSet rs = ps.executeQuery();
        User user = parseResultSetInstance(rs);
        rs.close();
        ps.close();
        connection.close();
        return user;
    }

    @Override
    public User findByParameters(Map<String, String> paramMap) {
        StringBuilder query = new StringBuilder();
        query.append(getReadQuery()).append(" WHERE ");
        for (String key : paramMap.keySet()) {
            query.append(key.toUpperCase()).append(" = ? AND ");
        }
        query.delete(query.length() - 5, query.length()); //delete last one useless " AND "
        query.append(";");
        PreparedStatement ps;
        User user = null;
        try {
            ps = connection.prepareStatement(query.toString());
            int i = 1;
            for (String key : paramMap.keySet()) {
                ps.setString(i, paramMap.get(key));
                i++;
            }
            ResultSet rs = ps.executeQuery();
            user = parseResultSetInstance(rs);
            rs.close();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}