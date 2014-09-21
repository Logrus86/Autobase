package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.*;
import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.entity.Vehicle;
import com.epam.bp.autobase.entity.props.Color;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CheckLoginAction implements Action {
    private ActionResult loginAdmin = new ActionResult("main-admin");
    private ActionResult loginClient = new ActionResult("main-client");
    private ActionResult loginDriver = new ActionResult("main-driver");
    private ActionResult loginFalse = new ActionResult("main");

    @Override
    public ActionResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("errormsg","");
        session.setAttribute("search_error", "");

        List<User> userList = null;
        List<Vehicle> vehicleList = null;
        List<Color> colorList = null;
        try {
            UserDao userDao = DaoFactory.getInstance().getDaoManager().getUserDao();
            userList = userDao.getAll();
            VehicleDao vehicleDao = DaoFactory.getInstance().getDaoManager().getVehicleDao();
            vehicleList = vehicleDao.getAll();
            ColorDao colorDao = DaoFactory.getInstance().getDaoManager().getColorDao();
            colorList = colorDao.getAll();
        } catch (DaoException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
        session.setAttribute("userList",userList);
        session.setAttribute("vehicleList",vehicleList);
        session.setAttribute("colorList", colorList);

        List<String> list_manufacturers = new ArrayList<>(); //TODO get from database
        list_manufacturers.add("Audi");
        list_manufacturers.add("BMW");
        list_manufacturers.add("Buick");
        list_manufacturers.add("Cadillack");
        list_manufacturers.add("Chevrolet");
        list_manufacturers.add("Ford");
        list_manufacturers.add("Honda");
        list_manufacturers.add("Hyundai");
        list_manufacturers.add("MAN");
        list_manufacturers.add("Mercedes");
        list_manufacturers.add("Mercedes-Benz");
        list_manufacturers.add("Mitsubishi");
        list_manufacturers.add("Nissan");
        list_manufacturers.add("Peugeot");
        list_manufacturers.add("Renault");
        list_manufacturers.add("Saab");
        list_manufacturers.add("Suzuki");
        list_manufacturers.add("Toyota");
        list_manufacturers.add("Volkswagen");
        session.setAttribute("list_manufacturers",list_manufacturers);
        List<String> list_models = new ArrayList<>(); //TODO get from database
        list_models.add("A4");
        list_models.add("A6");
        list_models.add("A8");
        list_models.add("B4");
        list_models.add("B5");
        list_models.add("Beetle");
        list_models.add("C3");
        list_models.add("C6");
        list_models.add("Civic");
        list_models.add("Corolla");
        list_models.add("Cruze");
        list_models.add("Cruise");
        list_models.add("CRX");
        list_models.add("Golf");
        list_models.add("Impala");
        list_models.add("M2");
        list_models.add("M8");
        list_models.add("Passat");
        list_models.add("Sentra");
        list_models.add("Sunny");
        list_models.add("Pulsar");
        list_models.add("S3");
        list_models.add("S4");
        list_models.add("X5");
        session.setAttribute("list_models",list_models);

        if (session.getAttribute("user") == null) return loginFalse;
        User user = (User) session.getAttribute("user");

        if (user.getRole() == User.Role.ADMIN) return loginAdmin;
        if (user.getRole() == User.Role.CLIENT) return loginClient;
        if (user.getRole() == User.Role.DRIVER) return loginDriver;

        return loginFalse;
    }
}
