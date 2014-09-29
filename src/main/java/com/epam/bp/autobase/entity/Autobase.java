package com.epam.bp.autobase.entity;

import com.epam.bp.autobase.dao.*;
import com.epam.bp.autobase.dao.H2.DaoManager;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Autobase {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Autobase.class);
    private static List<Color> colorList;
    private static List<Model> modelList;
    private static List<Manufacturer> manufacturerList;
    private static List<Vehicle> vehicleList;
    private static List<User> userList;

    private Autobase() {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(daoManager1 -> {
                ColorDao colorDao = daoManager1.getColorDao();
                ModelDao modelDao = daoManager1.getModelDao();
                ManufacturerDao manufacturerDao = daoManager1.getManufacturerDao();
                VehicleDao vehicleDao = daoManager1.getVehicleDao();
                UserDao userDao = daoManager1.getUserDao();
                colorList = colorDao.getAll();
                modelList = modelDao.getAll();
                manufacturerList = manufacturerDao.getAll();
                userList = userDao.getAll();
                vehicleList = vehicleDao.getAll();
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at Autobase constructor while performing transaction");
            throw new RuntimeException("Error at Autobase constructor while performing transaction", e);
        }

    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        Autobase.vehicleList = vehicleList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        Autobase.userList = userList;
    }

    public List<Color> getColorList() {
        return colorList;
    }

    public void setColorList(List<Color> colorList) {
        Autobase.colorList = colorList;
    }

    public List<Model> getModelList() {
        return modelList;
    }

    public void setModelList(List<Model> modelList) {
        Autobase.modelList = modelList;
    }

    public List<Manufacturer> getManufacturerList() {
        return manufacturerList;
    }

    public void setManufacturerList(List<Manufacturer> manufacturerList) {
        Autobase.manufacturerList = manufacturerList;
    }

    public Color getColorById(java.lang.Integer id) {
        Color result = null;
        for (Color color : colorList) {
            if (color.getId().equals(id)) result = color;
        }
        return result;
    }

    public Color getColorByValueEn(String valueEn) {
        Color result = null;
        for (Color color : colorList) {
            if (color.getValueEn().equals(valueEn)) result = color;
        }
        return result;
    }

    public Model getModelById(java.lang.Integer id) {
        Model result = null;
        for (Model model : modelList) {
            if (model.getId().equals(id)) result = model;
        }
        return result;
    }

    public Model getModelByValue(String value) {
        Model result = null;
        for (Model model : modelList) {
            if (model.getValue().equals(value)) result = model;
        }
        return result;
    }

    public Manufacturer getManufacturerById(java.lang.Integer id) {
        Manufacturer result = null;
        for (Manufacturer manufacturer : manufacturerList) {
            if (manufacturer.getId().equals(id)) result = manufacturer;
        }
        return result;
    }

    public Manufacturer getManufacturerByValue(String value) {
        Manufacturer result = null;
        for (Manufacturer manufacturer : manufacturerList) {
            if (manufacturer.getValue().equals(value)) result = manufacturer;
        }
        return result;
    }

    public List<Car> getCarList() {
        List<Car> result = new ArrayList<>();
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getVehicleType().equals("Car")) result.add((Car) vehicle);
        }
        return result;
    }

    public List<Bus> getBusList() {
        List<Bus> result = new ArrayList<>();
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getVehicleType().equals("Bus")) result.add((Bus) vehicle);
        }
        return result;
    }

    public List<Truck> getTruckList() {
        List<Truck> result = new ArrayList<>();
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getVehicleType().equals("Truck")) result.add((Truck) vehicle);
        }
        return result;
    }

    public List<User> getDriverList() {
        List<User> result = new ArrayList<>();
        for (User user : userList) {
            if (user.getRole().equals(User.Role.DRIVER)) result.add(user);
        }
        return result;
    }

    public static Autobase getInstance() {
        return InstanceHolder.instance;
    }

    private static class InstanceHolder {
        private static final Autobase instance = new Autobase();
    }
}