package com.epam.bp.autobase.service;

import com.epam.bp.autobase.cdi.SessionState;
import com.epam.bp.autobase.dao.OrderDao;
import com.epam.bp.autobase.dao.hibernate.Hibernate;
import com.epam.bp.autobase.model.dto.OrderDto;
import com.epam.bp.autobase.model.entity.Order;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class OrderService extends AbstractService<Order, OrderDto, OrderDao> {
    @Inject
    @Hibernate
    OrderDao dao;

    @Inject
    Event<Order> event;

    @Inject
    SessionState ss;

    @Override
    public void create(OrderDto dto) throws ServiceException {
        create(dto, dao, event, ss.getLocale());
    }

    @Override
    public OrderDto getById(Integer id) throws ServiceException {
        return getById(id, dao);
    }

    @Override
    public void update(OrderDto dto) throws ServiceException {
        update(dto, dao, event, ss.getLocale());
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        delete(id, dao, event);
    }

    @Override
    public void delete(OrderDto dto) throws ServiceException {
        delete(dto, dao, event);
    }

    @Override
    public OrderDto getDtoFromEntity(Order order) {
        return new OrderDto(order);
    }

    @Override
    public String checkAllFieldsNotBusy(Order or) throws ServiceException {
        return "";
    }

    @Override
    public String checkChangedFieldsNotBusy(Order or, OrderDto dto) throws ServiceException {
        return "";
    }
}
