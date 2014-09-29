package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.*;
import com.epam.bp.autobase.dao.H2.DaoManager;
import com.epam.bp.autobase.entity.*;
import com.epam.bp.autobase.util.Validator;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class ChangeVehicleAction implements Action {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ChangeVehicleAction.class);
    private static final ActionResult MAIN_DRIVER = new ActionResult("main-driver",true);
    private static final ActionResult ADMIN_CARS = new ActionResult("admin-cars", true);
    private static final ActionResult ADMIN_BUSES = new ActionResult("admin-buses", true);
    private static final ActionResult ADMIN_TRUCKS = new ActionResult("admin-trucks", true);
    private static final String ON = "on";
    private static final String ERROR = "vh_change_error";
    private static final String VEHICLE = "vehicle";
    private static final String USER = "user";
    private static final String ENTITY_CHANGES_FLAG = "listsChanged";
    private static final String SAVE = "save";
    private static final String DELETE = "delete";
    private static final String RENT = "rentPrice";
    private static final String DRIVER_ID = "driverId";
    private static final String OPERABLE = "operable";
    private static final String MODEL_ID = "model_id";
    private static final String MANUFACTURER_ID = "manufacturer_id";
    private static final String COLOR_ID = "color_id";
    private static final String FUELTYPE = "fuelType";
    private static final String MILEAGE = "mileage";
    private static final String PRODUCTIONYEAR = "productionYear";
    private static final String BUS = "Bus";
    private static final String CAR = "Car";
    private static final String TRUCK = "Truck";
    private static final String PASS_SEATS_NUM = "passengerSeatsNumber";
    private static final String DOORS_NUM = "doorsNumber";
    private static final String STAND_PLACES_NUM = "standingPlacesNumber";
    private static final String WITH_COND = "withConditioner";
    private static final String MAX_PAYLOAD = "maxPayload";
    private static final String ENCLOSED = "enclosed";
    private static final String TIPPER = "tipper";
    private ActionResult result;
    private Vehicle vehicle;
    private User user;
    private HttpServletRequest request;
    private HttpSession session;

    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        request = req;
        session = req.getSession();
        user = (User) session.getAttribute(USER);
        if (user.getRole().equals(User.Role.DRIVER)) vehicle = (Vehicle) session.getAttribute(VEHICLE);

        String error = Validator.validateRequestParametersMap(req);
        if (!error.isEmpty()) {
            session.setAttribute(ERROR, error);
            if (user.getRole().equals(User.Role.ADMIN)) {
                String referer = request.getHeader("Referer");
                referer = referer.substring(referer.lastIndexOf("/")+1,referer.length());
                return new ActionResult(referer,true);
            }
            if (user.getRole().equals(User.Role.DRIVER)) return MAIN_DRIVER;
        }

        //changing vehicle if we are client or driver; if we are admin, do it if SAVE parameter not null only
        if (!user.getRole().equals(User.Role.ADMIN) || req.getParameter(SAVE) != null)
            changeVehicle();

        //admin: if parameter delete not null, we are deleting vehicle
        if ((user.getRole().equals(User.Role.ADMIN)) && (request.getParameter(DELETE) != null))
            deleteVehicle();

        return result;
    }

    private void changeVehicle() throws ActionException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(daoManager1 -> {
                VehicleDao vehicleDao = daoManager1.getVehicleDao();
                Integer id = null;
                BigDecimal rentPrice = null;
                Integer driverId = null;
                if (user.getRole().equals(User.Role.ADMIN)) {
                    id = Integer.valueOf(request.getParameter(SAVE));
                    rentPrice = new BigDecimal(request.getParameter(RENT));
                    driverId = Integer.valueOf(request.getParameter(DRIVER_ID));
                    vehicle = vehicleDao.getById(id);
                    vehicle.setRentPrice(rentPrice);
                    vehicle.setDriverId(driverId);
                }
                Boolean operable = ON.equals(request.getParameter(OPERABLE));
                Integer colorId = (Integer.valueOf(request.getParameter(COLOR_ID)));
                Integer modelId = Integer.valueOf(request.getParameter(MODEL_ID));
                Integer manufacturerId = Integer.valueOf(request.getParameter(MANUFACTURER_ID));
                Vehicle.Fuel fuel = Vehicle.Fuel.valueOf(request.getParameter(FUELTYPE));
                BigDecimal mileage = new BigDecimal(request.getParameter(MILEAGE));
                Integer prodYear = Integer.parseInt(request.getParameter(PRODUCTIONYEAR));
                Integer passSeatsNum = null;
                Integer doorsNum = null;
                Integer standPlacesNum = null;
                Boolean withConder = null;
                BigDecimal maxPayload = null;
                Boolean enclosed = null;
                Boolean tipper = null;
                if (BUS.equals(vehicle.getVehicleType())) {
                    passSeatsNum = Integer.parseInt(request.getParameter(PASS_SEATS_NUM));
                    doorsNum = Integer.parseInt(request.getParameter(DOORS_NUM));
                    standPlacesNum = Integer.parseInt(request.getParameter(STAND_PLACES_NUM));
                }
                if (CAR.equals(vehicle.getVehicleType())) {
                    passSeatsNum = Integer.parseInt(request.getParameter(PASS_SEATS_NUM));
                    doorsNum = Integer.parseInt(request.getParameter(DOORS_NUM));
                    withConder = ON.equals(request.getParameter(WITH_COND));
                }
                if (TRUCK.equals(vehicle.getVehicleType())) {
                    maxPayload = new BigDecimal(request.getParameter(MAX_PAYLOAD));
                    enclosed = ON.equals(request.getParameter(ENCLOSED));
                    tipper = ON.equals(request.getParameter(TIPPER));
                }
                vehicle.setOperable(operable);
                vehicle.setModelId(modelId);
                vehicle.setManufacturerId(manufacturerId);
                vehicle.setColorId(colorId);
                vehicle.setFuelType(fuel);
                vehicle.setMileage(mileage);
                vehicle.setProductionYear(prodYear);
                if (BUS.equals(vehicle.getVehicleType())) {
                    ((Bus) vehicle).setPassengerSeatsNumber(passSeatsNum);
                    ((Bus) vehicle).setDoorsNumber(doorsNum);
                    ((Bus) vehicle).setStandingPlacesNumber(standPlacesNum);
                    if (user.getRole().equals(User.Role.ADMIN)) result = ADMIN_BUSES;
                }
                if (CAR.equals(vehicle.getVehicleType())) {
                    ((Car) vehicle).setPassengerSeatsNumber(passSeatsNum);
                    ((Car) vehicle).setDoorsNumber(doorsNum);
                    ((Car) vehicle).setWithConditioner(withConder);
                    if (user.getRole().equals(User.Role.ADMIN)) result = ADMIN_CARS;
                }
                if (TRUCK.equals(vehicle.getVehicleType())) {
                    ((Truck) vehicle).setMaxPayload(maxPayload);
                    ((Truck) vehicle).setEnclosed(enclosed);
                    ((Truck) vehicle).setTipper(tipper);
                    if (user.getRole().equals(User.Role.ADMIN)) result = ADMIN_TRUCKS;
                }
                vehicleDao.update(vehicle);
                if (user.getRole().equals(User.Role.DRIVER)) session.setAttribute(VEHICLE, vehicle);
                session.setAttribute(ERROR, "");
                session.setAttribute(ENTITY_CHANGES_FLAG, VEHICLE);
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at changeVehicle() while performing transaction");
            throw new ActionException("Error at changeVehicle() while performing transaction", e);
        }
        if (user.getRole().equals(User.Role.DRIVER)) result = MAIN_DRIVER;
    }

    private void deleteVehicle() throws ActionException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(daoManager1 -> {
                Integer id = Integer.valueOf(request.getParameter(DELETE));
                VehicleDao vehicleDao = daoManager1.getVehicleDao();
                Vehicle vehicle1 = vehicleDao.getById(id);
                if (vehicle1.getVehicleType().equals("Car")) result = ADMIN_CARS;
                if (vehicle1.getVehicleType().equals("Bus")) result = ADMIN_BUSES;
                if (vehicle1.getVehicleType().equals("Truck")) result = ADMIN_TRUCKS;
                vehicleDao.delete(id);
                session.setAttribute(ENTITY_CHANGES_FLAG, VEHICLE);
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at deleteVehicle() while performing transaction");
            throw new ActionException("Error at deleteVehicle() while performing transaction", e);
        }
    }
}
