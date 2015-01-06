package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.VehicleDao;
import com.epam.bp.autobase.entity.Entity;
import com.epam.bp.autobase.entity.Vehicle;
import com.epam.bp.autobase.util.Validator;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class SearchAction implements Action {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SearchAction.class);
    private static final ActionResult FIND_LOGINED = new ActionResult(ActionFactory.PAGE_SEARCH_RESULT);
    private static final ActionResult FIND_GUEST = new ActionResult(ActionFactory.PAGE_SEARCH_RESULT_GUEST);
    private static final ActionResult ERR_LOGINED = new ActionResult(ActionFactory.PAGE_MAIN_CLIENT);
    private static final ActionResult ERR_GUEST = new ActionResult(ActionFactory.PAGE_MAIN);
    private static final String RB_ERR_NOTHING_CHECKED = "error.nothing-checked";
    private static final String RB_REG_BEFORE_ORDER = "default.reg_before_order";
    private static final String ERROR = "search_error";
    private static final String REG_ERROR = "reg_error";
    private static final String FOUNDED_LIST = "foundedList";
    private static final String RB_NAME = "i18n.text";
    private static final String ATTR_LOCALE = "locale";
    private static final String ON = "on";
    private static final String TRUE = "TRUE";
    private static final String MODEL_ID = "modelId";
    private static final String MANUFACT_ID = "manufId";
    private static final String COLOR_ID = "colorId";
    private static final String FUEL = "fuel";
    private static final String MILEAGE = "mileage";
    private static final String STAND_N = "standN";
    private static final String PASS_N_BUS = "passNbus";
    private static final String PASS_N_CAR = "passNcar";
    private static final String DOORS_BUS = "doorsBus";
    private static final String DOORS_CAR = "doorsCar";
    private static final String CONDIT = "condit";
    private static final String ENCLOSED = "enclosed";
    private static final String TIPPER = "tipper";
    private static final String NOT_OLDER = "notOlder";
    private static final String RENT = "rent";
    private static final String PAYLOAD = "payload";
    private static final String VH_TYPE = "vhType";
    private static final String NOW_DATE = "nowdate";
    private static final String USER = "user";

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        HttpSession session = request.getSession();
        ServletContext context = session.getServletContext();
        Locale locale = (Locale) context.getAttribute(ATTR_LOCALE);
        ResourceBundle RB = ResourceBundle.getBundle(RB_NAME, locale);
        String error_nothing_checked = RB.getString(RB_ERR_NOTHING_CHECKED);
        RegisterAction.clearRegData(session);
        //check inputs
        String error = Validator.validateRequestParametersMap(request);
        if (!error.isEmpty()) {
            session.setAttribute(ERROR, error);
            session.setAttribute(FOUNDED_LIST, null);
            if (session.getAttribute(USER) == null) return ERR_GUEST;
            return ERR_LOGINED;
        }

        //looking for vehicle
        try {
            VehicleDao vehicleDao = DaoFactory.getInstance().getDaoManager().getVehicleDao();
            Map<String, String> params = parseRequestToMap(request);
            if (params.isEmpty()) {
                LOGGER.info("Nothing was checked.");
                session.setAttribute(ERROR, error_nothing_checked);
                session.setAttribute(FOUNDED_LIST, null);
                if (session.getAttribute(USER) == null) return ERR_GUEST;
                return ERR_LOGINED;
            }
            List<Vehicle> vehicles = vehicleDao.getListByParametersMap(params);

            //vehicle was not found
            if (vehicles.isEmpty()) {
                LOGGER.info("Vehicles wasn't found.");
                session.removeAttribute(ERROR);
                session.removeAttribute(FOUNDED_LIST);
                if (session.getAttribute(USER) == null) return FIND_GUEST;
                return FIND_LOGINED;
            }

            //vehicle was found, all is ok
            LOGGER.info("Vehicle was found.");
            session.setAttribute(FOUNDED_LIST, vehicles);
            session.removeAttribute(ERROR);
        } catch (Exception e) {
            LOGGER.error("Error at SearchAction while searching for vehicle");
            throw new ActionException("Error at SearchAction while searching for vehicle", e);
        }
        if (session.getAttribute(USER) == null) {
            String reg_error = RB.getString(RB_REG_BEFORE_ORDER);
            session.setAttribute(REG_ERROR, reg_error);
            return FIND_GUEST;
        }
        Date date = new Date();
        session.setAttribute(NOW_DATE, date);
        return FIND_LOGINED;
    }

    private Map<String, String> parseRequestToMap(HttpServletRequest req) {
        Map<String, String> result = new TreeMap<>();
        String modelId = req.getParameter(MODEL_ID);
        String manufactId = req.getParameter(MANUFACT_ID);
        String colorId = req.getParameter(COLOR_ID);
        String fuel = req.getParameter(FUEL);
        String mileage = req.getParameter(MILEAGE);
        String standN = req.getParameter(STAND_N);
        String passNbus = req.getParameter(PASS_N_BUS);
        String passNcar = req.getParameter(PASS_N_CAR);
        String doorsBus = req.getParameter(DOORS_BUS);
        String doorsCar = req.getParameter(DOORS_CAR);
        String notOlder = req.getParameter(NOT_OLDER);
        String rent = req.getParameter(RENT);
        String payload = req.getParameter(PAYLOAD);
        //these parameters must be "TRUE" if the same parameter from request is "ON", which means checkbox was checked
        String condit = null;
        String enclosed = null;
        String tipper = null;
        if (ON.equals(req.getParameter(CONDIT))) condit = TRUE;
        if (ON.equals(req.getParameter(ENCLOSED))) enclosed = TRUE;
        if (ON.equals(req.getParameter(TIPPER))) tipper = TRUE;

        if (modelId != null) result.put(com.epam.bp.autobase.dao.H2.VehicleDao.MODEL_ID,modelId);
        if (manufactId != null) result.put(com.epam.bp.autobase.dao.H2.VehicleDao.MANUFACTURER_ID,manufactId);
        if (colorId != null) result.put(com.epam.bp.autobase.dao.H2.VehicleDao.COLOR_ID,colorId);
        if (fuel != null) result.put(com.epam.bp.autobase.dao.H2.VehicleDao.FUEL_TYPE,fuel);
        if (mileage != null) result.put(com.epam.bp.autobase.dao.H2.VehicleDao.MILEAGE,mileage);
        if (standN != null) result.put(com.epam.bp.autobase.dao.H2.VehicleDao.STAND_PL_NUM,standN);
        if (passNbus != null) result.put(com.epam.bp.autobase.dao.H2.VehicleDao.PASS_PL_NUM,passNbus);
        if (passNcar != null) result.put(com.epam.bp.autobase.dao.H2.VehicleDao.PASS_PL_NUM,passNcar);
        if (doorsBus != null) result.put(com.epam.bp.autobase.dao.H2.VehicleDao.DOORS_NUM,doorsBus);
        if (doorsCar != null) result.put(com.epam.bp.autobase.dao.H2.VehicleDao.DOORS_NUM,doorsCar);
        if (condit != null) result.put(com.epam.bp.autobase.dao.H2.VehicleDao.CONDITIONER,condit);
        if (enclosed != null) result.put(com.epam.bp.autobase.dao.H2.VehicleDao.ENCLOSED,enclosed);
        if (tipper != null) result.put(com.epam.bp.autobase.dao.H2.VehicleDao.TIPPER,tipper);
        if (notOlder != null) result.put(com.epam.bp.autobase.dao.H2.VehicleDao.PROD_YEAR,notOlder);
        if (rent != null) result.put(com.epam.bp.autobase.dao.H2.VehicleDao.RENT,rent);
        if (payload != null) result.put(com.epam.bp.autobase.dao.H2.VehicleDao.PAYLOAD,payload);

        //if there are some other parameters, do search by defined vehicle type & operable only
        if (!result.isEmpty()) {
            String vhType = req.getParameter(VH_TYPE);
            if (!vhType.isEmpty()) result.put(com.epam.bp.autobase.dao.H2.VehicleDao.VEHICLE_TYPE,vhType);
            result.put(com.epam.bp.autobase.dao.H2.VehicleDao.OPERABLE,TRUE);
        }
        return result;
    }
}