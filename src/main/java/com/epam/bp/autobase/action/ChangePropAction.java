package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.ColorDao;
import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.H2.DaoManager;
import com.epam.bp.autobase.dao.ManufacturerDao;
import com.epam.bp.autobase.dao.ModelDao;
import com.epam.bp.autobase.entity.Color;
import com.epam.bp.autobase.entity.Manufacturer;
import com.epam.bp.autobase.entity.Model;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChangePropAction implements Action {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ChangePropAction.class);
    private static final ActionResult ADMIN_COLORS = new ActionResult("admin-colors");
    private static final ActionResult ADMIN_MANUFACTORS = new ActionResult("admin-manufacturers");
    private static final ActionResult ADMIN_MODELS = new ActionResult("admin-models");
    private static final String RB_NAME = "i18n.text";
    private static final String LOCALE = "locale";
    private static String color_busy;
    private static String model_busy;
    private static String manufacturer_busy;
    private static final String COLOR = "color";
    private static final String MANUFACTOR = "manufacturer";
    private static final String MODEL = "model";
    private static final String ENTITY_CHANGES_FLAG = "listsChanged";
    private static final String VALUE_EN = "valueEn";
    private static final String VALUE_RU = "valueRu";
    private static final String VALUE = "value";
    private static final String ERR_COLOR = "color_change_error";
    private static final String ERR_MODEL = "model_change_error";
    private static final String ERR_MANUF = "manuf_change_error";
    private static final String ERR_CREATE = "create_error";
    private static final String SAVE = "save";
    private static final String DELETE = "delete";
    private ActionResult result;
    private String propType;
    private HttpServletRequest request;
    private HttpSession session;

    public ChangePropAction(String propType) {
        this.propType = propType;
    }

    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        request = req;
        session = req.getSession();
        Locale locale = (Locale) session.getAttribute(LOCALE);
        ResourceBundle RB = ResourceBundle.getBundle(RB_NAME, locale);
        color_busy = RB.getString("error.busy-color");
        model_busy = RB.getString("error.busy-model");
        manufacturer_busy = RB.getString("error.busy-manufacturer");

        session.removeAttribute(ERR_MANUF);
        session.removeAttribute(ERR_COLOR);
        session.removeAttribute(ERR_MODEL);
        session.removeAttribute(ERR_CREATE);
        CreateEntityAction.clearEnteredData(session);

        if (request.getParameter(SAVE) != null) {
            if (propType.equals(COLOR)) changeColor();
            if (propType.equals(MANUFACTOR)) changeManufacturer();
            if (propType.equals(MODEL)) changeModel();
        }
        if (request.getParameter(DELETE) != null) {
            if (propType.equals(COLOR)) deleteColor();
            if (propType.equals(MANUFACTOR)) deleteManufacturer();
            if (propType.equals(MODEL)) deleteModel();
        }
        return result;
    }

    private void changeColor() throws ActionException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(daoManager1 -> {
                ColorDao colorDao = daoManager1.getColorDao();
                Integer id = Integer.valueOf(request.getParameter(SAVE));
                Color color = colorDao.getById(id);
                String valueEn = request.getParameter(VALUE_EN);
                String valueRu = request.getParameter(VALUE_RU);
                //check to unique if old value not equals new value, check valueEn
                if ((!color.getValueEn().equals(valueEn)) && (colorDao.getByValueEn(valueEn) != null)) {
                    session.setAttribute(ERR_COLOR, color_busy);
                } else {
                    // valueEn isn't busy, check valueRu
                    if ((!color.getValueRu().equals(valueRu)) && (colorDao.getByValueRu(valueRu) != null)) {
                        session.setAttribute(ERR_COLOR, color_busy);
                    } else {
                        //all is ok, proceed
                        color.setValueEn(valueEn);
                        color.setValueRu(valueRu);
                        colorDao.update(color);
                        session.removeAttribute(ERR_COLOR);
                        session.setAttribute(ENTITY_CHANGES_FLAG, COLOR);
                    }
                }
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at changeColor() while performing transaction");
            throw new ActionException("Error at changeColor() while performing transaction", e);
        }
        result = ADMIN_COLORS;
    }

    private void deleteColor() throws ActionException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(daoManager1 -> {
                ColorDao colorDao = daoManager1.getColorDao();
                Integer id = Integer.valueOf(request.getParameter(DELETE));
                colorDao.delete(id);
                session.setAttribute(ENTITY_CHANGES_FLAG, COLOR);
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at deleteColor() while performing transaction");
            throw new ActionException("Error at deleteColor() while performing transaction", e);
        }
        result = ADMIN_COLORS;
    }

    private void changeModel() throws ActionException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(daoManager1 -> {
                ModelDao modelDao = daoManager1.getModelDao();
                Integer id = Integer.valueOf(request.getParameter(SAVE));
                Model model = modelDao.getById(id);
                String value = request.getParameter(VALUE);
                //check to unique if old value not equals new value
                if ((!model.getValue().equals(value)) && (modelDao.getByValue(value) != null)) {
                    session.setAttribute(ERR_MODEL, model_busy);
                } else {
                    model.setValue(value);
                    modelDao.update(model);
                    session.removeAttribute(ERR_MODEL);
                    session.setAttribute(ENTITY_CHANGES_FLAG, MODEL);
                }
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at changeModel() while performing transaction");
            throw new ActionException("Error at changeModel() while performing transaction", e);
        }
        result = ADMIN_MODELS;
    }

    private void deleteModel() throws ActionException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(daoManager1 -> {
                ModelDao modelDao = daoManager1.getModelDao();
                Integer id = Integer.valueOf(request.getParameter(DELETE));
                modelDao.delete(id);
                session.setAttribute(ENTITY_CHANGES_FLAG, MODEL);
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at deleteModel() while performing transaction");
            throw new ActionException("Error at deleteModel() while performing transaction", e);
        }
        result = ADMIN_MODELS;
    }

    private void changeManufacturer() throws ActionException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(daoManager1 -> {
                ManufacturerDao manufacturerDao = daoManager1.getManufacturerDao();
                Integer id = Integer.valueOf(request.getParameter(SAVE));
                Manufacturer manufacturer = manufacturerDao.getById(id);
                String value = request.getParameter(VALUE);
                //check to unique if old value not equals new value
                if ((!manufacturer.getValue().equals(value)) && (manufacturerDao.getByValue(value) != null)) {
                    session.setAttribute(ERR_MANUF, manufacturer_busy);
                } else {
                    manufacturer.setValue(value);
                    manufacturerDao.update(manufacturer);
                    session.removeAttribute(ERR_MANUF);
                    session.setAttribute(ENTITY_CHANGES_FLAG, MANUFACTOR);
                }
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at changeManufacturer() while performing transaction");
            throw new ActionException("Error at changeManufacturer() while performing transaction", e);
        }
        result = ADMIN_MANUFACTORS;
    }

    private void deleteManufacturer() throws ActionException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(daoManager1 -> {
                ManufacturerDao manufacturerDao = daoManager1.getManufacturerDao();
                Integer id = Integer.valueOf(request.getParameter(DELETE));
                manufacturerDao.delete(id);
                session.setAttribute(ENTITY_CHANGES_FLAG, MANUFACTOR);
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at deleteManufacturer() while performing transaction");
            throw new ActionException("Error at deleteManufacturer() while performing transaction", e);
        }
        result = ADMIN_MANUFACTORS;
    }
}
