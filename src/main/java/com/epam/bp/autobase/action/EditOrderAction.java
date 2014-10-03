package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.H2.DaoManager;
import com.epam.bp.autobase.dao.OrderDao;
import com.epam.bp.autobase.entity.Order;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class EditOrderAction implements Action {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(EditOrderAction.class);
    private static final ActionResult FINISHED = new ActionResult("admin-orders", true);
    private static final String ORDER = "order";
    private static final String ORDER_ID = "orderId";
    private static final String STATUS = "status";
    private static final String ENTITY_CHANGES_FLAG = "listsChanged";

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        HttpSession session = request.getSession();
        Integer id = Integer.valueOf(request.getParameter(ORDER_ID));
        Order.Status status = Order.Status.valueOf(request.getParameter(STATUS));
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(daoManager1 -> {
                OrderDao orderDao = daoManager1.getOrderDao();
                Order order = orderDao.getById(id);
                order.setStatus(status);
                orderDao.update(order);
            });
            daoFactory.releaseContext();
            session.setAttribute(ENTITY_CHANGES_FLAG, ORDER);
        } catch (Exception e) {
            LOGGER.error("Error at EditOrderAction while performing transaction");
            throw new ActionException("Error at EditOrderAction while performing transaction", e);
        }
        return FINISHED;
    }
}
