package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.model.entity.User;

public interface UserDao {

    public User getByCredentials(String username, String password) throws DaoException;

}
