package com.epam.bp.autobase.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Pattern;

@javax.persistence.Entity
public class Color extends Entity {
    @NotEmpty
    @Pattern(regexp = "[A-Z]{1}[a-z]{2,20}", message = "Incorrect value_en")
    @Column(name = "VALUE_EN")
    private String valueEn;

    @NotEmpty
    @Pattern(regexp = "[А-Я]{1}[а-я]{2,20}", message = "Incorrect value_ru")
    @Column(name = "VALUE_RU")
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