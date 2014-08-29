package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public abstract class AbstractJDBCDao<PK extends Integer, T extends Identifiable<PK>> implements BaseDao<PK, T> {

    protected ConnectionPool.ProxyConnection connection;
    public abstract String getCreateQuery(); //  INSERT INTO [Table] ([columns]) VALUES ([values]);                 C
    public abstract String getReadQuery();   //  SELECT * FROM [Table];                                             R
    public abstract String getUpdateQuery(); //  UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;     U
    public abstract String getDeleteQuery(); //  DELETE FROM [Table] WHERE id= ?;                                   D
    public abstract List<T> parseResultSetList(ResultSet rs) throws DaoException;   // parse resultSet to list of T
    public abstract T parseResultSetInstance(ResultSet rs) throws DaoException;     // parse resultSet to one instance of T
    protected abstract void prepareStatementForInsert(PreparedStatement ps, T object) throws DaoException;// set fields of T to Insert query
    protected abstract void prepareStatementForUpdate(PreparedStatement ps, T object) throws DaoException;// set fields of T to Update query

    @Override
    public void create(T object) throws DaoException {
        String query = getCreateQuery();
        try (PreparedStatement ps = connection.prepareStatement(query);) {
            prepareStatementForInsert(ps, object);
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (Exception e) {
            throw new DaoException(e.getCause());
        }
    }

    @Override
    public T getById(PK id) throws DaoException {
        T object = null;
        String query = getReadQuery();
        query += " WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query);) {
            ps.setInt(1, (Integer) id);
            ResultSet rs = ps.executeQuery();
            object = parseResultSetInstance(rs);
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new DaoException(e.getCause());
        }
        return object;
    }

    @Override
    public void update(T object) throws DaoException {
        String query = getUpdateQuery();
        try (PreparedStatement ps = connection.prepareStatement(query);){
            prepareStatementForUpdate(ps,object);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new DaoException(e.getCause());
        }
    }

    @Override
    public void delete(PK id) throws DaoException {
        String query = getDeleteQuery();
        try (PreparedStatement ps = connection.prepareStatement(query);){
            ps.setObject(1,id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new DaoException(e.getCause());
        }
    }

    @Override
    public void delete(T object) throws DaoException {
        String query = getDeleteQuery();
        try (PreparedStatement ps = connection.prepareStatement(query);){
            ps.setObject(1,object.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new DaoException(e.getCause());
        }
    }

    @Override
    public List<T> getAll() throws DaoException {
        List<T> list = null;
        String sql = getReadQuery();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            list = parseResultSetList(rs);
        } catch (Exception e) {
            throw new DaoException(e.getCause());
        }
        return list;
    }

    public AbstractJDBCDao(ConnectionPool.ProxyConnection connection) {
        this.connection = connection;
    }
}