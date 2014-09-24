package com.epam.bp.autobase.filter;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.H2.H2DaoManager;
import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.entity.Vehicle;
import com.epam.bp.autobase.entity.props.Color;
import com.epam.bp.autobase.entity.props.Manufacturer;
import com.epam.bp.autobase.entity.props.Model;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class EntitiesFillerFilter implements Filter {
    private static final String ENTITY_CHANGES_FLAG = "listsChanged";
    private static final String LIST_USERS = "userList";
    private static final String LIST_VEHICLES = "vehicleList";
    private static final String LIST_COLORS = "colorList";
    private static final String LIST_MODELS = "modelList";
    private static final String LIST_MANUFS = "manufacturerList";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter0((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter0(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        if ((session.getAttribute(ENTITY_CHANGES_FLAG) == null) || (session.getAttribute(ENTITY_CHANGES_FLAG).equals("true"))) {
            try {
                DaoFactory daoFactory = DaoFactory.getInstance();
                H2DaoManager daoManager = daoFactory.getDaoManager();
                daoManager.transactionAndClose(new H2DaoManager.DaoCommand() {
                    @Override
                    public void execute(H2DaoManager daoManager) throws DaoException {
                        List<User> userList = daoManager.getUserDao().getAll();
                        List<Vehicle> vehicleList = daoManager.getVehicleDao().getAll();
                        List<Color> colorList = daoManager.getColorDao().getAll();
                        List<Model> modelList = daoManager.getModelDao().getAll();
                        List<Manufacturer> manufacturerList = daoManager.getManufacturerDao().getAll();
                        session.setAttribute(LIST_USERS, userList);
                        session.setAttribute(LIST_VEHICLES, vehicleList);
                        session.setAttribute(LIST_COLORS, colorList);
                        session.setAttribute(LIST_MODELS, modelList);
                        session.setAttribute(LIST_MANUFS, manufacturerList);
                    }
                });
                daoFactory.releaseContext();
            } catch (Exception e) {
                throw new RuntimeException("Error at EntitiesFillerFilter", e);
            }
            session.removeAttribute(ENTITY_CHANGES_FLAG);
        }

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}