package com.epam.bp.autobase.jsp.servlet;

import com.epam.bp.autobase.ejb.SessionState;
import com.epam.bp.autobase.model.dto.*;
import com.epam.bp.autobase.model.entity.Order;
import com.epam.bp.autobase.model.entity.User;
import com.epam.bp.autobase.model.entity.Vehicle;
import com.epam.bp.autobase.service.*;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet({
        "do/register",
        "do/create_user",
        "do/create_color",
        "do/create_model",
        "do/create_manufacturer",
        "do/change_user",
        "do/change_color",
        "do/change_model",
        "do/change_manufacturer",
        "do/change_order",
        "do/change_vehicle"
})
public class ChangeEntityServlet extends HttpServlet {
    private static final String ID = "id";
    private static final String PARAM_SAVE = "save";
    private static final String PARAM_DELETE = "delete";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String EMAIL = "email";
    private static final String DOB = "dob";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String PASSWORD_REPEAT = "password-repeat";
    private static final String BALANCE = "balance";
    private static final String ROLE = "role";
    private static final String VALUE_EN = "value_en";
    private static final String VALUE_RU = "value_ru";
    private static final String VALUE = "value";
    private static final String ORDER_ID = "orderId";
    private static final String ORDER_STATUS = "status";
    private static final String VEHICLE_ID = "vehicleId";
    private static final String VEHICLE_TYPE = "vehicleType";
    private static final String ON = "on";
    private static final String PASS_SEATS_NUM = "passengerSeatsNumber";
    private static final String DOORS_NUM = "doorsNumber";
    private static final String STAND_PLACES_NUM = "standingPlacesNumber";
    private static final String PRODUCTION_YEAR = "productionYear";
    private static final String RENT = "rentPrice";
    private static final String DRIVER_ID = "driverId";
    private static final String MODEL_ID = "model_id";
    private static final String MANUFACTURER_ID = "manufacturer_id";
    private static final String COLOR_ID = "color_id";
    private static final String FUEL_TYPE = "fuelType";
    private static final String MILEAGE = "mileage";
    private static final String WITH_CONDITIONER = "withConditioner";
    private static final String MAX_PAYLOAD = "maxPayload";
    private static final String ENCLOSED = "enclosed";
    private static final String TIPPER = "tipper";
    private static final String OPERABLE = "operable";

    private static final String PAGE_MAIN = "/WEB-INF/jsp/main.jsp";
    private static final String PAGE_ERROR = "/WEB-INF/jsp/error.jsp";
    private static final String PAGE_REGISTERED = "/WEB-INF/jsp/registered.jsp";
    private static final String PAGE_COLORS = "/WEB-INF/jsp/admin_colors.jsp";
    private static final String PAGE_MODELS = "/WEB-INF/jsp/admin_models.jsp";
    private static final String PAGE_MANUFACTURERS = "/WEB-INF/jsp/admin_manufacturers.jsp";
    private static final String PAGE_ORDERS = "/WEB-INF/jsp/mainADMIN.jsp";
    private static final String PAGE_BUSES = "/WEB-INF/jsp/admin_buses.jsp";
    private static final String PAGE_CARS = "/WEB-INF/jsp/admin_cars.jsp";
    private static final String PAGE_TRUCKS = "/WEB-INF/jsp/admin_trucks.jsp";
    private static final String PAGE_CABINET = "/WEB-INF/jsp/cabinet.jsp";
    private static final String PAGE_DRIVER = "/WEB-INF/jsp/mainDRIVER.jsp";
    private static final String PAGE_USERS = "/WEB-INF/jsp/admin_users.jsp";

    @Inject
    Logger logger;
    @Inject
    SessionState ss;
    @Inject
    UserService us;
    @Inject
    ColorService cs;
    @Inject
    ModelService ms;
    @Inject
    ManufacturerService mfs;
    @Inject
    OrderService os;
    @Inject
    VehicleService vs;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher resultView = req.getRequestDispatcher(PAGE_MAIN);
        resultView.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName = req.getServletPath().substring(req.getServletPath().lastIndexOf('/') + 1);
        logger.info("Invoke method by name: " + methodName);
        try {
            this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class).invoke(this, req, resp);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.error(e.getMessage(), e.getCause());
            req.setAttribute("statusCode", 500);
            req.setAttribute("message", "Method invocation failed: " + e.getMessage());
            RequestDispatcher resultView = req.getRequestDispatcher(PAGE_ERROR);
            resultView.forward(req, resp);
        }
    }

    private void create_user(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserDto userDto = getUserDtoFromRequest(req);
            us.create(userDto);
            RequestDispatcher resultView;
            if (ss.getSessionUser().getRole().equals(User.Role.ADMIN)) {
                logger.info("Newly created user: " + userDto.getUsername());
                resultView = req.getRequestDispatcher(PAGE_USERS);
            } else {
                ss.setSessionUser(userDto.buildLazyEntity());
                logger.info("Newly registered user: " + ss.getSessionUser().toString());
                resultView = req.getRequestDispatcher(PAGE_REGISTERED);
            }
            resultView.forward(req, resp);
        } catch (ServiceException se) {
            logger.error(se.getMessage() + ", " + se.getCause());
            se.printStackTrace();
            forwardDependsRole(req, resp);
        }
    }

    private void create_color(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ColorDto colorDto = new ColorDto()
                    .setValue_en(req.getParameter(VALUE_EN))
                    .setValue_ru(req.getParameter(VALUE_RU));
            cs.create(colorDto);
            logger.info("Color created successfully");
        } catch (ServiceException se) {
            logger.error(se.getMessage() + (se.getCause() != null ? ", " + se.getCause() : ""));
        }
        RequestDispatcher resultView = req.getRequestDispatcher(PAGE_COLORS);
        resultView.forward(req, resp);
    }

    private void create_model(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ModelDto modelDto = new ModelDto()
                    .setValue(req.getParameter(VALUE));
            ms.create(modelDto);
            logger.info("Model created successfully");
        } catch (ServiceException se) {
            logger.error(se.getMessage() + (se.getCause() != null ? ", " + se.getCause() : ""));
        }
        RequestDispatcher resultView = req.getRequestDispatcher(PAGE_MODELS);
        resultView.forward(req, resp);
    }

    private void create_manufacturer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ManufacturerDto manufacturerDto = new ManufacturerDto()
                    .setValue(req.getParameter(VALUE));
            mfs.create(manufacturerDto);
            logger.info("Manufacturer created successfully");
        } catch (ServiceException se) {
            logger.error(se.getMessage() + (se.getCause() != null ? ", " + se.getCause() : ""));
        }
        RequestDispatcher resultView = req.getRequestDispatcher(PAGE_MANUFACTURERS);
        resultView.forward(req, resp);
    }

    private void change_user(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //changing user if we are client or driver; if we are admin, do it if PARAM_SAVE parameter not null only
        if (!User.Role.ADMIN.equals(ss.getSessionUser().getRole()) || req.getParameter(PARAM_SAVE) != null) {
            try {
                UserDto userDto = getUserDtoFromRequest(req);
                if (ss.getSessionUser().getRole().equals(User.Role.DRIVER)) {
                    userDto.setRole(User.Role.DRIVER);
                    List<VehicleDto> vehicles = ss.getSessionUser().getVehicles().stream().map(VehicleDto::new).collect(Collectors.toCollection(LinkedList::new));
                    userDto.setVehicleDtoList(vehicles);
                    ss.setSessionUser(userDto.buildFullEntity());
                }
                if (ss.getSessionUser().getRole().equals(User.Role.CLIENT)) {
                    userDto.setBalance(ss.getSessionUser().getBalance());
                    userDto.setRole(User.Role.CLIENT);
                    List<OrderDto> orders = ss.getSessionUser().getOrders().stream().map(OrderDto::new).collect(Collectors.toCollection(LinkedList::new));
                    userDto.setOrderDtoList(orders);
                    ss.setSessionUser(userDto.buildFullEntity());
                }
                us.update(userDto);
                logger.info("User '" + req.getParameter(USERNAME) + "' was successfully updated");
            } catch (ServiceException se) {
                logger.error(se.getMessage());
            }
        } else {
            String stringId = req.getParameter(PARAM_DELETE);
            if (stringId != null) {
                try {
                    us.delete(Integer.valueOf(req.getParameter(PARAM_DELETE)));
                } catch (ServiceException se) {
                    logger.error(se.getMessage());
                }
                logger.info("User '" + req.getParameter(USERNAME) + "' was successfully deleted");
            }
        }
        forwardDependsRole(req, resp);
    }

    private void change_color(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String stringId;
            if (req.getParameter(PARAM_SAVE) == null) {
                stringId = req.getParameter(PARAM_DELETE);
                cs.delete(Integer.valueOf(stringId));
                logger.info("Color with id = " + stringId + " was successfully deleted");
            } else {
                stringId = req.getParameter(PARAM_SAVE);
                ColorDto colorDto = new ColorDto()
                        .setId(Integer.valueOf(stringId))
                        .setValue_en(req.getParameter(VALUE_EN))
                        .setValue_ru(req.getParameter(VALUE_RU));
                cs.update(colorDto);
                logger.info("Color '" + req.getParameter(VALUE_EN) + "' had successfully updated");
            }
        } catch (ServiceException se) {
            logger.error(se.getMessage() + ", " + se.getCause());
        }
        RequestDispatcher resultView = req.getRequestDispatcher(PAGE_COLORS);
        resultView.forward(req, resp);
    }

    private void change_model(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String stringId;
            if (req.getParameter(PARAM_SAVE) == null) {
                stringId = req.getParameter(PARAM_DELETE);
                ms.delete(Integer.valueOf(stringId));
                logger.info("Model with id = " + stringId + " was successfully deleted");
            } else {
                stringId = req.getParameter(PARAM_SAVE);
                ModelDto modelDto = new ModelDto()
                        .setId(Integer.valueOf(stringId))
                        .setValue(req.getParameter(VALUE));
                ms.update(modelDto);
                logger.info("Model '" + req.getParameter(VALUE) + "' had successfully updated");
            }
        } catch (ServiceException se) {
            logger.error(se.getMessage() + ", " + se.getCause());
        }
        RequestDispatcher resultView = req.getRequestDispatcher(PAGE_MODELS);
        resultView.forward(req, resp);
    }

    private void change_manufacturer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String stringId;
            if (req.getParameter(PARAM_SAVE) == null) {
                stringId = req.getParameter(PARAM_DELETE);
                mfs.delete(Integer.valueOf(stringId));
                logger.info("Manufacturer with id = " + stringId + " was successfully deleted");
            } else {
                stringId = req.getParameter(PARAM_SAVE);
                ManufacturerDto manufacturerDto = new ManufacturerDto()
                        .setId(Integer.valueOf(stringId))
                        .setValue(req.getParameter(VALUE));
                mfs.update(manufacturerDto);
                logger.info("Manufacturer '" + req.getParameter(VALUE) + "' had successfully updated");
            }
        } catch (ServiceException se) {
            logger.error(se.getMessage() + ", " + se.getCause());
        }
        RequestDispatcher resultView = req.getRequestDispatcher(PAGE_MANUFACTURERS);
        resultView.forward(req, resp);
    }

    private void change_order(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderDto dto = new OrderDto()
                .setId(Integer.valueOf(req.getParameter(ORDER_ID)))
                .setStatus(Order.Status.valueOf(req.getParameter(ORDER_STATUS)));
        try {
            os.update(dto);
            logger.info("Order with id=" + dto.getId() + " had successfully updated");
        } catch (ServiceException se) {
            logger.error(se.getMessage() + ", " + se.getCause());
        }
        RequestDispatcher resultView = req.getRequestDispatcher(PAGE_ORDERS);
        resultView.forward(req, resp);
    }

    private void change_vehicle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        VehicleDto dto = getVehicleDtoFromRequest(req);
        try {
            vs.update(dto);
            logger.info("Vehicle with id=" + dto.getId() + " had successfully updated");
        } catch (ServiceException se) {
            logger.error(se.getMessage() + ", " + se.getCause());
        }
        RequestDispatcher resultView = req.getRequestDispatcher("");
        if (ss.getSessionUser().getRole().equals(User.Role.DRIVER)) resultView = req.getRequestDispatcher(PAGE_DRIVER);
        else
            switch (dto.getType()) {
                case BUS:
                    resultView = req.getRequestDispatcher(PAGE_BUSES);
                    break;
                case CAR:
                    resultView = req.getRequestDispatcher(PAGE_CARS);
                    break;
                case TRUCK:
                    resultView = req.getRequestDispatcher(PAGE_TRUCKS);
                    break;
            }
        resultView.forward(req, resp);
    }

    private void forwardDependsRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher resultView = req.getRequestDispatcher(PAGE_MAIN);
        if ((ss.getSessionUser() != null) && (ss.getSessionUser().getRole() != null)) {
            if (User.Role.CLIENT.equals(ss.getSessionUser().getRole())) {
                resultView = req.getRequestDispatcher(PAGE_CABINET);
            } else {
                if (User.Role.DRIVER.equals(ss.getSessionUser().getRole())) {
                    resultView = req.getRequestDispatcher(PAGE_DRIVER);
                } else {
                    if (User.Role.ADMIN.equals(ss.getSessionUser().getRole())) {
                        resultView = req.getRequestDispatcher(PAGE_USERS);
                    }
                }
            }
        }
        resultView.forward(req, resp);
    }

    private UserDto getUserDtoFromRequest(HttpServletRequest req) {
        UserDto dto = new UserDto()
                .setUsername(req.getParameter(USERNAME))
                .setPassword(req.getParameter(PASSWORD))
                .setPassword_repeat(req.getParameter(PASSWORD_REPEAT))
                .setEmail(req.getParameter(EMAIL))
                .setFirstname(req.getParameter(FIRSTNAME))
                .setLastname(req.getParameter(LASTNAME))
                .setDob(req.getParameter(DOB));
        if (req.getParameterMap().containsKey(ROLE))
            dto.setRole(req.getParameter(ROLE));
        else dto.setRole(User.Role.CLIENT);
        if (dto.getRole().equals(User.Role.CLIENT)) {
            if (req.getParameterMap().containsKey(BALANCE))
                dto.setBalance(req.getParameter(BALANCE));
            else dto.setBalance(BigDecimal.ZERO);
        }
        //get id from "id" or "save" or "delete"
        if (req.getParameterMap().containsKey(ID))
            dto.setId(Integer.valueOf(req.getParameter(ID)));
        else {
            if (req.getParameterMap().containsKey(PARAM_SAVE)) dto.setId(Integer.valueOf(req.getParameter(PARAM_SAVE)));
            else if (req.getParameterMap().containsKey(PARAM_DELETE))
                dto.setId(Integer.valueOf(req.getParameter(PARAM_DELETE)));
        }
        return dto;
    }

    private VehicleDto getVehicleDtoFromRequest(HttpServletRequest req) {
        VehicleDto dto = new VehicleDto();

        if (req.getParameterMap().containsKey(VEHICLE_ID)) dto.setId(Integer.valueOf(req.getParameter(VEHICLE_ID)));
        else {
            if (req.getParameterMap().containsKey(PARAM_SAVE))
                dto.setId(Integer.valueOf(req.getParameter(PARAM_SAVE)));
            else if (req.getParameterMap().containsKey(PARAM_DELETE))
                dto.setId(Integer.valueOf(req.getParameter(PARAM_DELETE)));
        }
        String sId = "";
        if (ss.getSessionUser().getRole().equals(User.Role.DRIVER)) sId = String.valueOf(dto.getId());
        dto.setWithConditioner(ON.equals(req.getParameter(WITH_CONDITIONER + sId)));
        dto.setTipper(ON.equals(req.getParameter(TIPPER + sId)));
        dto.setOperable(ON.equals(req.getParameter(OPERABLE + sId)));
        if (req.getParameterMap().containsKey(VEHICLE_TYPE + sId))
            dto.setType(Vehicle.Type.valueOf(req.getParameter(VEHICLE_TYPE + sId)));
        if (req.getParameterMap().containsKey(PASS_SEATS_NUM + sId))
            dto.setPassengerSeatsNumber(Integer.parseInt(req.getParameter(PASS_SEATS_NUM + sId)));
        if (req.getParameterMap().containsKey(STAND_PLACES_NUM + sId))
            dto.setStandingPlacesNumber(Integer.parseInt(req.getParameter(STAND_PLACES_NUM + sId)));
        if (req.getParameterMap().containsKey(DOORS_NUM + sId))
            dto.setDoorsNumber(Integer.parseInt(req.getParameter(DOORS_NUM + sId)));
        if (req.getParameterMap().containsKey(ENCLOSED + sId))
            dto.setEnclosed(Boolean.valueOf(req.getParameter(ENCLOSED + sId)));
        if (req.getParameterMap().containsKey(MAX_PAYLOAD + sId))
            dto.setMaxPayload(new BigDecimal(req.getParameter(MAX_PAYLOAD + sId)));
        if (req.getParameterMap().containsKey(RENT + sId))
            dto.setRentPrice(new BigDecimal(req.getParameter(RENT + sId)));
        if (req.getParameterMap().containsKey(PRODUCTION_YEAR + sId))
            dto.setProductionYear(Integer.valueOf(req.getParameter(PRODUCTION_YEAR + sId)));
        if (req.getParameterMap().containsKey(MILEAGE + sId))
            dto.setMileage(new BigDecimal(req.getParameter(MILEAGE + sId)));
        if (req.getParameterMap().containsKey(FUEL_TYPE + sId))
            dto.setFuelType(Vehicle.Fuel.valueOf(req.getParameter(FUEL_TYPE + sId)));
        if (req.getParameterMap().containsKey(COLOR_ID + sId))
            dto.setColorDto(new ColorDto().setId(Integer.valueOf(req.getParameter(COLOR_ID + sId))));
        if (req.getParameterMap().containsKey(MODEL_ID + sId))
            dto.setModelDto(new ModelDto().setId(Integer.valueOf(req.getParameter(MODEL_ID + sId))));
        if (req.getParameterMap().containsKey(MANUFACTURER_ID + sId))
            dto.setManufacturerDto(new ManufacturerDto().setId(Integer.valueOf(req.getParameter(MANUFACTURER_ID + sId))));
        if (req.getParameterMap().containsKey(DRIVER_ID + sId))
            dto.setDriverDto(new UserDto().setRole(User.Role.DRIVER).setId(Integer.valueOf(req.getParameter(DRIVER_ID + sId))));
        return dto;
    }
}
