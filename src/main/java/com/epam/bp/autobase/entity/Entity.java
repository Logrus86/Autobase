package com.epam.bp.autobase.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;


@javax.persistence.Entity
public abstract class Entity implements Serializable{


    @Id
    @GeneratedValue
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
