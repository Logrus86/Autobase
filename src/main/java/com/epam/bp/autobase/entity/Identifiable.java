package com.epam.bp.autobase.entity;

import java.io.Serializable;

public interface Identifiable<T extends Identifiable> extends Serializable {

    Integer getId();

    T setId(Integer id);

}
