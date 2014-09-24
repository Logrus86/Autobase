package com.epam.bp.autobase.dao;

public interface DaoManager {
    public UserDao getUserDao();
    public VehicleDao getVehicleDao();
    public ColorDao getColorDao();
    public ModelDao getModelDao();
    public ManufacturerDao getManufacturerDao();
}
