package com.stroitel.techshop.dao;

import com.stroitel.techshop.domain.Category;
import com.stroitel.techshop.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<Product, Long>, JpaRepository<Product, Long> {

    Page<Product> findByCategoryOrderByName(Category category, Pageable request);

    Page<Product> findAllByNameContains(String name, Pageable request);

    Page<Product> findByAvailableOrderByName(boolean available, Pageable request);
}