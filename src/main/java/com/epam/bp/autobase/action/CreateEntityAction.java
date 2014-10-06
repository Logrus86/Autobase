package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.*;
import com.epam.bp.autobase.dao.H2.DaoManager;
import com.epam.bp.autobase.entity.*;
import com.epam.bp.autobase.util.AttributeSetter;
import com.epam.bp.autobase.util.Validator;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.ResourceBundle;

public class CreateEntityAction implements Action {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CreateEntityAction.class);
    private static final ActionResult BUS_PAGE = new ActionResult(ActionFactory.PAGE_ADMIN_BUSES);
    private static final ActionResult CAR_PAGE = new ActionResult(ActionFactory.PAGE_ADMIN_CARS);
    private static final ActionResult TRUCK_PAGE = new ActionResult(ActionFactory.PAGE_ADMIN_TRUCKS);
    private static final ActionResult USER_PAGE = new ActionResult(ActionFactory.PAGE_ADMIN_USERS);
    private static final ActionResult COLOR_PAGE = new ActionResult(ActionFactory.PAGE_ADMIN_COLORS);
    private static final ActionResult MODEL_PAGE = new ActionResult(ActionFactory.PAGE_ADMIN_MODELS);
    private static final ActionResult MANUFACTURER_PAGE = new ActionResult(ActionFactory.PAGE_ADMIN_MANUFACTURERS);
    private static final String RB_NAME = "i18n.text";
    private static final String LOCALE = "locale";
    private static final String ERROR = "create_error";
    private static final String ON = "on";
    private static final String ERROR_BUSY_COLOR = "error.busy-color";
    private static final String ERROR_BUSY_MODEL = "error.busy-model";
    private static final String ERROR_BUSY_MANUFACTURER = "error.busy-manufacturer";
    private static String color_busy;
    private static String model_busy;
    private static String manufacturer_busy;
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
        ServletContext context = session.getServletContext();
        Locale locale = (Locale) context.getAttribute(LOCALE);
        ResourceBundle RB = ResourceBundle.getBundle(RB_NAME, locale);
        color_busy = RB.getString(ERROR_BUSY_COLOR);
        model_busy = RB.getString(ERROR_BUSY_MODEL);
        manufacturer_busy = RB.getString(ERROR_BUSY_MANUFACTURER);

        clearEnteredData(session);

        //validate data
        String error = Validator.validateRequestParametersMap(request);
        if (!error.isEmpty()) {
            session.setAttribute(ERROR, error);
            forwardEnteredData();
            if (Entity.COLOR.equals(entityName)) return COLOR_PAGE;
            if (Entity.MODEL.equals(entityName)) return MODEL_PAGE;
            if (Entity.MANUFACTURER.equals(entityName)) return MANUFACTURER_PAGE;
            if (Entity.BUS.equals(entityName)) return BUS_PAGE;
            if (Entity.CAR.equals(entityName)) return CAR_PAGE;
            if (Entity.TRUCK.equals(entityName)) return TRUCK_PAGE;
            if (Entity.USER.equals(entityName)) return USER_PAGE;
        }

        if (Entity.USER.equals(entityName)) createUser();
        if ((Entity.BUS.equals(entityName)) || (Entity.CAR.equals(entityName)) || (Entity.TRUCK.equals(entityName)))
            createVehicle();
        if ((Entity.COLOR.equals(entityName)) || (Entity.MODEL.equals(entityName)) || (Entity.MANUFACTURER.equals(entityName)))
            createSpec();

        return result;
    }

    private void createSpec() throws ActionException {
        Object prop = null;
        String valueEn = request.getParameter(Entity.VALUE_EN);
        String valueRu = request.getParameter(Entity.VALUE_RU);
        String value = request.getParameter(Entity.VALUE);
        if (Entity.COLOR.equals(entityName)) prop = new Color()
                .setValueEn(valueEn)
                .setValueRu(valueRu);
        if (Entity.MODEL.equals(entityName)) prop = new Model().setValue(value);
        if (Entity.MANUFACTURER.equals(entityName)) prop = new Manufacturer().setValue(value);
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            final Object finalSpec = prop;
            daoManager.executeAndClose(daoManager1 -> {
                if (Entity.COLOR.equals(entityName)) {
                    ColorDao colorDao = daoManager1.getColorDao();
                    if ((colorDao.getByValueEn(valueEn) != null) || (colorDao.getByValueRu(valueRu) != null)) {
                        session.setAttribute(ERROR, color_busy);
                        forwardEnteredData();
                    } else {
                        colorDao.create((Color) finalSpec);
                        AttributeSetter.setEntityToContext(Entity.COLOR, session.getServletContext());
                    }
                }
                if (Entity.MODEL.equals(entityName)) {
                    ModelDao modelDao = daoManager1.getModelDao();
                    if (modelDao.getByValue(value) != null) {
                        session.setAttribute(ERROR, model_busy);
                        forwardEnteredData();
                    } else {
                        modelDao.create((Model) finalSpec);
                        AttributeSetter.setEntityToContext(Entity.MODEL, session.getServletContext());
                    }
                }
                if (Entity.MANUFACTURER.equals(entityName)) {
                    ManufacturerDao manufacturerDao = daoManager1.getManufacturerDao();
                    if (manufacturerDao.getByValue(value) != null) {
                        session.setAttribute(ERROR, manufacturer_busy);
                        forwardEnteredData();
                    } else {
                        manufacturerDao.create((Manufacturer) finalSpec);
                        AttributeSetter.setEntityToContext(Entity.MANUFACTURER, session.getServletContext());
                    }
                }
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at createSpec() while performing transaction");
            throw new ActionException("Error at createSpec() while performing transaction", e);
        }
        if (Entity.COLOR.equals(entityName)) result = COLOR_PAGE;
        if (Entity.MODEL.equals(entityName)) result = MODEL_PAGE;
        if (Entity.MANUFACTURER.equals(entityName)) result = MANUFACTURER_PAGE;
    }

    private void createUser() throws ActionException {
        String firstname = request.getParameter(Entity.FIRSTNAME);
        String lastname = request.getParameter(Entity.LASTNAME);
        String email = request.getParameter(Entity.EMAIL);
        String dob = request.getParameter(Entity.DOB);
        String username = request.getParameter(Entity.USERNAME);
        String password = request.getParameter(Entity.PASSWORD);
        BigDecimal balance = new BigDecimal(request.getParameter(Entity.BALANCE));
        User.Role role = User.Role.valueOf(request.getParameter(Entity.ROLE));

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
            daoFactory.releaseContext();
            AttributeSetter.setEntityToSession(Entity.USER, session);
        } catch (Exception e) {
            LOGGER.error("Error at createUser() while performing transaction");
            throw new ActionException("Error at createUser() while performing transaction", e);
        }
        result = USER_PAGE;
    }

    private void createVehicle() throws ActionException {
        Vehicle vehicle = null;
        Integer prodYear = Integer.valueOf(request.getParameter(Entity.PRODUCTION_YEAR));
        Integer colorId = Integer.valueOf(request.getParameter(Entity.COLOR_ID));
        Integer manufacturerId = Integer.valueOf(request.getParameter(Entity.MANUFACTURER_ID));
        Integer modelId = Integer.valueOf(request.getParameter(Entity.MODEL_ID));
        Vehicle.Fuel fuel = Vehicle.Fuel.valueOf(request.getParameter(Entity.FUEL_TYPE));
        BigDecimal mileage = new BigDecimal(request.getParameter(Entity.MILEAGE));
        BigDecimal rentPrice = new BigDecimal(request.getParameter(Entity.RENT));
        Integer driverId = Integer.parseInt(request.getParameter(Entity.DRIVER_ID));
        Integer doorsN;
        Integer passN;
        Integer standN;
        BigDecimal payload;
        Boolean enclosed;
        Boolean tipper;
        if (Entity.BUS.equals(entityName)) {
            doorsN = Integer.parseInt(request.getParameter(Entity.DOORS_NUM));
            passN = Integer.parseInt(request.getParameter(Entity.PASS_SEATS_NUM));
            standN = Integer.parseInt(request.getParameter(Entity.STAND_PLACES_NUM));
            vehicle = new Bus()
                    .setDoorsNumber(doorsN)
                    .setPassengerSeatsNumber(passN)
                    .setStandingPlacesNumber(standN)
                    .setType(Vehicle.Type.BUS);
        }
        if (Entity.CAR.equals(entityName)) {
            doorsN = Integer.parseInt(request.getParameter(Entity.DOORS_NUM));
            passN = Integer.parseInt(request.getParameter(Entity.PASS_SEATS_NUM));
            Boolean withConditioner = ON.equals(request.getParameter(Entity.WITH_CONDITIONER));
            vehicle = new Car()
                    .setDoorsNumber(doorsN)
                    .setPassengerSeatsNumber(passN)
                    .setWithConditioner(withConditioner)
                    .setType(Vehicle.Type.CAR);
        }
        if (Entity.TRUCK.equals(entityName)) {
            payload = new BigDecimal(request.getParameter(Entity.MAX_PAYLOAD));
            enclosed = ON.equals(request.getParameter(Entity.ENCLOSED));
            tipper = ON.equals(request.getParameter(Entity.TIPPER));
            vehicle = new Truck()
                    .setTipper(tipper)
                    .setEnclosed(enclosed)
                    .setMaxPayload(payload)
                    .setType(Vehicle.Type.TRUCK);
        }
        if (vehicle != null) {
            vehicle.setOperable(true);
            vehicle.setColorId(colorId);
            vehicle.setModelId(modelId);
            vehicle.setManufacturerId(manufacturerId);
            vehicle.setProductionYear(prodYear);
            vehicle.setFuelType(fuel);
            vehicle.setMileage(mileage);
            vehicle.setRentPrice(rentPrice);
            vehicle.setDriverId(driverId);
        }
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            VehicleDao vehicleDao = daoManager.getVehicleDao();
            final Vehicle finalVehicle = vehicle;
            daoManager.executeAndClose(daoManager1 -> vehicleDao.create(finalVehicle));
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at createVehicle() while performing transaction");
            throw new ActionException("Error at createVehicle() while performing transaction", e);
        }
        if (Entity.BUS.equals(entityName)) {
            AttributeSetter.setEntityToSession(Entity.BUS, session);
            result = BUS_PAGE;
        }
        if (Entity.CAR.equals(entityName)) {
            AttributeSetter.setEntityToSession(Entity.CAR, session);
            result = CAR_PAGE;
        }
        if (Entity.TRUCK.equals(entityName)) {
            AttributeSetter.setEntityToSession(Entity.TRUCK, session);
            result = TRUCK_PAGE;
        }
    }

    private void forwardEnteredData() {
        if (Entity.COLOR.equals(entityName)) {
            session.setAttribute(Entity.VALUE_EN, request.getParameter(Entity.VALUE_EN));
            session.setAttribute(Entity.VALUE_RU, request.getParameter(Entity.VALUE_RU));
            return;
        }
        if ((Entity.MODEL.equals(entityName)) || (Entity.MANUFACTURER.equals(entityName))) {
            session.setAttribute(Entity.VALUE, request.getParameter(Entity.VALUE));
            return;
        }
        if ((Entity.BUS.equals(entityName)) || (Entity.CAR.equals(entityName)) || (Entity.TRUCK.equals(entityName))){
            session.setAttribute(Entity.MODEL_ID, request.getParameter(Entity.MODEL_ID));
            session.setAttribute(Entity.MANUFACTURER_ID, request.getParameter(Entity.MANUFACTURER_ID));
            session.setAttribute(Entity.PRODUCTION_YEAR, request.getParameter(Entity.PRODUCTION_YEAR));
            session.setAttribute(Entity.COLOR_ID, request.getParameter(Entity.COLOR_ID));
            session.setAttribute(Entity.FUEL_TYPE, request.getParameter(Entity.FUEL_TYPE));
            session.setAttribute(Entity.MILEAGE, request.getParameter(Entity.MILEAGE));
            session.setAttribute(Entity.RENT, request.getParameter(Entity.RENT));
            session.setAttribute(Entity.DRIVER_ID, request.getParameter(Entity.DRIVER_ID));
            session.setAttribute(Entity.DOORS_NUM, request.getParameter(Entity.DOORS_NUM));
            session.setAttribute(Entity.PASS_SEATS_NUM, request.getParameter(Entity.PASS_SEATS_NUM));
            session.setAttribute(Entity.STAND_PLACES_NUM, request.getParameter(Entity.STAND_PLACES_NUM));
        }
        if (Entity.BUS.equals(entityName)) {
            session.setAttribute(Entity.DOORS_NUM, request.getParameter(Entity.DOORS_NUM));
            session.setAttribute(Entity.PASS_SEATS_NUM, request.getParameter(Entity.PASS_SEATS_NUM));
            session.setAttribute(Entity.WITH_CONDITIONER, request.getParameter(Entity.WITH_CONDITIONER));
            return;
        }
        if (Entity.CAR.equals(entityName)) {
            session.setAttribute(Entity.DOORS_NUM, request.getParameter(Entity.DOORS_NUM));
            session.setAttribute(Entity.PASS_SEATS_NUM, request.getParameter(Entity.PASS_SEATS_NUM));
            session.setAttribute(Entity.WITH_CONDITIONER, request.getParameter(Entity.WITH_CONDITIONER));
            return;
        }
        if (Entity.TRUCK.equals(entityName)) {
            session.setAttribute(Entity.MAX_PAYLOAD, request.getParameter(Entity.MAX_PAYLOAD));
            session.setAttribute(Entity.ENCLOSED, request.getParameter(Entity.ENCLOSED));
            session.setAttribute(Entity.TIPPER, request.getParameter(Entity.TIPPER));
            return;
        }
        if (Entity.USER.equals(entityName)) {
            session.setAttribute(Entity.FIRSTNAME, request.getParameter(Entity.FIRSTNAME));
            session.setAttribute(Entity.LASTNAME, request.getParameter(Entity.LASTNAME));
            session.setAttribute(Entity.DOB, request.getParameter(Entity.DOB));
            session.setAttribute(Entity.USERNAME, request.getParameter(Entity.USERNAME));
            session.setAttribute(Entity.EMAIL, request.getParameter(Entity.EMAIL));
            session.setAttribute(Entity.ROLE, request.getParameter(Entity.ROLE));
            session.setAttribute(Entity.BALANCE, request.getParameter(Entity.BALANCE));
        }
    }

    public static void clearEnteredData(HttpSession session) {
            session.removeAttribute(Entity.VALUE_EN);
            session.removeAttribute(Entity.VALUE_RU);
            session.removeAttribute(Entity.VALUE);
            session.removeAttribute(Entity.MODEL_ID);
            session.removeAttribute(Entity.MANUFACTURER_ID);
            session.removeAttribute(Entity.PRODUCTION_YEAR);
            session.removeAttribute(Entity.COLOR_ID);
            session.removeAttribute(Entity.FUEL_TYPE);
            session.removeAttribute(Entity.MILEAGE);
            session.removeAttribute(Entity.RENT);
            session.removeAttribute(Entity.DRIVER_ID);
            session.removeAttribute(Entity.DOORS_NUM);
            session.removeAttribute(Entity.PASS_SEATS_NUM);
            session.removeAttribute(Entity.STAND_PLACES_NUM);
            session.removeAttribute(Entity.MODEL_ID);
            session.removeAttribute(Entity.MANUFACTURER_ID);
            session.removeAttribute(Entity.PRODUCTION_YEAR);
            session.removeAttribute(Entity.COLOR_ID);
            session.removeAttribute(Entity.FUEL_TYPE);
            session.removeAttribute(Entity.MILEAGE);
            session.removeAttribute(Entity.RENT);
            session.removeAttribute(Entity.DRIVER_ID);
            session.removeAttribute(Entity.DOORS_NUM);
            session.removeAttribute(Entity.PASS_SEATS_NUM);
            session.removeAttribute(Entity.WITH_CONDITIONER);
            session.removeAttribute(Entity.MODEL_ID);
            session.removeAttribute(Entity.MANUFACTURER_ID);
            session.removeAttribute(Entity.PRODUCTION_YEAR);
            session.removeAttribute(Entity.COLOR_ID);
            session.removeAttribute(Entity.FUEL_TYPE);
            session.removeAttribute(Entity.MILEAGE);
            session.removeAttribute(Entity.RENT);
            session.removeAttribute(Entity.DRIVER_ID);
            session.removeAttribute(Entity.MAX_PAYLOAD);
            session.removeAttribute(Entity.ENCLOSED);
            session.removeAttribute(Entity.TIPPER);
            session.removeAttribute(Entity.FIRSTNAME);
            session.removeAttribute(Entity.LASTNAME);
            session.removeAttribute(Entity.DOB);
            session.removeAttribute(Entity.USERNAME);
            session.removeAttribute(Entity.EMAIL);
            session.removeAttribute(Entity.ROLE);
            session.removeAttribute(Entity.BALANCE);
    }
}
