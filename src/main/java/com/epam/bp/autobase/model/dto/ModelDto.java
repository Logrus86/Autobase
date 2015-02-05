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

    public ModelDto setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public Model buildLazyEntity() {
        return new Model()
                .setId(getId())
                .setValue(value);
    }

    @Override
    public Model buildFullEntity() {
        return buildLazyEntity();
    }
}
