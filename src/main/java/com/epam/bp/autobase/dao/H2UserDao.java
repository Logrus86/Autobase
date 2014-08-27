package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.util.DateParser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class H2UserDao extends H2AbstractDao<Integer,User> {
    ConnectionPool.ProxyConnection proxyConnection;

    public H2UserDao(ConnectionPool.ProxyConnection proxyConnection) {
        super(proxyConnection);
    }

    public User findByCredentials(String username, String password) throws SQLException, ClassNotFoundException, InterruptedException {
        ConnectionPool cp = ConnectionPool.getInstance();
        proxyConnection = cp.getConnection();
        String query = getReadQuery()+" WHERE USERNAME = ? AND PASSWORD = ?;";
        PreparedStatement preparedStatement = proxyConnection.prepareStatement(query);
        preparedStatement.setString(1, username.toUpperCase());
        preparedStatement.setString(2, password.toUpperCase());
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = parseResultSetInctance(resultSet);
        resultSet.close();
        preparedStatement.close();
        cp.returnConnection(proxyConnection);
        return user;
    }

    public void add(User user) throws SQLException, ClassNotFoundException, InterruptedException {
        ConnectionPool cp = ConnectionPool.getInstance();
        proxyConnection = cp.getConnection();
        create(user);
        cp.returnConnection(proxyConnection);
    }

    //=======================================================================================================

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
    public User parseResultSetInctance(ResultSet rs) {
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
}