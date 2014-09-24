package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.*;
import com.epam.bp.autobase.dao.H2.DaoManager;
import com.epam.bp.autobase.entity.props.Color;
import com.epam.bp.autobase.entity.props.Manufacturer;
import com.epam.bp.autobase.entity.props.Model;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

public class ChangePropAction implements Action {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ChangePropAction.class);
    private static final ActionResult ADMIN_COLORS = new ActionResult("admin-colors", true);
    private static final ActionResult ADMIN_MANUFACTORS = new ActionResult("admin-manufacturers", true);
    private static final ActionResult ADMIN_MODELS = new ActionResult("admin-models", true);
    private static final ResourceBundle RB = ResourceBundle.getBundle("i18n.text");
    private static final String COLOR_BUSY = RB.getString("error.busy-color");
    private static final String MODEL_BUSY = RB.getString("error.busy-model");
    private static final String MANUFACTURER_BUSY = RB.getString("error.busy-manufacturer");
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

        if (request.getParameter(SAVE) != null) {
            if (propType.equals(COLOR)) ChangeColor();
            if (propType.equals(MANUFACTOR)) ChangeManufacturer();
            if (propType.equals(MODEL)) ChangeModel();
        }
        if (request.getParameter(DELETE) != null) {
            if (propType.equals(COLOR)) DeleteColor();
            if (propType.equals(MANUFACTOR)) DeleteManufacturer();
            if (propType.equals(MODEL)) DeleteModel();
        }
        return result;
    }

    private void ChangeColor() throws ActionException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(new DaoManager.DaoCommand() {
                @Override
                public void execute(DaoManager daoManager) throws DaoException {
                    ColorDao colorDao = daoManager.getColorDao();
                    Integer id = Integer.valueOf(request.getParameter(SAVE));
                    Color color = colorDao.getById(id);
                    String valueEn = request.getParameter(VALUE_EN);
                    String valueRu = request.getParameter(VALUE_RU);
                    //check to unique if old value not equals new value, check valueEn
                    if ((!color.getValueEn().equals(valueEn)) && (colorDao.getByValueEn(valueEn) != null)) {
                        session.setAttribute(ERR_COLOR, COLOR_BUSY);
                    } else {
                        // valueEn isn't busy, check valueRu
                        if ((!color.getValueRu().equals(valueRu)) && (colorDao.getByValueRu(valueRu) != null)) {
                            session.setAttribute(ERR_COLOR, COLOR_BUSY);
                        } else {
                            //all is ok, proceed
                            color.setValueEn(valueEn);
                            color.setValueRu(valueRu);
                            colorDao.update(color);
                            session.removeAttribute(ERR_COLOR);
                            session.setAttribute(ENTITY_CHANGES_FLAG, COLOR);
                        }
                    }
                }
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at ChangeColor() while performing transaction");
            throw new ActionException("Error at ChangeColor() while performing transaction", e);
        }
        result = ADMIN_COLORS;
    }

    private void DeleteColor() throws ActionException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(new DaoManager.DaoCommand() {
                @Override
                public void execute(DaoManager daoManager) throws DaoException {
                    ColorDao colorDao = daoManager.getColorDao();
                    Integer id = Integer.valueOf(request.getParameter(DELETE));
                    colorDao.delete(id);
                    session.setAttribute(ENTITY_CHANGES_FLAG, COLOR);
                }
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at DeleteColor() while performing transaction");
            throw new ActionException("Error at DeleteColor() while performing transaction", e);
        }
    }

    private void ChangeModel() throws ActionException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(new DaoManager.DaoCommand() {
                @Override
                public void execute(DaoManager daoManager) throws DaoException {
                    ModelDao modelDao = daoManager.getModelDao();
                    Integer id = Integer.valueOf(request.getParameter(SAVE));
                    Model model = modelDao.getById(id);
                    String value = request.getParameter(VALUE);
                    //check to unique if old value not equals new value
                    if ((!model.getValue().equals(value)) && (modelDao.getByValue(value) != null)) {
                        session.setAttribute(ERR_MODEL, MODEL_BUSY);
                    } else {
                        model.setValue(value);
                        modelDao.update(model);
                        session.removeAttribute(ERR_MODEL);
                        session.setAttribute(ENTITY_CHANGES_FLAG, MODEL);
                    }
                }
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at ChangeModel() while performing transaction");
            throw new ActionException("Error at ChangeModel() while performing transaction", e);
        }
        result = ADMIN_MODELS;
    }

    private void DeleteModel() throws ActionException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(new DaoManager.DaoCommand() {
                @Override
                public void execute(DaoManager daoManager) throws DaoException {
                    ModelDao modelDao = daoManager.getModelDao();
                    Integer id = Integer.valueOf(request.getParameter(DELETE));
                    modelDao.delete(id);
                    session.setAttribute(ENTITY_CHANGES_FLAG, MODEL);
                }
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at DeleteModel() while performing transaction");
            throw new ActionException("Error at DeleteModel() while performing transaction", e);
        }
    }

    private void ChangeManufacturer() throws ActionException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(new DaoManager.DaoCommand() {
                @Override
                public void execute(DaoManager daoManager) throws DaoException {
                    ManufacturerDao manufacturerDao = daoManager.getManufacturerDao();
                    Integer id = Integer.valueOf(request.getParameter(SAVE));
                    Manufacturer manufacturer = manufacturerDao.getById(id);
                    String value = request.getParameter(VALUE);
                    //check to unique if old value not equals new value
                    if ((!manufacturer.getValue().equals(value)) && (manufacturerDao.getByValue(value) != null)) {
                        session.setAttribute(ERR_MANUF, MANUFACTURER_BUSY);
                    } else {
                        manufacturer.setValue(value);
                        manufacturerDao.update(manufacturer);
                        session.removeAttribute(ERR_MANUF);
                        session.setAttribute(ENTITY_CHANGES_FLAG, MANUFACTOR);
                    }
                }
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at ChangeManufacturer() while performing transaction");
            throw new ActionException("Error at ChangeManufacturer() while performing transaction", e);
        }
        result = ADMIN_MANUFACTORS;
    }

    private void DeleteManufacturer() throws ActionException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(new DaoManager.DaoCommand() {
                @Override
                public void execute(DaoManager daoManager) throws DaoException {
                    ManufacturerDao manufacturerDao = daoManager.getManufacturerDao();
                    Integer id = Integer.valueOf(request.getParameter(DELETE));
                    manufacturerDao.delete(id);
                    session.setAttribute(ENTITY_CHANGES_FLAG, MANUFACTOR);
                }
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at DeleteManufacturer() while performing transaction");
            throw new ActionException("Error at DeleteManufacturer() while performing transaction", e);
        }
    }
}
