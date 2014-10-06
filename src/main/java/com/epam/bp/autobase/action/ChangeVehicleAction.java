package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.H2.DaoManager;
import com.epam.bp.autobase.dao.VehicleDao;
import com.epam.bp.autobase.entity.*;
import com.epam.bp.autobase.util.AttributeSetter;
import com.epam.bp.autobase.util.Validator;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class ChangeVehicleAction implements Action {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ChangeVehicleAction.class);
    private static final ActionResult MAIN_DRIVER = new ActionResult(ActionFactory.PAGE_MAIN_DRIVER, true);
    private static final ActionResult ADMIN_CARS = new ActionResult(ActionFactory.PAGE_ADMIN_CARS, true);
    private static final ActionResult ADMIN_BUSES = new ActionResult(ActionFactory.PAGE_ADMIN_BUSES, true);
    private static final ActionResult ADMIN_TRUCKS = new ActionResult(ActionFactory.PAGE_ADMIN_TRUCKS, true);
    private static final String ON = "on";
    private static final String ERROR = "vh_change_error";
    private static final String SAVE = "save";
    private static final String DELETE = "delete";
    private static final String VEHICLE_ID = "vehicleId";
    private static final String PRODUCTION_YEAR = "productionYear";
    private static final String PASS_SEATS_NUM = "passengerSeatsNumber";
    private static final String DOORS_NUM = "doorsNumber";
    private static final String STAND_PLACES_NUM = "standingPlacesNumber";

    private ActionResult result;
    private Vehicle vehicle;
    private User user;
    private HttpServletRequest request;
    private HttpSession session;

    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        request = req;
        session = req.getSession();
        user = (User) session.getAttribute(Entity.USER);
        if (user.getRole().equals(User.Role.DRIVER)) vehicle = (Vehicle) session.getAttribute(Entity.VEHICLE);

        String error = Validator.validateRequestParametersMap(req);
        if (!error.isEmpty()) {
            session.setAttribute(ERROR, error);
            if (user.getRole().equals(User.Role.ADMIN)) {
                String referer = request.getHeader("Referer");
                referer = referer.substring(referer.lastIndexOf("/") + 1, referer.length());
                return new ActionResult(referer, true);
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
                    rentPrice = new BigDecimal(request.getParameter(Entity.RENT));
                    driverId = Integer.valueOf(request.getParameter(Entity.DRIVER_ID));
                    vehicle = vehicleDao.getById(id);
                    vehicle.setRentPrice(rentPrice);
                    vehicle.setDriverId(driverId);
                }
                String idString = "";
                if (user.getRole().equals(User.Role.DRIVER)) {
                    idString = request.getParameter(VEHICLE_ID);
                    vehicle = vehicleDao.getById(Integer.valueOf(idString));
                }
                Boolean operable = ON.equals(request.getParameter(Entity.OPERABLE + idString));
                Integer colorId = (Integer.valueOf(request.getParameter(Entity.COLOR_ID + idString)));
                Integer modelId = Integer.valueOf(request.getParameter(Entity.MODEL_ID + idString));
                Integer manufacturerId = Integer.valueOf(request.getParameter(Entity.MANUFACTURER_ID + idString));
                Vehicle.Fuel fuel = Vehicle.Fuel.valueOf(request.getParameter(Entity.FUEL_TYPE + idString));
                BigDecimal mileage = new BigDecimal(request.getParameter(Entity.MILEAGE + idString));
                Integer prodYear = Integer.parseInt(request.getParameter(PRODUCTION_YEAR + idString));
                Integer passSeatsNum = null;
                Integer doorsNum = null;
                Integer standPlacesNum = null;
                Boolean withConder = null;
                BigDecimal maxPayload = null;
                Boolean enclosed = null;
                Boolean tipper = null;
                vehicle.setOperable(operable);
                vehicle.setModelId(modelId);
                vehicle.setManufacturerId(manufacturerId);
                vehicle.setColorId(colorId);
                vehicle.setFuelType(fuel);
                vehicle.setMileage(mileage);
                vehicle.setProductionYear(prodYear);
                if (vehicle.getType() == Vehicle.Type.BUS) {
                    passSeatsNum = Integer.parseInt(request.getParameter(PASS_SEATS_NUM + idString));
                    doorsNum = Integer.parseInt(request.getParameter(DOORS_NUM + idString));
                    standPlacesNum = Integer.parseInt(request.getParameter(STAND_PLACES_NUM + idString));
                    ((Bus) vehicle).setPassengerSeatsNumber(passSeatsNum);
                    ((Bus) vehicle).setDoorsNumber(doorsNum);
                    ((Bus) vehicle).setStandingPlacesNumber(standPlacesNum);
                    AttributeSetter.setEntityToSession(Entity.BUS, session);
                    if (user.getRole().equals(User.Role.ADMIN)) result = ADMIN_BUSES;
                }
                if (vehicle.getType() == Vehicle.Type.CAR) {
                    passSeatsNum = Integer.parseInt(request.getParameter(PASS_SEATS_NUM + idString));
                    doorsNum = Integer.parseInt(request.getParameter(DOORS_NUM + idString));
                    withConder = ON.equals(request.getParameter(Entity.WITH_CONDITIONER + idString));
                    ((Car) vehicle).setPassengerSeatsNumber(passSeatsNum);
                    ((Car) vehicle).setDoorsNumber(doorsNum);
                    ((Car) vehicle).setWithConditioner(withConder);
                    AttributeSetter.setEntityToSession(Entity.CAR, session);
                    if (user.getRole().equals(User.Role.ADMIN)) result = ADMIN_CARS;
                }
                if (vehicle.getType() == Vehicle.Type.TRUCK) {
                    maxPayload = new BigDecimal(request.getParameter(Entity.MAX_PAYLOAD + idString));
                    enclosed = ON.equals(request.getParameter(Entity.ENCLOSED + idString));
                    tipper = ON.equals(request.getParameter(Entity.TIPPER + idString));
                    ((Truck) vehicle).setMaxPayload(maxPayload);
                    ((Truck) vehicle).setEnclosed(enclosed);
                    ((Truck) vehicle).setTipper(tipper);
                    AttributeSetter.setEntityToSession(Entity.TRUCK, session);
                    if (user.getRole().equals(User.Role.ADMIN)) result = ADMIN_TRUCKS;
                }
                vehicleDao.update(vehicle);
                session.setAttribute(ERROR, "");
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
                Vehicle vehicle = vehicleDao.getById(id);
                vehicleDao.delete(vehicle);
                if (vehicle.getType() == Vehicle.Type.CAR) {
                    AttributeSetter.setEntityToSession(Entity.CAR, session);
                    result = ADMIN_CARS;
                }
                if (vehicle.getType() == Vehicle.Type.BUS) {
                    AttributeSetter.setEntityToSession(Entity.BUS, session);
                    result = ADMIN_BUSES;
                }
                if (vehicle.getType() == Vehicle.Type.TRUCK) {
                    AttributeSetter.setEntityToSession(Entity.TRUCK, session);
                    result = ADMIN_TRUCKS;
                }
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at deleteVehicle() while performing transaction");
            throw new ActionException("Error at deleteVehicle() while performing transaction", e);
        }
    }
}
