package com.epam.bp.autobase.entity;

import java.util.ArrayList;
import java.util.List;

public class DriversBase {
    private static List<User> driversBase = new ArrayList<>();

    public static List<User> getDriversBase() {
        return driversBase;
    }

    public static void setDriversBase(List<User> drivers) {
        driversBase = drivers;
    }

    public static void add(User driver) {
        driversBase.add(driver);
    }

    public static User getDriverById(int id) {
        return driversBase.get(id);
    }
}
