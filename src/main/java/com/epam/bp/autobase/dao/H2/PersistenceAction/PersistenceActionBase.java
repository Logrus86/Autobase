package com.epam.bp.autobase.dao.H2.PersistenceAction;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.H2.H2DaoManager;

public abstract class PersistenceActionBase {
    protected H2DaoManager daoManager = null;

    public PersistenceActionBase(H2DaoManager manager){
        this.daoManager = manager;
    }

    protected abstract void doPersistenceAction(H2DaoManager manager) throws DaoException;
    protected abstract void doPersistenceCreate(H2DaoManager manager) throws DaoException;
    protected abstract void doPersistenceUpdate(H2DaoManager manager) throws DaoException;
    protected abstract void doPersistenceDelete(H2DaoManager manager) throws DaoException;

    public void doAction() throws DaoException {
        this.daoManager.transactionAndClose(new H2DaoManager.DaoCommand(){
            public Object execute(H2DaoManager manager) throws DaoException {
                doPersistenceAction(manager);
                return null; // TODO: REALLY NULL ? this line was empty in Jenkov' tutorial
            }
        });
    }

    public void doCreate() throws DaoException {
        this.daoManager.transactionAndClose(new H2DaoManager.DaoCommand(){
            public Object execute(H2DaoManager manager) throws DaoException {
                doPersistenceCreate(manager);
                return null;
            }
        });
    }

    public void doUpdate() throws DaoException {
        this.daoManager.transactionAndClose(new H2DaoManager.DaoCommand(){
            public Object execute(H2DaoManager manager) throws DaoException {
                doPersistenceUpdate(manager);
                return null;
            }
        });
    }
    public void doDelete() throws DaoException {
        this.daoManager.transactionAndClose(new H2DaoManager.DaoCommand(){
            public Object execute(H2DaoManager manager) throws DaoException {
                doPersistenceDelete(manager);
                return null;
            }
        });
    }
}
