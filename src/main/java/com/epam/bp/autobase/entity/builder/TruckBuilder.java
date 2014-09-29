package com.epam.bp.autobase.entity.builder;

import com.epam.bp.autobase.entity.Truck;

public abstract class TruckBuilder extends VehicleBuilder {
    protected Truck truck;

    public Truck getTruck() {
        return truck;
    }

    public void createNewTruckProduct() {
        truck = new Truck();
    }

    public abstract void buildMaxPayload();

    public abstract void buildEnclosed();

    public abstract void buildTipper();
}
