package com.epam.bp.autobase.model.dto;

public class ColorDto extends BaseDto {
    private String value_en;
    private String value_ru;

    public String getValue_en() {
        return value_en;
    }

    public ColorDto setValue_en(String value_en) {
        this.value_en = value_en;
        return this;
    }

    public String getValue_ru() {
        return value_ru;
    }

    public ColorDto setValue_ru(String value_ru) {
        this.value_ru = value_ru;
        return this;
    }
}
