package com.stroitel.techshop.service.impl;

import com.stroitel.techshop.dao.OrderDAO;
import com.stroitel.techshop.domain.Order;
import com.stroitel.techshop.domain.UserAccount;
import com.stroitel.techshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Transactional(readOnly = true)
    @Override
    public List<Order> getAllOrders() {
        return orderDAO.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Order> getUserOrders(UserAccount userAccount) {
        return orderDAO.findByUserAccount(userAccount);
    }

    @Transactional(readOnly = true)
    @Override
    public Order getById(Long id) {
        return orderDAO.findById(id).get();
    }

    @Transactional
    @Override
    public Order createOrder(UserAccount userAccount) {

        Order order = new Order(userAccount);
        orderDAO.saveAndFlush(order);
        order.calculateTotalPrice();
        return orderDAO.save(order);
    }

    @Transactional
    @Override
    public Order setExecuted(Long id, Boolean isExecuted) {

        Order order = orderDAO.findById(id).get();
        order.setExecuted(isExecuted);
        return orderDAO.save(order);
    }
}
