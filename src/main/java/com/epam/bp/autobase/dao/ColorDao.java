package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.model.entity.Color;

public interface ColorDao extends BaseDao<Color> {

    Color getByValueEn(String valueEn) throws DaoException;

    Color getByValueRu(String valueRu) throws DaoException;

}
