package com.epam.bp.autobase.entity;

import java.util.Locale;
import java.util.ResourceBundle;

public abstract class VehicleBuilder {

    private static final ResourceBundle rb = ResourceBundle.getBundle("vehicles", Locale.ENGLISH);
    static final String[] vhColors = rb.getString("vh.colorList").split(",");
    static final String[] vhManufacturers = rb.getString("vh.manufacturerList").split(",");
    static final String[] vhModels = rb.getString("vh.modelList").split(",");

    public abstract void buildRentPrice();

    public abstract void buildOperable();

    public abstract void buildModel();

    public abstract void buildManufacturer();

    public abstract void buildDriver();

    public abstract void buildProductionYear();

    public abstract void buildColor();

    public abstract void buildMileage();

    public abstract void buildFuelType();
}
