package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.entity.User;

import java.util.Map;

public interface UserDao extends JDBCDao<Integer, User>{
    public User findByParameters(Map<String,String> parameters) throws DaoException;
}