package com.epam.bp.autobase.model.dto;

import com.epam.bp.autobase.model.entity.Color;

public class ColorDto extends AbstractDto<Color, ColorDto> {
    private String value_en;
    private String value_ru;

    public ColorDto() {
    }
    
    public ColorDto(Color color) {
        super(color);
        value_en = color.getValue_en();
        value_ru = color.getValue_ru();
    }

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

    @Override
    public Color buildEntity() {
        return new Color()
                .setId(getId())
                .setValue_en(value_en)
                .setValue_ru(value_ru);
    }
}
