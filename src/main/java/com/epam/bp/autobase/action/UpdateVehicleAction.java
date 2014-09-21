package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.H2.H2VehicleDao;
import com.epam.bp.autobase.dao.VehicleDao;
import com.epam.bp.autobase.entity.Bus;
import com.epam.bp.autobase.entity.Car;
import com.epam.bp.autobase.entity.Truck;
import com.epam.bp.autobase.entity.Vehicle;
import com.epam.bp.autobase.util.Validator;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class UpdateVehicleAction implements Action {
    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(super.getClass());
    private ActionResult main_driver = new ActionResult("main-driver");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        HttpSession session = request.getSession();
        Vehicle vehicle = (Vehicle) session.getAttribute("vehicle");

        String error = Validator.validateRequestParametersMap(request);
        if (!error.isEmpty()) {
            session.setAttribute("upd_vh_error", error);
            return main_driver;
        }

        //data was entered correctly, proceed
        if ("on".equals(request.getParameter("operable"))) vehicle.setOperable(true); else vehicle.setOperable(false);
        vehicle.setModel(request.getParameter("model"));
        vehicle.setManufacturer(request.getParameter("manufacturer"));
        vehicle.setColor(request.getParameter("color"));
        vehicle.setFuelType(Vehicle.Fuel.valueOf(request.getParameter("fuelType")));
        vehicle.setMileage(BigDecimal.valueOf(Double.parseDouble(request.getParameter("mileage"))));
        vehicle.setProductionYear(Integer.parseInt(request.getParameter("productionYear")));
        if ("Bus".equals(vehicle.getVehicleType())) {
            ((Bus) vehicle).setPassengerSeatsNumber(Integer.parseInt(request.getParameter("passengerSeatsNumber")));
            ((Bus) vehicle).setDoorsNumber(Integer.parseInt(request.getParameter("doorsNumber")));
            ((Bus) vehicle).setStandingPlacesNumber(Integer.parseInt(request.getParameter("standingPlacesNumber")));
        }
        if ("Car".equals(vehicle.getVehicleType())) {
            ((Car) vehicle).setPassengerSeatsNumber(Integer.parseInt(request.getParameter("passengerSeatsNumber")));
            ((Car) vehicle).setDoorsNumber(Integer.parseInt(request.getParameter("doorsNumber")));
            if ("on".equals(request.getParameter("withConditioner"))) ((Car) vehicle).setWithConditioner(true);
            else ((Car) vehicle).setWithConditioner(false);
        }
        if ("Truck".equals(vehicle.getVehicleType())) {
            ((Truck) vehicle).setMaxPayload(BigDecimal.valueOf(Long.parseLong(request.getParameter("maxPayload"))));
            if ("on".equals(request.getParameter("enclosed"))) ((Truck) vehicle).setEnclosed(true);
            else ((Truck) vehicle).setEnclosed(false);
            if ("on".equals(request.getParameter("tipper"))) ((Truck) vehicle).setTipper(true);
            else ((Truck) vehicle).setTipper(false);
        }

        H2VehicleDao h2VehicleDao;
        try {
            VehicleDao vehicleDao = DaoFactory.getInstance().getDaoManager().getVehicleDao();
            vehicleDao.update(vehicle);
            session.setAttribute("vehicle", vehicle);
            session.setAttribute("upd_vh_error", "");
        } catch (Exception e) {
            throw new ActionException("Error at UpdateVehicleAction while updating vehicle ID=" + vehicle.getId(), e);
        }
        return main_driver;
    }
}
