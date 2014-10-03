package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.H2.DaoManager;
import com.epam.bp.autobase.dao.OrderDao;
import com.epam.bp.autobase.dao.UserDao;
import com.epam.bp.autobase.dao.VehicleDao;
import com.epam.bp.autobase.entity.Autobase;
import com.epam.bp.autobase.entity.Order;
import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.entity.Vehicle;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;

public class OrderAction implements Action {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OrderAction.class);
    private static final ActionResult ORDER_FAIL = new ActionResult("search-result");
    private static final ActionResult ORDER_SUCCESS = new ActionResult("ordered");
    private static final String VH_ID = "vhId";
    private static final String DAY_COUNT = "dayCount";
    private static final String ERROR = "order_err";
    private static final String USER = "user";
    private static final String VEHICLE = "vehicle";
    private static final String ENTITY_CHANGES_FLAG = "listsChanged";
    private static final String DATE_START = "dateStart";
    private static final String ORDER = "order";

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        HttpSession session = request.getSession();
        Integer id = Integer.valueOf(request.getParameter(VH_ID));
        BigDecimal dayCount = new BigDecimal(request.getParameter(DAY_COUNT));
        String dateStart = request.getParameter(DATE_START);
        User user = (User) session.getAttribute(USER);
        Autobase autobase = Autobase.getInstance();
        Vehicle vehicle = autobase.getVehicleById(id);

        BigDecimal balance = user.getBalance();
        BigDecimal rent = vehicle.getRentPrice();
        BigDecimal sum = rent.multiply(dayCount);

        LOGGER.info("User balance: "+balance+", rent: "+rent+", days: "+dayCount+", sum: "+sum);

        if (sum.compareTo(balance) == 1) {
            session.setAttribute(ERROR, "Insufficient funds");
            LOGGER.info("Order processing error: Insufficient funds ");
            return ORDER_FAIL;
        }

        user.setBalance(balance.subtract(sum));
        vehicle.setOperable(false);
        Order order = new Order();
        order.setClientId(user.getId());
        order.setVehicleId(vehicle.getId());
        order.setDateStart(dateStart);
        order.setDayCount(dayCount.intValue());
        Date dateOrdered = new Date();
        order.setDateOrdered(dateOrdered);
        order.setSum(sum);
        order.setStatus(Order.Status.PENDING);
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(daoManager1 -> {
                UserDao userDao = daoManager1.getUserDao();
                VehicleDao vehicleDao = daoManager1.getVehicleDao();
                OrderDao orderDao = daoManager1.getOrderDao();
                userDao.update(user);
                vehicleDao.update(vehicle);
                orderDao.create(order);
                //get this order from db to get its id
                Order newOrder = orderDao.getByDateOrdered(dateOrdered);
                session.setAttribute(ORDER, newOrder);
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at OrderAction while performing transaction");
            throw new ActionException("Error at OrderAction while performing transaction", e);
        }
        session.setAttribute(VEHICLE, vehicle);
        session.removeAttribute(ENTITY_CHANGES_FLAG);
        LOGGER.info("User balance: "+balance+", rent: "+rent+", days: "+dayCount+", sum: "+sum);
        return ORDER_SUCCESS;
    }
}
