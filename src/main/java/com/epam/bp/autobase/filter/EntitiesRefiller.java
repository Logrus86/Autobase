package com.epam.bp.autobase.filter;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.H2.DaoManager;
import com.epam.bp.autobase.entity.Autobase;
import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.entity.Vehicle;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class EntitiesRefiller implements Filter {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(EntitiesRefiller.class);
    private static final String ENTITY_CHANGES_FLAG = "listsChanged";
    private static final String LIST_USERS = "userList";
    private static final String LIST_CARS = "carList";
    private static final String LIST_BUSES = "busList";
    private static final String LIST_TRUCKS = "truckList";
    private static final String LIST_COLORS = "colorList";
    private static final String LIST_MODELS = "modelList";
    private static final String LIST_MANUFS = "manufacturerList";
    private static final String USER = "user";
    private static final String VEHICLE = "vehicle";
    private static final String COLOR = "color";
    private static final String MODEL = "model";
    private static final String MANUFACT = "manufacturer";
    private static final String UPDATED = "UPDATED";
    private static final String DRIVER_VEHICLES = "driverVehicles";
    private Autobase autobase;
    private HttpSession session;
    private DaoManager daoManager;
    private String entityChanged;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter0((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter0(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        session = req.getSession();
        if (session.getAttribute(ENTITY_CHANGES_FLAG) != null) {
            entityChanged = (String) session.getAttribute(ENTITY_CHANGES_FLAG);
        }

        if (!UPDATED.equals(entityChanged)) {
            try {
                DaoFactory daoFactory = DaoFactory.getInstance();
                daoManager = daoFactory.getDaoManager();
                autobase = Autobase.getInstance();
                daoManager.transactionAndClose(daoManager1 -> {
                    if (USER.equals(entityChanged)) refillUsers();
                    if (COLOR.equals(entityChanged)) refillColors();
                    if (MODEL.equals(entityChanged)) refillModels();
                    if (MANUFACT.equals(entityChanged)) refillManufacturers();
                    if (VEHICLE.equals(entityChanged)) refillVehicles();
                    if (entityChanged == null) {
                        refillUsers();
                        refillColors();
                        refillModels();
                        refillManufacturers();
                        refillVehicles();
                    }
                });
                daoFactory.releaseContext();
            } catch (Exception e) {
                throw new RuntimeException("Error at EntitiesFillerFilter while performing transaction", e);
            }
            session.setAttribute(ENTITY_CHANGES_FLAG, UPDATED);
        }
        chain.doFilter(req, resp);
    }

    private void refillManufacturers() throws DaoException {
        autobase.setManufacturerList(daoManager.getManufacturerDao().getAll());
        session.setAttribute(LIST_MANUFS, autobase.getManufacturerList());
        LOGGER.info("ManufacturerList was refilled.");
    }

    private void refillModels() throws DaoException {
        autobase.setModelList(daoManager.getModelDao().getAll());
        session.setAttribute(LIST_MODELS, autobase.getModelList());
        LOGGER.info("ModelList was refilled.");
    }

    private void refillColors() throws DaoException {
        autobase.setColorList(daoManager.getColorDao().getAll());
        session.setAttribute(LIST_COLORS, autobase.getColorList());
        LOGGER.info("ColorList was refilled.");
    }

    private void refillUsers() throws DaoException {
        autobase.setUserList(daoManager.getUserDao().getAll());
        session.setAttribute(LIST_USERS, autobase.getUserList());
        LOGGER.info("UserList was refilled.");
    }

    private void refillVehicles() throws DaoException {
        autobase.setVehicleList(daoManager.getVehicleDao().getAll());
        session.setAttribute(LIST_CARS, autobase.getCarList());
        session.setAttribute(LIST_BUSES, autobase.getBusList());
        session.setAttribute(LIST_TRUCKS, autobase.getTruckList());
        User user = (User) session.getAttribute(USER);
        if ((user!=null) && (user.getRole() == User.Role.DRIVER)) {
            //if user is driver, we must renew his vehicle(s) also:
            List<Vehicle> driverVehicles = Autobase.getInstance().getVehicleListByDriver(user);
            if (!driverVehicles.isEmpty()) session.setAttribute(DRIVER_VEHICLES, driverVehicles);
        }
        LOGGER.info("Vehicles lists were refilled.");
    }

    @Override
    public void destroy() {

    }
}