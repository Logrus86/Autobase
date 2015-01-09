package com.epam.bp.autobase.entity;

public abstract class Entity {
    //these string fields are used by actions to get required parameter from request
    public static final String VEHICLE = "vehicle";
    public static final String BUS = "Bus";
    public static final String CAR = "Car";
    public static final String TRUCK = "Truck";
    public static final String RENT = "rentPrice";
    public static final String DRIVER_ID = "driverId";
    public static final String MODEL_ID = "model_id";
    public static final String MANUFACTURER_ID = "manufacturer_id";
    public static final String COLOR_ID = "color_id";
    public static final String FUEL_TYPE = "fuelType";
    public static final String MILEAGE = "mileage";
    public static final String WITH_CONDITIONER = "withConditioner";
    public static final String MAX_PAYLOAD = "maxPayload";
    public static final String ENCLOSED = "enclosed";
    public static final String TIPPER = "tipper";
    public static final String OPERABLE = "operable";
    public static final String PRODUCTION_YEAR = "year_prod";
    public static final String PASS_SEATS_NUM = "passN";
    public static final String DOORS_NUM = "doorsN";
    public static final String STAND_PLACES_NUM = "standN";
    public static final String USER = "user";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String EMAIL = "email";
    public static final String DOB = "dob";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String BALANCE = "balance";
    public static final String ROLE = "role";
    public static final String COLOR = "Color";
    public static final String MODEL = "Model";
    public static final String MANUFACTURER = "Manufacturer";
    public static final String VALUE_EN = "valueEn";
    public static final String VALUE_RU = "valueRu";
    public static final String VALUE = "value";
    public static final String ORDER = "order";
    public static final String DRIVER_VEHICLES = "driverVehicles";

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
