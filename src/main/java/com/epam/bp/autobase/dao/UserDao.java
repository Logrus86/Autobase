package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.model.entity.User;

public interface UserDao extends BaseDao<User> {

    User getByCredentials(String username, String password) throws DaoException;

    User getByUuidString(String uuid) throws DaoException;

}
