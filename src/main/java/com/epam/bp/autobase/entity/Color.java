package com.epam.bp.autobase.entity;

import com.epam.bp.autobase.dao.Identifiable;

public class Color implements Identifiable<Integer> {
    private String valueEn;
    private String valueRu;
    private Integer id;

    public Color setValueEn(String valueEn) {
        this.valueEn = valueEn;
        return this;
    }

    public Color setValueRu(String valueRu) {
        this.valueRu = valueRu;
        return this;
    }

    public String getValueEn() {
        return valueEn;
    }

    public String getValueRu() {
        return valueRu;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }
}