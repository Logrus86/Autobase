package com.epam.bp.autobase.dao.H2;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.Identifiable;
import com.epam.bp.autobase.dao.JDBCDao;
import com.epam.bp.autobase.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class H2AbstractDao<PK extends Integer, T extends Identifiable<PK>> implements JDBCDao<PK, T> {
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
            throw new DaoException("Error while preparing statement at 'create' method", e);
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
            if (rs.next()) object = parseResultSetInstance(rs);
            else object = null;
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new DaoException("Error while preparing statement at 'getById' method", e);
        }
        return object;
    }

    @Override
    public void update(T object) throws DaoException {
        String query = getUpdateQuery();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            prepareStatementForUpdate(ps, object);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new DaoException("Error while preparing statement at 'update' method", e);
        }
    }

    @Override
    public void delete(PK id) throws DaoException {
        String query = getDeleteQuery();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new DaoException("Error while preparing statement at 'delete(by id)' method", e);
        }
    }

    @Override
    public void delete(T object) throws DaoException {
        String query = getDeleteQuery();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, object.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new DaoException("Error while preparing statement at 'delete(by object)' method", e);
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
            throw new DaoException("Error while preparing statement at 'getAll' method", e);
        }
        return list;
    }

    @Override
    public List<T> findByParams(Map<String, String> params) throws DaoException {
        StringBuilder query = new StringBuilder();
        query.append(getReadQuery()).append(" WHERE 1 = 1");
        for (String key : params.keySet()) {
            query.append(" AND ").append(key.toUpperCase()).append(" = ?");
        }
        query.append(";");
        PreparedStatement ps;
        List<T> result = new ArrayList<>();
        try {
            ps = connection.prepareStatement(query.toString());
            int i = 1;
            for (String key : params.keySet()) {
                ps.setString(i, params.get(key));
                i++;
            }
            ResultSet rs = ps.executeQuery();
            result = parseResultSetList(rs);
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            if (result != null) throw new DaoException("Finding " + result.getClass().getSimpleName() + " by parameters error", e);
            else throw new DaoException("Finding entity by parameters error, entity result = null", e);
        }
        return result;
    }
}