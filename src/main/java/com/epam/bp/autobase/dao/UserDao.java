package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.entity.User;

import java.util.UUID;

public interface UserDao extends BaseDao<User> {

    User getByCredentials(String username, String password) throws DaoException;

    User getByUuid(UUID uuid) throws DaoException;

}
