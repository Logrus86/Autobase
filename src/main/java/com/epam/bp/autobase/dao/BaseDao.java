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
    public static final String VEHICLE = "VEHICLE";
    public static final String VEHICLE_TYPE = "VEHICLE_TYPE";
    public static final String MODEL_ID = "MODEL_ID";
    public static final String MANUFACTURER_ID = "MANUFACTURER_ID";
    public static final String PROD_YEAR = "PRODUCTION_YEAR";
    public static final String COLOR_ID = "COLOR_ID";
    public static final String MILEAGE = "MILEAGE";
    public static final String FUEL_TYPE = "FUEL_TYPE";
    public static final String OPERABLE = "OPERABLE";
    public static final String RENT = "RENT_PRICE";
    public static final String DRIVER_ID = "DRIVER_ID";
    public static final String STAND_PL_NUM = "STANDING_PLACES_NUMBER";
    public static final String PASS_PL_NUM = "PASSENGER_SEATS_NUMBER";
    public static final String DOORS_NUM = "DOORS_NUMBER";
    public static final String CONDITIONER = "CONDITIONER";
    public static final String PAYLOAD = "MAX_PAYLOAD";
    public static final String ENCLOSED = "ENCLOSED";
    public static final String TIPPER = "TIPPER";

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
