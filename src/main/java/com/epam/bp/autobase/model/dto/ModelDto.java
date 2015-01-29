package com.epam.bp.autobase.model.dto;

import com.epam.bp.autobase.model.entity.Model;

public class ModelDto extends AbstractDto<Model, ModelDto> {

    private String value;

    public ModelDto() {
    }

    public ModelDto(Model entity) {
        super(entity);
        value = entity.getValue();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public Model buildEntity() {
        return new Model()
                .setId(getId())
                .setValue(value);
    }
}
