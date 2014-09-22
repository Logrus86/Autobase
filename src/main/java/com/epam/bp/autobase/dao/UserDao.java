package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.entity.User;

public interface UserDao extends BaseDao<Integer, User> {
    public User getByUsername(String username) throws DaoException;
}
