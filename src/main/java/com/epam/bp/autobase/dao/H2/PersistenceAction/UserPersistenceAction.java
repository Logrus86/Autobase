package com.epam.bp.autobase.dao.H2.PersistenceAction;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.H2.H2DaoManager;
import com.epam.bp.autobase.entity.User;

public class UserPersistenceAction extends PersistenceActionBase {
    private User user;

    public UserPersistenceAction(H2DaoManager manager) {
        super(manager);
    }

    public UserPersistenceAction(H2DaoManager manager, User user) {
        super(manager);
        this.user = user;
    }

    @Override
    protected void doPersistenceAction(H2DaoManager manager) throws DaoException {

    }

    @Override
    protected void doPersistenceCreate(H2DaoManager manager) throws DaoException {
        DaoFactory.getInstance().getDaoManager().getUserDao().create(this.user);
    }

    @Override
    protected void doPersistenceUpdate(H2DaoManager DaoManager) throws DaoException {
        User userToUpdate = DaoFactory.getInstance().getDaoManager().getUserDao().getById(this.user.getId());
        if (!userToUpdate.getFirstname().equals(this.user.getFirstname())) userToUpdate.setFirstname(this.user.getFirstname());
        if (!userToUpdate.getLastname().equals(this.user.getLastname())) userToUpdate.setLastname(this.user.getLastname());
        if (!userToUpdate.getDob().equals(this.user.getDob())) userToUpdate.setDob(this.user.getDob());
        if (!userToUpdate.getUsername().equals(this.user.getUsername())) userToUpdate.setUsername(this.user.getUsername());
        if (!userToUpdate.getPassword().equals(this.user.getPassword())) userToUpdate.setPassword(this.user.getPassword());
        if (!userToUpdate.getEmail().equals(this.user.getEmail())) userToUpdate.setEmail(this.user.getEmail());
        if (!userToUpdate.getRole().equals(this.user.getRole())) userToUpdate.setRole(this.user.getRole());
        if (!userToUpdate.getBalance().equals(this.user.getBalance())) userToUpdate.setBalance(this.user.getBalance());
        DaoManager.getUserDao().update(userToUpdate);
    }

    @Override
    protected void doPersistenceDelete(H2DaoManager manager) throws DaoException {

    }
}
