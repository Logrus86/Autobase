package com.epam.bp.autobase.model.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@NamedQuery(name = "Manufacturer.getAll", query = "SELECT m FROM Manufacturer m ORDER BY m.id")
public class Manufacturer implements Identifiable<Manufacturer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @Pattern(regexp = "[\\w]{2,20}", message = "{com.epam.bp.autobase.model.entity.value.message}")
    private String value;

    public Integer getId() {
        return id;
    }

    public Manufacturer setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Manufacturer setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return "Manufacturer{id: " + id + ", value:" + value + '}';
    }
}
