package com.stroitel.techshop.dao;

import com.stroitel.techshop.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CartDAO extends CrudRepository<Cart, Long>, JpaRepository<Cart, Long> {

}
