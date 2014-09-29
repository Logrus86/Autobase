package com.epam.bp.autobase.entity;

import com.epam.bp.autobase.dao.Identifiable;

public class Manufacturer implements Identifiable<Integer> {
    private Integer id;
    private String value;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public Manufacturer setValue(String value) {
        this.value = value;
        return this;
    }
}