package com.epam.bp.autobase.jsp.dto;

import com.epam.bp.autobase.entity.Identifiable;

public abstract class AbstractDto<T extends Identifiable, T2 extends AbstractDto> {

    private Integer id;

    public AbstractDto() {
    }

    public AbstractDto(T entity) {
        id = entity.getId();
    }

    public Integer getId() {
        return id;
    }

    public T2 setId(Integer id) {
        this.id = id;
        return (T2) this;
    }

    public abstract T buildLazyEntity();

    public abstract T buildFullEntity();

    public abstract T overwriteEntityFromDto(T entity);
}
