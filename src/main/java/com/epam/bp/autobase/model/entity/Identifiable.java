package com.epam.bp.autobase.model.entity;

import java.io.Serializable;

public interface Identifiable<T extends Identifiable> extends Serializable {

    public Integer getId();

    public T setId(Integer id);

}
