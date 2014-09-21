package com.epam.bp.autobase.entity.props;

import com.epam.bp.autobase.dao.Identifiable;

public class Color implements Identifiable<Integer> {
    private String valueEn;
    private String valueRu;
    private Integer id;

    public void setValueEn(String valueEn) {
        this.valueEn = valueEn;
    }

    public void setValueRu(String valueRu) {
        this.valueRu = valueRu;
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