package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.*;
import com.epam.bp.autobase.entity.Vehicle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChangeUserAction implements Action {
    ActionResult temp = new ActionResult("temp");
    ActionResult main_admin = new ActionResult("main",true);

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        HttpSession session = request.getSession();
        String change_result = "";
        if (request.getParameter("save") != null) {
            change_result = "save id: "+request.getParameter("save");
        }
        if (request.getParameter("delete") != null) {
            change_result = "delete id: "+request.getParameter("delete");
            try {
                UserDao userDao = DaoFactory.getInstance().getDaoManager().getUserDao();
                VehicleDao vehicleDao = DaoFactory.getInstance().getDaoManager().getVehicleDao();
                Map<String,String> map = new HashMap<>();
                map.put("DRIVERID",request.getParameter("delete"));
                //find all vehicles linked with our user by driver-id field and set it to null
                List<Vehicle> vehicles = vehicleDao.findByParams(map);
                if (!vehicles.isEmpty()) {
                    for (Vehicle vehicle:vehicles) {
                        vehicle.setDriverId(null);
                        vehicleDao.update(vehicle);
                    }
                }
                //and we can delete our user now
                userDao.delete(Integer.valueOf(request.getParameter("delete")));
                return main_admin;
            } catch (DaoException | InterruptedException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        session.setAttribute("change_result",change_result);
        return temp;
    }
}
