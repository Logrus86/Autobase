package com.epam.bp.autobase.model.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@NamedQuery(name = "Model.getAll", query = "SELECT m FROM Model m ORDER BY m.id")
public class Model implements Identifiable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @Pattern(regexp = "[\\w]{2,20}", message = "{com.epam.bp.autobase.model.entity.value.message}")
    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public Model setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return "Model{id: " + id + ", value:" + value + '}';
    }
}
