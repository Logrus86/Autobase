package com.epam.bp.autobase.entity.props;

import com.epam.bp.autobase.dao.Identifiable;

public class Model implements Identifiable<Integer>{
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

    public void setValue(String value) {
        this.value = value;
    }
}
