package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.model.entity.Color;

public interface ColorDao {

    public Color getByValueEn(String valueEn) throws DaoException;

    public Color getByValueRu(String valueRu) throws DaoException;

}
