package com.epam.bp.autobase.dao.JDBC;

import com.epam.bp.autobase.dao.*;

public interface JdbcDaoManager extends com.epam.bp.autobase.dao.DaoManager {
    public interface DaoCommand { public void execute(DaoManager daoManager) throws DaoException; }
    public void executeAndClose(DaoCommand command) throws DaoException;
    public void transaction(DaoCommand command) throws DaoException;
    public void transactionAndClose(DaoCommand command) throws DaoException;
}
