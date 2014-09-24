package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.entity.User;

import java.util.List;

public interface UserDao extends BaseDao<Integer, User> {
    public List<User> getUsersListByUsername(String username) throws DaoException;
    public User getByCredentials(String username, String password) throws DaoException;
}
