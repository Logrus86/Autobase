package com.epam.bp.autobase.dao;

public interface DaoManager {
    public UserDao getUserDao();
    public VehicleDao getVehicleDao();
    public ColorDao getColorDao();
    public ModelDao getModelDao();
    public ManufacturerDao getManufacturerDao();
    public OrderDao getOrderDao();
    public interface DaoCommand { public void execute(DaoManager daoManager) throws DaoException; }
    public void executeAndClose(DaoCommand command) throws DaoException;
    public void transaction(DaoCommand command) throws DaoException;
    public void transactionAndClose(DaoCommand command) throws DaoException;
}
