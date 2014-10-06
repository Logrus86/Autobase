package com.epam.bp.autobase.entity;

public class Model extends Entity{
    private String value;

    public String getValue() {
        return value;
    }

    public Model setValue(String value) {
        this.value = value;
        return this;
    }
}
