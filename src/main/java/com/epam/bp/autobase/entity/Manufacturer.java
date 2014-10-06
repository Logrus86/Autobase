package com.epam.bp.autobase.entity;

public class Manufacturer extends Entity {
    private String value;

    public String getValue() {
        return value;
    }

    public Manufacturer setValue(String value) {
        this.value = value;
        return this;
    }
}
