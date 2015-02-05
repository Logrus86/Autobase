package com.epam.bp.autobase.model.dto;

import com.epam.bp.autobase.model.entity.Manufacturer;

public class ManufacturerDto extends AbstractDto<Manufacturer, ManufacturerDto> {

    private String value;

    public ManufacturerDto() {
    }

    public ManufacturerDto(Manufacturer entity) {
        super(entity);
        value = entity.getValue();
    }

    public String getValue() {
        return value;
    }

    public ManufacturerDto setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public Manufacturer buildLazyEntity() {
        return new Manufacturer()
                .setId(getId())
                .setValue(value);
    }

    @Override
    public Manufacturer buildFullEntity() {
        return buildLazyEntity();
    }
}
