package com.epam.bp.autobase.dao.H2.PersistenceAction;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.H2.DaoManager;

public abstract class PersistenceActionBase {
    protected DaoManager daoManager = null;

    public PersistenceActionBase(DaoManager manager){
        this.daoManager = manager;
    }

    protected abstract void doPersistenceAction(DaoManager manager) throws DaoException;
    protected abstract void doPersistenceCreate(DaoManager manager) throws DaoException;
    protected abstract void doPersistenceUpdate(DaoManager manager) throws DaoException;
    protected abstract void doPersistenceDelete(DaoManager manager) throws DaoException;

    public void doAction() throws DaoException {
        this.daoManager.transactionAndClose(new DaoManager.DaoCommand(){
            public void execute(DaoManager manager) throws DaoException {
                doPersistenceAction(manager);
            }
        });
    }

    public void doCreate() throws DaoException {
        this.daoManager.transactionAndClose(new DaoManager.DaoCommand(){
            public void execute(DaoManager manager) throws DaoException {
                doPersistenceCreate(manager);
            }
        });
    }

    public void doUpdate() throws DaoException {
        this.daoManager.transactionAndClose(new DaoManager.DaoCommand(){
            public void execute(DaoManager manager) throws DaoException {
                doPersistenceUpdate(manager);
            }
        });
    }
    public void doDelete() throws DaoException {
        this.daoManager.transactionAndClose(new DaoManager.DaoCommand(){
            public void execute(DaoManager manager) throws DaoException {
                doPersistenceDelete(manager);
            }
        });
    }
}
