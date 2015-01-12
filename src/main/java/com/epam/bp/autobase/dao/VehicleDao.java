package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.entity.Vehicle;

public interface VehicleDao extends BaseDao<Integer, Vehicle> {
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
}
