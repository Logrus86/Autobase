package com.epam.bp.autobase.jsp.servlet;

import com.epam.bp.autobase.ejb.SessionState;
import com.epam.bp.autobase.model.dto.VehicleDto;
import com.epam.bp.autobase.service.VehicleService;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@WebServlet({
        "do/search"
})
public class SearchServlet extends HttpServlet {
    @Inject
    SessionState ss;
    @Inject
    private Logger logger;
    @Inject
    private VehicleService vs;
    private static final String PAGE_ERROR = "/WEB-INF/jsp/error.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            VehicleDto example = getVehicleDtoFromRequest(req);
            if (example != null) {
                logger.info("Trying to search vehicle: " + example.buildLazyEntity());
                List<VehicleDto> result = vs.findByParams(example);
                req.getSession().setAttribute("foundedList", result);
            } else logger.info("Search parameters are not pointed.");
        } catch (Exception e) {
            logger.error(e);
            logger.error(e.getMessage(), e.getCause());
            req.setAttribute("statusCode", 500);
            req.setAttribute("message", e.getMessage());
            RequestDispatcher resultView = req.getRequestDispatcher(PAGE_ERROR);
            resultView.forward(req, resp);
        }
        RequestDispatcher rd;
        if (ss.getSessionUser() != null) rd = req.getRequestDispatcher("/WEB-INF/jsp/search_result.jsp");
        else rd = req.getRequestDispatcher("/WEB-INF/jsp/search_result_guest.jsp");
        rd.forward(req, resp);
    }

    private VehicleDto getVehicleDtoFromRequest(HttpServletRequest req) throws ServletException {
        VehicleDto dto = new VehicleDto();
        for (Field field : VehicleDto.class.getDeclaredFields()) {
            if (req.getParameterMap().containsKey(field.getName()))
                try {
                    field.setAccessible(true);
                    if (("mileage").equals(field.getName()) || ("rentPrice").equals(field.getName()) || ("maxPayload".equals(field.getName()))) {
                        field.set(dto, field.getType().getConstructor(String.class).newInstance(req.getParameter(field.getName())));
                    } else {
                        if (("modelDto".equals(field.getName())) || ("manufacturerDto".equals(field.getName())) || ("colorDto".equals(field.getName()))) {
                            logger.info("field.getName: "+field.getName()+", "+req.getParameter(field.getName()));
                                //todo
                            field.set(dto, field.getType().getConstructor(req.getParameter(field.getName()).getClass()).newInstance(req.getParameter(field.getName())));
                        } else
                            field.set(dto, field.getType().getDeclaredMethod("valueOf", String.class).invoke(field, req.getParameter(field.getName())));
                    }
                    field.setAccessible(false);
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
                    logger.error("Field " + field.getName() + " of class: " + field.getType() + " setting error with value: " + req.getParameter(field.getName()), e);
                    throw new ServletException(e);
                }
        }
        return dto;
    }
}
