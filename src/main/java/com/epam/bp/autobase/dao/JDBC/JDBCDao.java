package com.epam.bp.autobase.dao.JDBC;

import com.epam.bp.autobase.dao.BaseDao;
import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.entity.Identifiable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public interface JDBCDao<PK, T extends Identifiable> extends BaseDao<PK, T> {

    public abstract String getCreateQuery(); //  INSERT INTO [Table] ([columns]) VALUES ([values]);                 C
    public abstract String getReadQuery();   //  SELECT * FROM [Table];                                             R
    public abstract String getUpdateQuery(); //  UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;     U
    public abstract String getDeleteQuery(); //  DELETE FROM [Table] WHERE id= ?;                                   D
    public abstract List<T> parseResultSetList(ResultSet rs) throws DaoException;   // parse resultSet to list of T
    public abstract T parseResultSetInstance(ResultSet rs) throws DaoException;     // parse resultSet to one instance of T
    public abstract void prepareStatementForInsert(PreparedStatement ps, T object) throws DaoException;// set fields of T to Insert query
    public abstract void prepareStatementForUpdate(PreparedStatement ps, T object) throws DaoException;// set fields of T to Update query
}