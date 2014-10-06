package com.epam.bp.autobase.entity;

public class Color extends Entity {
    private String valueEn;
    private String valueRu;

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

}