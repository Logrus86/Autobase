package com.epam.bp.autobase.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class H2AbstractDao<PK extends Integer, T extends Identifiable<PK>> implements BaseDao<PK, T> {

    private ConnectionPool.ProxyConnection proxyConnection;
    public abstract String getCreateQuery(); //  INSERT INTO [Table] ([columns]) VALUES ([values]);                 C
    public abstract String getReadQuery();   //  SELECT * FROM [Table];                                             R
    public abstract String getUpdateQuery(); //  UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;     U
    public abstract String getDeleteQuery(); //  DELETE FROM [Table] WHERE id= ?;                                   D
    public abstract List<T> parseResultSetList(ResultSet rs);   // parse resultSet to list of T
    public abstract T parseResultSetInctance(ResultSet rs);     // parse resultSet to one instance of T
    protected abstract void prepareStatementForInsert(PreparedStatement ps, T object); // set fields of T to Insert query argues
    protected abstract void prepareStatementForUpdate(PreparedStatement ps, T object); // set fields of T to Update query argues

    @Override
    public void create(T object) {
        String query = getCreateQuery();
        try (PreparedStatement ps = proxyConnection.prepareStatement(query);) {
            prepareStatementForInsert(ps, object);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T getById(PK id) {
        T object = null;
        String query = getReadQuery();
        query += " WHERE id = ?";
        try (PreparedStatement ps = proxyConnection.prepareStatement(query);) {
            ps.setInt(1, (Integer) id);
            ResultSet rs = ps.executeQuery();
            object = parseResultSetInctance(rs);
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public void update(T object) {
        String query = getUpdateQuery();
        try (PreparedStatement ps = proxyConnection.prepareStatement(query);){
            prepareStatementForUpdate(ps,object);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(PK id) {
        String query = getDeleteQuery();
        try (PreparedStatement ps = proxyConnection.prepareStatement(query);){
            ps.setObject(1,id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(T object) {
        String query = getDeleteQuery();
        try (PreparedStatement ps = proxyConnection.prepareStatement(query);){
            ps.setObject(1,object.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> getAll() {
        List<T> list = null;
        String sql = getReadQuery();
        try {
            PreparedStatement ps = proxyConnection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            list = parseResultSetList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public H2AbstractDao(ConnectionPool.ProxyConnection proxyConnection) {
        this.proxyConnection = proxyConnection;
    }
}