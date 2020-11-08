package com.stroitel.techshop.service;

import com.stroitel.techshop.domain.Order;
import com.stroitel.techshop.domain.UserAccount;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    List<Order> getUserOrders(UserAccount userAccount);

    Order getById(Long id);

    Order createOrder(UserAccount userAccount);

    Order setExecuted(Long id, Boolean isExecuted);
}
