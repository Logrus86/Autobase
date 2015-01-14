package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.DaoManager;
import com.epam.bp.autobase.dao.OrderDao;
import com.epam.bp.autobase.entity.Order;
import com.epam.bp.autobase.util.AttributeSetter;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class EditOrderAction implements Action {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(EditOrderAction.class);
    private static final ActionResult FINISHED = new ActionResult(ActionFactory.PAGE_ADMIN_ORDERS, true);
    private static final String ORDER_ID = "orderId";
    private static final String ORDER = "order";
    private static final String STATUS = "status";
    @Inject
    AttributeSetter as;

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        Integer id = Integer.valueOf(request.getParameter(ORDER_ID));
        Order.Status status = Order.Status.valueOf(request.getParameter(STATUS));
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
                OrderDao orderDao = daoManager.getOrderDao();
                Order order = orderDao.getById(id);
                order.setStatus(status);
                orderDao.update(order);

            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at EditOrderAction while performing transaction");
            throw new ActionException("Error at EditOrderAction while performing transaction", e);
        }
        as.setToSession(ORDER, request.getSession());
        return FINISHED;
    }
}
