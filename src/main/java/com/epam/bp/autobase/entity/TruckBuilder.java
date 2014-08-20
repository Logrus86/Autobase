package com.epam.bp.autobase.entity;

public abstract class TruckBuilder extends VehicleBuilder{
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
