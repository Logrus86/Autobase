package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.ColorDao;
import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.DaoManager;
import com.epam.bp.autobase.dao.ManufacturerDao;
import com.epam.bp.autobase.dao.ModelDao;
import com.epam.bp.autobase.entity.Color;
import com.epam.bp.autobase.entity.Manufacturer;
import com.epam.bp.autobase.entity.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChangeSpecAction implements Action {
    private static final ActionResult ADMIN_COLORS = new ActionResult(ActionFactory.PAGE_ADMIN_COLORS, true);
    private static final ActionResult ADMIN_MANUFACTURERS = new ActionResult(ActionFactory.PAGE_ADMIN_MANUFACTURERS, true);
    private static final ActionResult ADMIN_MODELS = new ActionResult(ActionFactory.PAGE_ADMIN_MODELS, true);
    private static final String RB_NAME = "i18n.text";
    private static final String LOCALE = "locale";
    private static final String SAVE = "save";
    private static final String DELETE = "delete";
    private static final String ERROR_COLOR = "color_change_error";
    private static final String ERROR_MODEL = "model_change_error";
    private static final String ERROR_MANUFACTURER = "manufacturer_change_error";
    private static final String ERROR_CREATE = "create_error";
    private static final String ERROR_BUSY_COLOR = "error.busy-color";
    private static final String ERROR_BUSY_MODEL = "error.busy-model";
    private static final String ERROR_BUSY_MANUFACTURER = "error.busy-manufacturer";
    private static final String COLOR = "Color";
    private static final String MODEL = "Model";
    private static final String MANUFACTURER = "Manufacturer";
    private static final String VALUE_EN = "valueEn";
    private static final String VALUE_RU = "valueRu";
    private static final String VALUE = "value";
    private static String color_busy;
    private static String model_busy;
    private static String manufacturer_busy;
    private static ActionResult result;
    private static HttpServletRequest request;
    private static HttpSession session;
    private String spec_type;

    public ChangeSpecAction(String spec_type) {
        this.spec_type = spec_type;
    }

    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        request = req;
        session = req.getSession();
        Locale locale = (Locale) session.getAttribute(LOCALE);
        ResourceBundle RB = ResourceBundle.getBundle(RB_NAME, locale);
        color_busy = RB.getString(ERROR_BUSY_COLOR);
        model_busy = RB.getString(ERROR_BUSY_MODEL);
        manufacturer_busy = RB.getString(ERROR_BUSY_MANUFACTURER);

        session.removeAttribute(ERROR_MANUFACTURER);
        session.removeAttribute(ERROR_COLOR);
        session.removeAttribute(ERROR_MODEL);
        session.removeAttribute(ERROR_CREATE);
        CreateEntityAction.clearEnteredData(session);

        if (request.getParameter(SAVE) != null) {
            switch (spec_type) {
                case COLOR:
                    changeColor();
                    break;
                case MODEL:
                    changeModel();
                    break;
                case MANUFACTURER:
                    changeManufacturer();
                    break;
            }
        }
        if (request.getParameter(DELETE) != null) {
            switch (spec_type) {
                case COLOR:
                    deleteColor();
                    break;
                case MODEL:
                    deleteModel();
                    break;
                case MANUFACTURER:
                    deleteManufacturer();
                    break;
            }
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
                if ((!color.getValue_en().equals(valueEn)) && (colorDao.getByValueEn(valueEn) != null)) {
                    session.setAttribute(ERROR_COLOR, color_busy);
                } else {
                    // valueEn isn't busy, check valueRu
                    if ((!color.getValue_ru().equals(valueRu)) && (colorDao.getByValueRu(valueRu) != null)) {
                        session.setAttribute(ERROR_COLOR, color_busy);
                    } else {
                        //all is ok, proceed
                        color.setValue_en(valueEn);
                        color.setValue_ru(valueRu);
                        colorDao.update(color);
                        session.removeAttribute(ERROR_COLOR);

                    }
                }
            });
            daoFactory.releaseContext();
    //        as.setToContext(COLOR, session.getServletContext());
        } catch (Exception e) {
    //        LOGGER.error("Error at changeColor() while performing transaction");
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

            });
            daoFactory.releaseContext();
     //       as.setToContext(COLOR, session.getServletContext());
        } catch (Exception e) {
     //       LOGGER.error("Error at deleteColor() while performing transaction");
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
                    session.setAttribute(ERROR_MODEL, model_busy);
                } else {
                    model.setValue(value);
                    modelDao.update(model);
                    session.removeAttribute(ERROR_MODEL);
                }
            });
            daoFactory.releaseContext();
      //      as.setToContext(MODEL, session.getServletContext());
        } catch (Exception e) {
      //      LOGGER.error("Error at changeModel() while performing transaction");
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
            });
            daoFactory.releaseContext();
      //      as.setToContext(MODEL, session.getServletContext());
        } catch (Exception e) {
       //     LOGGER.error("Error at deleteModel() while performing transaction");
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
                    session.setAttribute(ERROR_MANUFACTURER, manufacturer_busy);
                } else {
                    manufacturer.setValue(value);
                    manufacturerDao.update(manufacturer);
                    session.removeAttribute(ERROR_MANUFACTURER);
                }
            });
            daoFactory.releaseContext();
   //         as.setToContext(MANUFACTURER, session.getServletContext());
        } catch (Exception e) {
    //        LOGGER.error("Error at changeManufacturer() while performing transaction");
            throw new ActionException("Error at changeManufacturer() while performing transaction", e);
        }
        result = ADMIN_MANUFACTURERS;
    }

    private void deleteManufacturer() throws ActionException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(daoManager1 -> {
                ManufacturerDao manufacturerDao = daoManager1.getManufacturerDao();
                Integer id = Integer.valueOf(request.getParameter(DELETE));
                manufacturerDao.delete(id);

            });
            daoFactory.releaseContext();
     //       as.setToContext(MANUFACTURER, session.getServletContext());
        } catch (Exception e) {
    //        LOGGER.error("Error at deleteManufacturer() while performing transaction");
            throw new ActionException("Error at deleteManufacturer() while performing transaction", e);
        }
        result = ADMIN_MANUFACTURERS;
    }
}
