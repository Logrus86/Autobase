package com.epam.bp.autobase.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@NamedQuery(name="Manufacturer.getAll",  query = "SELECT m FROM Manufacturer m ORDER BY m.id")
public class Manufacturer implements Identifiable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

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
