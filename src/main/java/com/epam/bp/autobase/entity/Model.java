package com.epam.bp.autobase.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Entity
public class Model implements Identifiable {
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

    public Model setValue(String value) {
        this.value = value;
        return this;
    }
}
