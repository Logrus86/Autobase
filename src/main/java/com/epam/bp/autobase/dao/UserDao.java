package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.model.entity.User;

public interface UserDao extends BaseDao<User> {

    public User findByCredentials(String username, String password) throws DaoException;

}
