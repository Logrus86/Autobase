package com.epam.bp.autobase.model.dto;

public class ModelAndManufacturerDto extends BaseDto {
    private String value;

    public String getValue() {
        return value;
    }

    public ModelAndManufacturerDto setValue(String value) {
        this.value = value;
        return this;
    }
}
