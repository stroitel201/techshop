package com.stroitel.techshop.service;


import com.stroitel.techshop.domain.Category;
import com.stroitel.techshop.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    List<Product> findAll(PageRequest request);

    List<Product> findByCategory(Category category, PageRequest request);

    Product findByName(String name);

    List<Product> findByAvailability(String available, PageRequest request);

    Product getProduct(long productId);

    Optional<Product> findById(long productId);

    Product create(Product product, String categoryTitle);

    Product update(long productId, Product product, String categoryTitle);

    void updateAvailability(Map<Boolean, List<Long>> productIdsByAvailability);

    Product delete(long id);
}