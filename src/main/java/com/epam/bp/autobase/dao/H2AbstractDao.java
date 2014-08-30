package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public abstract class H2AbstractDao<PK extends Integer,T extends Identifiable<PK>> implements JDBCDao<PK,T>{
    protected ConnectionPool.ProxyConnection connection;
    public H2AbstractDao(ConnectionPool.ProxyConnection connection) {
        this.connection = connection;
    }

    @Override
    public void create(T object) throws DaoException {
        String query = getCreateQuery();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            prepareStatementForInsert(ps, object);
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (Exception e) {
            throw new DaoException("Error while preparing statement at 'create' method", e.getCause());
        }
    }

    @Override
    public T getById(PK id) throws DaoException {
        T object;
        String query = getReadQuery();
        query += " WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, (Integer) id);
            ResultSet rs = ps.executeQuery();
            object = parseResultSetInstance(rs);
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new DaoException("Error while preparing statement at 'getById' method", e.getCause());
        }
        return object;
    }

    @Override
    public void update(T object) throws DaoException {
        String query = getUpdateQuery();
        try (PreparedStatement ps = connection.prepareStatement(query)){
            prepareStatementForUpdate(ps,object);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new DaoException("Error while preparing statement at 'update' method", e.getCause());
        }
    }

    @Override
    public void delete(PK id) throws DaoException {
        String query = getDeleteQuery();
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setObject(1,id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new DaoException("Error while preparing statement at 'delete(id)' method", e.getCause());
        }
    }

    @Override
    public void delete(T object) throws DaoException {
        String query = getDeleteQuery();
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setObject(1,object.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new DaoException("Error while preparing statement at 'delete(object)' method", e.getCause());
        }
    }

    @Override
    public List<T> getAll() throws DaoException {
        List<T> list;
        String sql = getReadQuery();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            list = parseResultSetList(rs);
        } catch (Exception e) {
            throw new DaoException("Error while preparing statement at 'getAll' method", e.getCause());
        }
        return list;
    }
}