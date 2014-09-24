package com.epam.bp.autobase.filter;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.H2.DaoManager;
import com.epam.bp.autobase.entity.*;
import com.epam.bp.autobase.entity.props.Color;
import com.epam.bp.autobase.entity.props.Manufacturer;
import com.epam.bp.autobase.entity.props.Model;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EntitiesFillerFilter implements Filter {
    private static final String ENTITY_CHANGES_FLAG = "listsChanged";
    private static final String LIST_USERS = "userList";
    private static final String LIST_VEHICLES = "vehicleList";
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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter0((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter0(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        String entityChanged = (String) session.getAttribute(ENTITY_CHANGES_FLAG);
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(new DaoManager.DaoCommand() {
                @Override
                public void execute(DaoManager daoManager) throws DaoException {
                    if (USER.equals(entityChanged)) {
                        List<User> userList = daoManager.getUserDao().getAll();
                        session.setAttribute(LIST_USERS, userList);
                    }
                    if (VEHICLE.equals(entityChanged)) {
                        List<Vehicle> vehicleList = daoManager.getVehicleDao().getAll();
                        List<Vehicle> carList = carListFromVehicleList(vehicleList);
                        List<Vehicle> busList = busListFromVehicleList(vehicleList);
                        List<Vehicle> truckList = truckListFromVehicleList(vehicleList);
                        session.setAttribute(LIST_VEHICLES, vehicleList);
                        session.setAttribute(LIST_CARS, carList);
                        session.setAttribute(LIST_BUSES, busList);
                        session.setAttribute(LIST_TRUCKS, truckList);
                    }
                    if (COLOR.equals(entityChanged)) {
                        List<Color> colorList = daoManager.getColorDao().getAll();
                        session.setAttribute(LIST_COLORS, colorList);
                    }
                    if (MODEL.equals(entityChanged)) {
                        List<Model> modelList = daoManager.getModelDao().getAll();
                        session.setAttribute(LIST_MODELS, modelList);
                    }
                    if (MANUFACT.equals(entityChanged)) {
                        List<Manufacturer> manufacturerList = daoManager.getManufacturerDao().getAll();
                        session.setAttribute(LIST_MANUFS, manufacturerList);
                    }
                    if (entityChanged == null) {
                        List<User> userList = daoManager.getUserDao().getAll();
                        List<Vehicle> vehicleList = daoManager.getVehicleDao().getAll();
                        List<Color> colorList = daoManager.getColorDao().getAll();
                        List<Model> modelList = daoManager.getModelDao().getAll();
                        List<Manufacturer> manufacturerList = daoManager.getManufacturerDao().getAll();
                        List<Vehicle> carList = carListFromVehicleList(vehicleList);
                        List<Vehicle> busList = busListFromVehicleList(vehicleList);
                        List<Vehicle> truckList = truckListFromVehicleList(vehicleList);
                        session.setAttribute(LIST_USERS, userList);
                        session.setAttribute(LIST_COLORS, colorList);
                        session.setAttribute(LIST_MODELS, modelList);
                        session.setAttribute(LIST_MANUFS, manufacturerList);
                        session.setAttribute(LIST_VEHICLES, vehicleList);
                        session.setAttribute(LIST_CARS, carList);
                        session.setAttribute(LIST_BUSES, busList);
                        session.setAttribute(LIST_TRUCKS, truckList);
                    }
                }
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            throw new RuntimeException("Error at EntitiesFillerFilter", e);
        }
        session.removeAttribute(ENTITY_CHANGES_FLAG);

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }

    private List<Vehicle> carListFromVehicleList(List<Vehicle> vehicleList) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getVehicleType().equals("Car")) result.add(vehicle);
        }
        return result;
    }

    private List<Vehicle> busListFromVehicleList(List<Vehicle> vehicleList) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getVehicleType().equals("Bus")) result.add(vehicle);
        }
        return result;
    }

    private List<Vehicle> truckListFromVehicleList(List<Vehicle> vehicleList) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getVehicleType().equals("Truck")) result.add(vehicle);
        }
        return result;
    }
}