package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.entity.Identifiable;
import java.util.List;
import java.util.Map;

public interface BaseDao<PK, T extends Identifiable> {
    public static final String ID = "ID";
    public static final String ORDER_BY = "ORDER BY";
    public static final String COLOR = "COLOR";
    public static final String VALUE_EN = "VALUE_EN";
    public static final String VALUE_RU = "VALUE_RU";
    public static final String VALUE = "VALUE";
    public static final String MANUFACTURER = "MANUFACTURER";
    public static final String MODEL = "MODEL";
    public static final String VH_ORDER = "VH_ORDER";
    public static final String CLIENT_ID = "CLIENT_ID";
    public static final String VEHICLE_ID = "VEHICLE_ID";
    public static final String DATE_START = "DATE_START";
    public static final String DAYS_COUNT = "DAYS_COUNT";
    public static final String DATE_ORDERED = "DATE_ORDERED";
    public static final String SUM = "SUM";
    public static final String STATUS = "STATUS";
    public static final String ORDER_BY_ID = "ORDER BY ID";
    public static final String USER = "USER";
    public static final String FIRSTNAME = "FIRSTNAME";
    public static final String LASTNAME = "LASTNAME";
    public static final String DOB = "DOB";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String EMAIL = "EMAIL";
    public static final String ROLE = "ROLE";
    public static final String BALANCE = "BALANCE";

    public void create(T object) throws DaoException;

    public T getById(PK id) throws DaoException;

    public void update(T object) throws DaoException;

    public void delete(PK id) throws DaoException;

    public void delete(T object) throws DaoException;

    public List<T> getAll() throws DaoException;

    public List<T> getAllSortedBy(String columnName) throws DaoException;

    public List<T> getListByParameter(String param_name, String param_value) throws DaoException;

    public List<T> getListByParametersMap(Map<String, String> params) throws DaoException;

}
