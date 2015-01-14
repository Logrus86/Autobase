package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.entity.Color;

public interface ColorDao extends BaseDao<Integer, Color> {
    public Color getByValueEn(String valueEn) throws DaoException;
    public Color getByValueRu(String valueRu) throws DaoException;
}
