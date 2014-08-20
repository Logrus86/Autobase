package com.epam.bp.autobase.dao;

public class DaoFactory {
    private static UserDao userDao;

    public static UserDao getUserDao() {
        return userDao;
    }
}
