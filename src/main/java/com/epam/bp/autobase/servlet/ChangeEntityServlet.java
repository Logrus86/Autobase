package com.epam.bp.autobase.servlet;

import com.epam.bp.autobase.cdi.SessionState;
import com.epam.bp.autobase.model.dto.*;
import com.epam.bp.autobase.model.entity.User;
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
        "do/change_manufacturer"
})
public class ChangeEntityServlet extends HttpServlet {
    public static final String PARAM_SAVE = "save";
    public static final String PARAM_DELETE = "delete";
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher resultView = req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        resultView.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName = req.getServletPath().substring(req.getServletPath().lastIndexOf('/') + 1);
        logger.info("Launch method by name: " + methodName);
        try {
            this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class).invoke(this, req, resp);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.error(e.getMessage(), e.getCause());
            req.setAttribute("statusCode", 500);
            req.setAttribute("message", "Method invocation failed: " + e.getMessage());
            RequestDispatcher resultView = req.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            resultView.forward(req, resp);
        }
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
                        .setValue_en(req.getParameter(AbstractService.VALUE_EN))
                        .setValue_ru(req.getParameter(AbstractService.VALUE_RU));
                cs.update(colorDto);
                logger.info("Color '" + req.getParameter(AbstractService.VALUE_EN) + "' had successfully updated");
            }
        } catch (ServiceException se) {
            logger.error(se.getMessage() + ", " + se.getCause());
        }
        RequestDispatcher resultView = req.getRequestDispatcher("/WEB-INF/jsp/admin_colors.jsp");
        resultView.forward(req, resp);
    }

    private void create_color(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ColorDto colorDto = new ColorDto()
                    .setValue_en(req.getParameter(AbstractService.VALUE_EN))
                    .setValue_ru(req.getParameter(AbstractService.VALUE_RU));
            cs.create(colorDto);
            logger.info("Color created successfully");
        } catch (ServiceException se) {
            logger.error(se.getMessage() + (se.getCause() != null ? ", " + se.getCause() : ""));
        }
        RequestDispatcher resultView = req.getRequestDispatcher("/WEB-INF/jsp/admin_colors.jsp");
        resultView.forward(req, resp);
    }

    private void create_model(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ModelDto modelDto = new ModelDto()
                    .setValue(req.getParameter(AbstractService.VALUE));
            ms.create(modelDto);
            logger.info("Model created successfully");
        } catch (ServiceException se) {
            logger.error(se.getMessage() + (se.getCause() != null ? ", " + se.getCause() : ""));
        }
        RequestDispatcher resultView = req.getRequestDispatcher("/WEB-INF/jsp/admin_models.jsp");
        resultView.forward(req, resp);
    }

    private void create_manufacturer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ManufacturerDto manufacturerDto = new ManufacturerDto()
                    .setValue(req.getParameter(AbstractService.VALUE));
            mfs.create(manufacturerDto);
            logger.info("Manufacturer created successfully");
        } catch (ServiceException se) {
            logger.error(se.getMessage() + (se.getCause() != null ? ", " + se.getCause() : ""));
        }
        RequestDispatcher resultView = req.getRequestDispatcher("/WEB-INF/jsp/admin_manufacturers.jsp");
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
                logger.info("User '" + req.getParameter(AbstractService.USERNAME) + "' was successfully updated");
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
                logger.info("User '" + req.getParameter(AbstractService.USERNAME) + "' was successfully deleted");
            }
        }
        forwardDependsRole(req, resp);
    }

    private void create_user(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserDto userDto = getUserDtoFromRequest(req);
            us.create(userDto);
            if (us.getErrorMap() == null) ss.setSessionUser(userDto.buildLazyEntity());
            logger.info("Newly registered user: " + ss.getSessionUser().toString());
            RequestDispatcher resultView = req.getRequestDispatcher("/WEB-INF/jsp/registered.jsp");
            resultView.forward(req, resp);
        } catch (ServiceException se) {
            logger.error(se.getMessage());
            forwardDependsRole(req, resp);
        }
    }

    private void forwardDependsRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher resultView = req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        if ((ss.getSessionUser() != null) && (ss.getSessionUser().getRole() != null)) {
            if (User.Role.CLIENT.equals(ss.getSessionUser().getRole())) {
                resultView = req.getRequestDispatcher("/WEB-INF/jsp/cabinet.jsp");
            } else {
                if (User.Role.DRIVER.equals(ss.getSessionUser().getRole())) {
                    resultView = req.getRequestDispatcher("/WEB-INF/jsp/mainDRIVER.jsp");
                } else {
                    if (User.Role.ADMIN.equals(ss.getSessionUser().getRole())) {
                        resultView = req.getRequestDispatcher("/WEB-INF/jsp/admin_users.jsp");
                    }
                }
            }
        }
        resultView.forward(req, resp);
    }

    private UserDto getUserDtoFromRequest(HttpServletRequest req) {
        UserDto dto = new UserDto()
                .setUsername(req.getParameter(AbstractService.USERNAME))
                .setPassword(req.getParameter(AbstractService.PASSWORD))
                .setEmail(req.getParameter(AbstractService.EMAIL))
                .setFirstname(req.getParameter(AbstractService.FIRSTNAME))
                .setLastname(req.getParameter(AbstractService.LASTNAME))
                .setDob(req.getParameter(AbstractService.DOB));
        if (req.getParameterMap().containsKey(AbstractService.ROLE))
            dto.setRole(req.getParameter(AbstractService.ROLE));
        else dto.setRole(User.Role.CLIENT);
        if (req.getParameterMap().containsKey(AbstractService.BALANCE))
            dto.setBalance(req.getParameter(AbstractService.BALANCE));
        else dto.setBalance(BigDecimal.ZERO);
        //get id from "id" or "save" or "delete"
        if (req.getParameterMap().containsKey(AbstractService.ID))
            dto.setId(Integer.valueOf(req.getParameter(AbstractService.ID)));
        else {
            if (req.getParameterMap().containsKey(PARAM_SAVE)) dto.setId(Integer.valueOf(req.getParameter(PARAM_SAVE)));
            else if (req.getParameterMap().containsKey(PARAM_DELETE))
                dto.setId(Integer.valueOf(req.getParameter(PARAM_DELETE)));
        }
        return dto;
    }
}
