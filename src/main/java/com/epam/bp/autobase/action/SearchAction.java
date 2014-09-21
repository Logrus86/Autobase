package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.VehicleDao;
import com.epam.bp.autobase.entity.Vehicle;
import com.epam.bp.autobase.util.Validator;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class SearchAction implements Action { //TODO post -> get
    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(super.getClass());
    private ActionResult search_result_logined = new ActionResult("search-result");
    private ActionResult search_result_guest = new ActionResult("search-result-guest");
    private ActionResult search_err_logined = new ActionResult("main-client");
    private ActionResult search_err_guest = new ActionResult("main");

    private static final ResourceBundle RB = ResourceBundle.getBundle("i18n.text");
    private static final String ERROR_NOTHING_CHECKED = RB.getString("error.nothing-checked");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        HttpSession session = request.getSession();

        //check inputs
        String error = Validator.validateRequestParametersMap(request);
        if (!error.isEmpty()) {
            session.setAttribute("search_error", error);
            session.setAttribute("vehicleList", null);
            if (session.getAttribute("user") == null) return search_err_guest;
            return search_err_logined;
        }

        //looking for vehicle
        try {
            VehicleDao vehicleDao = DaoFactory.getInstance().getDaoManager().getVehicleDao();
            Map<String, String> params = new HashMap<>();
            // filling papameters map for vehicle search according to entered data from search form
            if ("on".equals(request.getParameter("isMODEL"))) params.put("MODEL", request.getParameter("MODEL"));
            if ("on".equals(request.getParameter("isMANUFACTURER")))
                params.put("MANUFACTURER", request.getParameter("MANUFACTURER"));
            if ("on".equals(request.getParameter("isCOLOR"))) params.put("COLOR", request.getParameter("COLOR"));
            if ("on".equals(request.getParameter("isFUELTYPE")))
                params.put("FUELTYPE", request.getParameter("FUELTYPE"));
            if ("on".equals(request.getParameter("isSTANDING_PLACES_NUMBER")))
                params.put("STANDING_PLACES_NUMBER", request.getParameter("STANDING_PLACES_NUMBER"));
            if ("on".equals(request.getParameter("isPASSENGER_SEATS_NUMBER_BUS")))
                params.put("PASSENGER_SEATS_NUMBER", request.getParameter("PASSENGER_SEATS_NUMBER_BUS"));
            if ("on".equals(request.getParameter("isPASSENGER_SEATS_NUMBER_CAR")))
                params.put("PASSENGER_SEATS_NUMBER", request.getParameter("PASSENGER_SEATS_NUMBER_CAR"));
            if ("on".equals(request.getParameter("isDOORS_NUMBER_BUS")))
                params.put("DOORS_NUMBER", request.getParameter("DOORS_NUMBER_BUS"));
            if ("on".equals(request.getParameter("isDOORS_NUMBER_CAR")))
                params.put("DOORS_NUMBER", request.getParameter("DOORS_NUMBER_CAR"));
            if ("on".equals(request.getParameter("isCONDITIONER"))) params.put("CONDITIONER", "true");
            if ("on".equals(request.getParameter("isMAX_PAYLOAD"))) params.put("MAX_PAYLOAD", "true");
            if ("on".equals(request.getParameter("isENCLOSED"))) params.put("ENCLOSED", "true");
            if ("on".equals(request.getParameter("isTIPPER"))) params.put("TIPPER", "true");

            //check for input at least one field to search, if not, return to the same page with error message
            //& !("on".equals(request.getParameter("isMILEAGE"))) & !("on".equals(request.getParameter("isNOTOLDER"))) & !("on".equals(request.getParameter("isRENTPRICE")))
            if (params.isEmpty() ) {
                if ("on".equals((request.getParameter("isMILEAGE")))
                        | "on".equals(request.getParameter("isNOTOLDER"))
                        | "on".equals(request.getParameter("isRENTPRICE"))) {

                }
                LOGGER.info("Nothing was checked.");
                session.setAttribute("search_error", ERROR_NOTHING_CHECKED);
                session.setAttribute("vehicleList", null);
                if (session.getAttribute("user") == null) return search_err_guest;
                return search_err_logined;
            }
            List<Vehicle> vehicles = vehicleDao.findByParams(params);
            //delete not operable
            for (Vehicle vehicle : vehicles) {
                if (!vehicle.isOperable()) vehicles.remove(vehicle);
            }

//            if (params.isEmpty()) vehicles = h2VehicleDao.getAll();
//            else vehicles = h2VehicleDao.findByParams(params);
//            for (Vehicle vehicle : vehicles) { //TODO we cant delete entity form collection while iterating it
//                if ((!vehicle.isOperable()) | ("on".equals(request.getParameter("isMILEAGE")) & vehicle.getMileage().compareTo(BigDecimal.valueOf(Double.parseDouble(request.getParameter("MILEAGE")))) == 1)
//                        | ("on".equals(request.getParameter("isNOTOLDER")) & vehicle.getProductionYear() > Integer.parseInt(request.getParameter("NOTOLDER")))
//                        | ("on".equals(request.getParameter("isRENTPRICE")) & vehicle.getRentPrice().compareTo(BigDecimal.valueOf(Double.parseDouble(request.getParameter("RENTPRICE")))) == 1)) {
//                    vehicles.remove(vehicle);
//                }
//            }
            //vehicle was not found
            if (vehicles.isEmpty()) {
                LOGGER.info("Vehicles wasn't found.");
                session.setAttribute("search_result", "Nothing was found.");
                session.setAttribute("search_error", "");
                session.setAttribute("vehicleList", null);
                if (session.getAttribute("user") == null) return search_result_guest;
                return search_result_logined;
            }
            //vehicle was found, all is ok
            LOGGER.info("Vehicle was found.");
            session.setAttribute("vehicleList", vehicles);
            session.setAttribute("search_error", "");
            session.setAttribute("search_result", "Found:");
        } catch (Exception e) {
            LOGGER.error("Error at SearchAction while searching for vehicle");
            throw new ActionException("Error at SearchAction while searching for vehicle", e);
        }
        if (session.getAttribute("user") == null) return search_result_guest;
        return search_result_logined;
    }
}
