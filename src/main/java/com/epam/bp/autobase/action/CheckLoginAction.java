package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.entity.Vehicle;
import com.epam.bp.autobase.entity.props.Color;
import com.epam.bp.autobase.entity.props.Manufacturer;
import com.epam.bp.autobase.entity.props.Model;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CheckLoginAction implements Action {
    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(super.getClass());
    private ActionResult loginAdmin = new ActionResult("main-admin");
    private ActionResult loginClient = new ActionResult("main-client");
    private ActionResult loginDriver = new ActionResult("main-driver");
    private ActionResult loginFalse = new ActionResult("main");

    @Override
    public ActionResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("errormsg", "");
        session.setAttribute("search_error", "");
    //    session.setAttribute("change_error", "");

        //TODO WHERE IT MUST BE DONE ?
        if (session.getAttribute("manufacturerList") == null) {
            LOGGER.info("Lists of entities are empty, filling...");
            List<User> userList = null;
            List<Vehicle> vehicleList = null;
            List<Color> colorList = null;
            List<Model> modelList = null;
            List<Manufacturer> manufacturerList = null;
            try {
                userList = DaoFactory.getInstance().getDaoManager().getUserDao().getAll();
                vehicleList = DaoFactory.getInstance().getDaoManager().getVehicleDao().getAll();
                colorList = DaoFactory.getInstance().getDaoManager().getColorDao().getAll();
                modelList = DaoFactory.getInstance().getDaoManager().getModelDao().getAll();
                manufacturerList = DaoFactory.getInstance().getDaoManager().getManufacturerDao().getAll();
            } catch (DaoException | ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
            session.setAttribute("userList", userList);
            session.setAttribute("vehicleList", vehicleList);
            session.setAttribute("colorList", colorList);
            session.setAttribute("modelList", modelList);
            session.setAttribute("manufacturerList", manufacturerList);
        }

        if (session.getAttribute("user") == null) return loginFalse;
        User user = (User) session.getAttribute("user");

        if (user.getRole() == User.Role.ADMIN) return loginAdmin;
        if (user.getRole() == User.Role.CLIENT) return loginClient;
        if (user.getRole() == User.Role.DRIVER) return loginDriver;

        return loginFalse;
    }
}
