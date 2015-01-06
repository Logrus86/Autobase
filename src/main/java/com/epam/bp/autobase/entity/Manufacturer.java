package com.epam.bp.autobase.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@javax.persistence.Entity
public class Manufacturer extends Entity {
    @NotEmpty
    @Pattern(regexp = "[\\w]{2,20}", message = "Incorrect value")
    private String value;

    public String getValue() {
        return value;
    }

    public Manufacturer setValue(String value) {
        this.value = value;
        return this;
    }
}
