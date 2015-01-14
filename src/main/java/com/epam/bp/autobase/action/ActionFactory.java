package com.epam.bp.autobase.action;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static final Map<String, Action> ACTIONS = new HashMap<>();
    public static final String PAGE_MAIN = "main";
    public static final String PAGE_MAIN_DRIVER = "main_driver";
    public static final String PAGE_MAIN_CLIENT = "main_client";
    public static final String PAGE_REGISTER_SUCCESS = "registered";
    public static final String PAGE_ADMIN_BUSES = "admin_buses";
    public static final String PAGE_ADMIN_CARS = "admin_cars";
    public static final String PAGE_ADMIN_COLORS = "admin_colors";
    public static final String PAGE_ADMIN_MANUFACTURERS = "admin_manufacturers";
    public static final String PAGE_ADMIN_MODELS = "admin_models";
    public static final String PAGE_ADMIN_ORDERS = "admin_orders";
    public static final String PAGE_ADMIN_TRUCKS = "admin_trucks";
    public static final String PAGE_ADMIN_USERS = "admin_users";
    public static final String PAGE_ORDERED = "ordered";
    public static final String PAGE_SEARCH_RESULT = "search_result";
    public static final String PAGE_SEARCH_RESULT_GUEST = "search_result_guest";
    public static final String PAGE_CABINET = "cabinet";
    public static final String BUS = "Bus";
    public static final String CAR = "Car";
    public static final String TRUCK = "Truck";
    public static final String USER = "user";
    public static final String COLOR = "Color";
    public static final String MODEL = "Model";
    public static final String MANUFACTURER = "Manufacturer";

    static {
     //   ACTIONS.put("GET/login", new ShowPageAction(PAGE_MAIN));
        ACTIONS.put("GET/registered", new ShowPageAction(PAGE_REGISTER_SUCCESS));
        ACTIONS.put("GET/admin_buses", new ShowPageAction(PAGE_ADMIN_BUSES));
        ACTIONS.put("GET/admin_cars", new ShowPageAction(PAGE_ADMIN_CARS));
        ACTIONS.put("GET/admin_colors", new ShowPageAction(PAGE_ADMIN_COLORS));
        ACTIONS.put("GET/admin_manufacturers", new ShowPageAction(PAGE_ADMIN_MANUFACTURERS));
        ACTIONS.put("GET/admin_models", new ShowPageAction(PAGE_ADMIN_MODELS));
        ACTIONS.put("GET/admin_orders", new ShowPageAction(PAGE_ADMIN_ORDERS));
        ACTIONS.put("GET/admin_trucks", new ShowPageAction(PAGE_ADMIN_TRUCKS));
        ACTIONS.put("GET/admin_users", new ShowPageAction(PAGE_ADMIN_USERS));
        ACTIONS.put("GET/main_driver", new ShowPageAction(PAGE_MAIN_DRIVER));
        ACTIONS.put("GET/ordered", new ShowPageAction(PAGE_ORDERED));
        ACTIONS.put("GET/cabinet", new ShowPageAction(PAGE_CABINET));

        ACTIONS.put("GET/main", new CheckLoginAction());
        ACTIONS.put("GET/search", new SearchAction());
        ACTIONS.put("GET/quit", new LogoutAction());
    //    ACTIONS.put("GET/locale", new ChangeLocaleAction());

    //    ACTIONS.put("POST/login", new LoginAction());
        ACTIONS.put("POST/register", new RegisterAction());
        ACTIONS.put("POST/change_user", new ChangeUserAction());
        ACTIONS.put("POST/change_vehicle", new ChangeVehicleAction());
        ACTIONS.put("POST/change_color", new ChangeSpecAction(COLOR));
        ACTIONS.put("POST/change_model", new ChangeSpecAction(MODEL));
        ACTIONS.put("POST/change_manufacturer", new ChangeSpecAction(MANUFACTURER));
        ACTIONS.put("POST/create_bus", new CreateEntityAction(BUS));
        ACTIONS.put("POST/create_car", new CreateEntityAction(CAR));
        ACTIONS.put("POST/create_truck", new CreateEntityAction(TRUCK));
        ACTIONS.put("POST/create_user", new CreateEntityAction(USER));
        ACTIONS.put("POST/create_color", new CreateEntityAction(COLOR));
        ACTIONS.put("POST/create_model", new CreateEntityAction(MODEL));
        ACTIONS.put("POST/create_manufacturer", new CreateEntityAction(MANUFACTURER));
        ACTIONS.put("POST/order", new OrderAction());
        ACTIONS.put("POST/order_edit", new EditOrderAction());
    }

    public static Action getAction(String actionName) {
        return ACTIONS.get(actionName);
    }
}
