package com.epam.bp.autobase.entity.props;

import com.epam.bp.autobase.dao.ColorDao;
import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.DaoFactory;

import java.util.ArrayList;
import java.util.List;

public class ColorsBase {
    private static volatile ColorsBase instance;
    private static List<Color> list = new ArrayList<>();

    private ColorsBase() {
        try {
            ColorDao colorDao = DaoFactory.getInstance().getDaoManager().getColorDao();
            list = colorDao.getAll();
        } catch (DaoException | InterruptedException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<Color> getList() {
        return list;
    }

    public static void setColorList(List<Color> list) {
        ColorsBase.list = list;
    }

    public Color getColor(String valueEn) {
        for (Color color : list) {
            if (color.getValueEn().equals(valueEn)) return color;
        }
        return null;
    }

    public void add(String valueEn, String valueRu) {
        Color color = new Color();
        color.setValueEn(valueEn);
        color.setValueRu(valueRu);
        if (!list.contains(color)) list.add(color);
    }

    public static void remove(Color color) {
        if (!list.contains(color)) list.remove(color);
    }

    public static void remove(String valueEn) {
        for (Color color : list) {
            if (color.getValueEn().equals(valueEn)) list.remove(color);
        }
    }

    public static void remove(String valueEn, String valueRu) {
        for (Color color : list) {
            if (color.getValueEn().equals(valueEn) & (color.getValueRu().equals(valueRu))) list.remove(color);
        }
    }

    public static ColorsBase getInstance() {
        return InstanceHolder.instance;
    }

    private static class InstanceHolder {
        private static ColorsBase instance = new ColorsBase();
    }
}
