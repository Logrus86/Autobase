package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.*;
import com.epam.bp.autobase.dao.H2.DaoManager;
import com.epam.bp.autobase.entity.*;
import com.epam.bp.autobase.util.Validator;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.ResourceBundle;

public class CreateEntityAction implements Action {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CreateEntityAction.class);
    private static final ActionResult BUS_PAGE = new ActionResult("admin-buses", true);
    private static final ActionResult CAR_PAGE = new ActionResult("admin-cars", true);
    private static final ActionResult TRUCK_PAGE = new ActionResult("admin-trucks", true);
    private static final ActionResult USER_PAGE = new ActionResult("admin-users", true);
    private static final ActionResult COLOR_PAGE = new ActionResult("admin-colors", true);
    private static final ActionResult MODEL_PAGE = new ActionResult("admin-models", true);
    private static final ActionResult MANUFACTURER_PAGE = new ActionResult("admin-manufacturers", true);
    private static final String RB_NAME = "i18n.text";
    private static final String LOCALE = "locale";
    private static String color_busy;
    private static String model_busy;
    private static String manufacturer_busy;
    private static final String RENT = "rentPrice";
    private static final String DRIVER_ID = "driverId";
    private static final String MODEL_ID = "model_id";
    private static final String MANUFACTURER_ID = "manufacturer_id";
    private static final String COLOR_ID = "color_id";
    private static final String FUELTYPE = "fuelType";
    private static final String MILEAGE = "mileage";
    private static final String PRODUCTIONYEAR = "year_prod";
    private static final String VEHICLE = "vehicle";
    private static final String BUS = "bus";
    private static final String CAR = "car";
    private static final String TRUCK = "truck";
    private static final String PASS_SEATS_NUM = "passN";
    private static final String DOORS_NUM = "doorsN";
    private static final String STAND_PLACES_NUM = "standN";
    private static final String WITH_COND = "withConditioner";
    private static final String MAX_PAYLOAD = "maxPayload";
    private static final String ENCLOSED = "enclosed";
    private static final String TIPPER = "tipper";
    private static final String ERROR = "create_error";
    private static final String USER = "user";
    private static final String COLOR = "color";
    private static final String MODEL = "model";
    private static final String MANUFACTURER = "manufacturer";
    private static final String ENTITY_CHANGES_FLAG = "listsChanged";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String EMAIL = "email";
    private static final String DOB = "dob";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String BALANCE = "balance";
    private static final String ROLE = "role";
    private static final String VALUE_EN = "valueEn";
    private static final String VALUE_RU = "valueRu";
    private static final String VALUE = "value";
    private static final String ON = "on";
    private ActionResult result;
    private String entityName;
    private HttpServletRequest request;
    private HttpSession session;

    public CreateEntityAction(String entityName) {
        this.entityName = entityName;
    }

    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        request = req;
        session = req.getSession();
        session.removeAttribute(ERROR);
        Locale locale = (Locale) session.getAttribute(LOCALE);
        ResourceBundle RB = ResourceBundle.getBundle(RB_NAME, locale);
        color_busy = RB.getString("error.busy-color");
        model_busy = RB.getString("error.busy-model");
        manufacturer_busy = RB.getString("error.busy-manufacturer");

        clearEnteredData(session);

        //validate data
        String error = Validator.validateRequestParametersMap(request);
        if (!error.isEmpty()) {
            session.setAttribute(ERROR, error);
            forwardEnteredData();
            if (COLOR.equals(entityName)) return COLOR_PAGE;
            if (MODEL.equals(entityName)) return MODEL_PAGE;
            if (MANUFACTURER.equals(entityName)) return MANUFACTURER_PAGE;
            if (BUS.equals(entityName)) return BUS_PAGE;
            if (CAR.equals(entityName)) return CAR_PAGE;
            if (TRUCK.equals(entityName)) return TRUCK_PAGE;
            if (USER.equals(entityName)) return USER_PAGE;
        }

        if (USER.equals(entityName)) createUser();
        if ((BUS.equals(entityName)) || (CAR.equals(entityName)) || (TRUCK.equals(entityName)))
            createVehicle();
        if ((COLOR.equals(entityName)) || (MODEL.equals(entityName)) || (MANUFACTURER.equals(entityName)))
            createProp();

        return result;
    }

    private void createProp() throws ActionException {
        Object prop = null;
        String valueEn = request.getParameter(VALUE_EN);
        String valueRu = request.getParameter(VALUE_RU);
        String value = request.getParameter(VALUE);
        if (COLOR.equals(entityName)) prop = new Color()
                .setValueEn(valueEn)
                .setValueRu(valueRu);
        if (MODEL.equals(entityName)) prop = new Model().setValue(value);
        if (MANUFACTURER.equals(entityName)) prop = new Manufacturer().setValue(value);
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            final Object finalProp = prop;
            daoManager.executeAndClose(daoManager1 -> {
                if (COLOR.equals(entityName)) {
                    ColorDao colorDao = daoManager1.getColorDao();
                    if ((colorDao.getByValueEn(valueEn) != null) || (colorDao.getByValueRu(valueRu) != null)) {
                        session.setAttribute(ERROR, color_busy);
                        forwardEnteredData();
                    } else {
                        colorDao.create((Color) finalProp);
                        session.setAttribute(ENTITY_CHANGES_FLAG, COLOR);
                    }
                }
                if (MODEL.equals(entityName)) {
                    ModelDao modelDao = daoManager1.getModelDao();
                    if (modelDao.getByValue(value) != null) {
                        session.setAttribute(ERROR, model_busy);
                        forwardEnteredData();
                    } else {
                        modelDao.create((Model) finalProp);
                        session.setAttribute(ENTITY_CHANGES_FLAG, MODEL);
                    }
                }
                if (MANUFACTURER.equals(entityName)) {
                    ManufacturerDao manufacturerDao = daoManager1.getManufacturerDao();
                    if (manufacturerDao.getByValue(value) != null) {
                        session.setAttribute(ERROR, manufacturer_busy);
                        forwardEnteredData();
                    } else {
                        manufacturerDao.create((Manufacturer) finalProp);
                        session.setAttribute(ENTITY_CHANGES_FLAG, MANUFACTURER);
                    }
                }
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at createProp() while performing transaction");
            throw new ActionException("Error at createProp() while performing transaction", e);
        }
        if (COLOR.equals(entityName)) result = COLOR_PAGE;
        if (MODEL.equals(entityName)) result = MODEL_PAGE;
        if (MANUFACTURER.equals(entityName)) result = MANUFACTURER_PAGE;
    }

    private void createUser() throws ActionException {
        String firstname = request.getParameter(FIRSTNAME);
        String lastname = request.getParameter(LASTNAME);
        String email = request.getParameter(EMAIL);
        String dob = request.getParameter(DOB);
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        BigDecimal balance = new BigDecimal(request.getParameter(BALANCE));
        User.Role role = User.Role.valueOf(request.getParameter(ROLE));

        User user = new User()
                .setFirstname(firstname)
                .setLastname(lastname)
                .setEmail(email)
                .setDob(dob)
                .setUsername(username)
                .setPassword(password)
                .setBalance(balance)
                .setRole(role);
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            UserDao userDao = daoManager.getUserDao();
            final User finalUser = user;
            daoManager.executeAndClose(daoManager1 -> userDao.create(finalUser));
            session.setAttribute(ENTITY_CHANGES_FLAG, USER);
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at createUser() while performing transaction");
            throw new ActionException("Error at createUser() while performing transaction", e);
        }
        result = USER_PAGE;
    }

    private void createVehicle() throws ActionException {
        Vehicle vehicle = null;
        Integer prodYear = Integer.valueOf(request.getParameter(PRODUCTIONYEAR));
        Integer colorId = Integer.valueOf(request.getParameter(COLOR_ID));
        Integer manufacturerId = Integer.valueOf(request.getParameter(MANUFACTURER_ID));
        Integer modelId = Integer.valueOf(request.getParameter(MODEL_ID));
        Vehicle.Fuel fuel = Vehicle.Fuel.valueOf(request.getParameter(FUELTYPE));
        BigDecimal mileage = new BigDecimal(request.getParameter(MILEAGE));
        BigDecimal rentPrice = new BigDecimal(request.getParameter(RENT));
        Integer driverId = Integer.parseInt(request.getParameter(DRIVER_ID));
        Integer doorsN;
        Integer passN;
        Integer standN;
        BigDecimal payload;
        Boolean enclosed;
        Boolean tipper;

        if (BUS.equals(entityName)) {
            doorsN = Integer.parseInt(request.getParameter(DOORS_NUM));
            passN = Integer.parseInt(request.getParameter(PASS_SEATS_NUM));
            standN = Integer.parseInt(request.getParameter(STAND_PLACES_NUM));
            vehicle = new Bus()
                    .setDoorsNumber(doorsN)
                    .setPassengerSeatsNumber(passN)
                    .setStandingPlacesNumber(standN);
        }
        if (CAR.equals(entityName)) {
            doorsN = Integer.parseInt(request.getParameter(DOORS_NUM));
            passN = Integer.parseInt(request.getParameter(PASS_SEATS_NUM));
            Boolean withConditioner = ON.equals(request.getParameter(WITH_COND));
            vehicle = new Car()
                    .setDoorsNumber(doorsN)
                    .setPassengerSeatsNumber(passN)
                    .setWithConditioner(withConditioner);
        }
        if (TRUCK.equals(entityName)) {
            payload = new BigDecimal(request.getParameter(MAX_PAYLOAD));
            enclosed = ON.equals(request.getParameter(ENCLOSED));
            tipper = ON.equals(request.getParameter(TIPPER));
            vehicle = new Truck()
                    .setTipper(tipper)
                    .setEnclosed(enclosed)
                    .setMaxPayload(payload);
        }
        vehicle.setOperable(true);
        vehicle.setColorId(colorId);
        vehicle.setModelId(modelId);
        vehicle.setManufacturerId(manufacturerId);
        vehicle.setProductionYear(prodYear);
        vehicle.setFuelType(fuel);
        vehicle.setMileage(mileage);
        vehicle.setRentPrice(rentPrice);
        vehicle.setDriverId(driverId);
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            VehicleDao vehicleDao = daoManager.getVehicleDao();
            final Vehicle finalVehicle = vehicle;
            daoManager.executeAndClose(daoManager1 -> vehicleDao.create(finalVehicle));
            session.setAttribute(ENTITY_CHANGES_FLAG, VEHICLE);
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at createVehicle() while performing transaction");
            throw new ActionException("Error at createVehicle() while performing transaction", e);
        }
        if (BUS.equals(entityName)) result = BUS_PAGE;
        if (CAR.equals(entityName)) result = CAR_PAGE;
        if (TRUCK.equals(entityName)) result = TRUCK_PAGE;
    }

    private void forwardEnteredData() {
        if (COLOR.equals(entityName)) {
            session.setAttribute(VALUE_EN, request.getParameter(VALUE_EN));
            session.setAttribute(VALUE_RU, request.getParameter(VALUE_RU));
        }
        if ((MODEL.equals(entityName)) || (MANUFACTURER.equals(entityName))) {
            session.setAttribute(VALUE, request.getParameter(VALUE));
        }
        if (BUS.equals(entityName)) {
            session.setAttribute(MODEL_ID, request.getParameter(MODEL_ID));
            session.setAttribute(MANUFACTURER_ID, request.getParameter(MANUFACTURER_ID));
            session.setAttribute(PRODUCTIONYEAR, request.getParameter(PRODUCTIONYEAR));
            session.setAttribute(COLOR_ID, request.getParameter(COLOR_ID));
            session.setAttribute(FUELTYPE, request.getParameter(FUELTYPE));
            session.setAttribute(MILEAGE, request.getParameter(MILEAGE));
            session.setAttribute(RENT, request.getParameter(RENT));
            session.setAttribute(DRIVER_ID, request.getParameter(DRIVER_ID));
            session.setAttribute(DOORS_NUM, request.getParameter(DOORS_NUM));
            session.setAttribute(PASS_SEATS_NUM, request.getParameter(PASS_SEATS_NUM));
            session.setAttribute(STAND_PLACES_NUM, request.getParameter(STAND_PLACES_NUM));
        }
        if (CAR.equals(entityName)) {
            session.setAttribute(MODEL_ID, request.getParameter(MODEL_ID));
            session.setAttribute(MANUFACTURER_ID, request.getParameter(MANUFACTURER_ID));
            session.setAttribute(PRODUCTIONYEAR, request.getParameter(PRODUCTIONYEAR));
            session.setAttribute(COLOR_ID, request.getParameter(COLOR_ID));
            session.setAttribute(FUELTYPE, request.getParameter(FUELTYPE));
            session.setAttribute(MILEAGE, request.getParameter(MILEAGE));
            session.setAttribute(RENT, request.getParameter(RENT));
            session.setAttribute(DRIVER_ID, request.getParameter(DRIVER_ID));
            session.setAttribute(DOORS_NUM, request.getParameter(DOORS_NUM));
            session.setAttribute(PASS_SEATS_NUM, request.getParameter(PASS_SEATS_NUM));
            session.setAttribute(WITH_COND, request.getParameter(WITH_COND));
        }
        if (TRUCK.equals(entityName)) {
            session.setAttribute(MODEL_ID, request.getParameter(MODEL_ID));
            session.setAttribute(MANUFACTURER_ID, request.getParameter(MANUFACTURER_ID));
            session.setAttribute(PRODUCTIONYEAR, request.getParameter(PRODUCTIONYEAR));
            session.setAttribute(COLOR_ID, request.getParameter(COLOR_ID));
            session.setAttribute(FUELTYPE, request.getParameter(FUELTYPE));
            session.setAttribute(MILEAGE, request.getParameter(MILEAGE));
            session.setAttribute(RENT, request.getParameter(RENT));
            session.setAttribute(DRIVER_ID, request.getParameter(DRIVER_ID));
            session.setAttribute(MAX_PAYLOAD, request.getParameter(MAX_PAYLOAD));
            session.setAttribute(ENCLOSED, request.getParameter(ENCLOSED));
            session.setAttribute(TIPPER, request.getParameter(TIPPER));
        }
        if (USER.equals(entityName)) {
            session.setAttribute(FIRSTNAME, request.getParameter(FIRSTNAME));
            session.setAttribute(LASTNAME, request.getParameter(LASTNAME));
            session.setAttribute(DOB, request.getParameter(DOB));
            session.setAttribute(USERNAME, request.getParameter(USERNAME));
            session.setAttribute(EMAIL, request.getParameter(EMAIL));
            session.setAttribute(ROLE, request.getParameter(ROLE));
            session.setAttribute(BALANCE, request.getParameter(BALANCE));
        }
    }

    public static void clearEnteredData(HttpSession session) {
            session.removeAttribute(VALUE_EN);
            session.removeAttribute(VALUE_RU);
            session.removeAttribute(VALUE);
            session.removeAttribute(MODEL_ID);
            session.removeAttribute(MANUFACTURER_ID);
            session.removeAttribute(PRODUCTIONYEAR);
            session.removeAttribute(COLOR_ID);
            session.removeAttribute(FUELTYPE);
            session.removeAttribute(MILEAGE);
            session.removeAttribute(RENT);
            session.removeAttribute(DRIVER_ID);
            session.removeAttribute(DOORS_NUM);
            session.removeAttribute(PASS_SEATS_NUM);
            session.removeAttribute(STAND_PLACES_NUM);
            session.removeAttribute(MODEL_ID);
            session.removeAttribute(MANUFACTURER_ID);
            session.removeAttribute(PRODUCTIONYEAR);
            session.removeAttribute(COLOR_ID);
            session.removeAttribute(FUELTYPE);
            session.removeAttribute(MILEAGE);
            session.removeAttribute(RENT);
            session.removeAttribute(DRIVER_ID);
            session.removeAttribute(DOORS_NUM);
            session.removeAttribute(PASS_SEATS_NUM);
            session.removeAttribute(WITH_COND);
            session.removeAttribute(MODEL_ID);
            session.removeAttribute(MANUFACTURER_ID);
            session.removeAttribute(PRODUCTIONYEAR);
            session.removeAttribute(COLOR_ID);
            session.removeAttribute(FUELTYPE);
            session.removeAttribute(MILEAGE);
            session.removeAttribute(RENT);
            session.removeAttribute(DRIVER_ID);
            session.removeAttribute(MAX_PAYLOAD);
            session.removeAttribute(ENCLOSED);
            session.removeAttribute(TIPPER);
            session.removeAttribute(FIRSTNAME);
            session.removeAttribute(LASTNAME);
            session.removeAttribute(DOB);
            session.removeAttribute(USERNAME);
            session.removeAttribute(EMAIL);
            session.removeAttribute(ROLE);
            session.removeAttribute(BALANCE);
    }
}
