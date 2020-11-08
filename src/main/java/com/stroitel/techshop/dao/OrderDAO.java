package com.stroitel.techshop.dao;

import com.stroitel.techshop.domain.Order;
import com.stroitel.techshop.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order, Long> {

    List<Order> findByUserAccount(UserAccount userAccount);
}
