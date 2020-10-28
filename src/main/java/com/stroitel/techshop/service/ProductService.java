package com.stroitel.techshop.service;

import com.stroitel.techshop.model.Product;
import com.stroitel.techshop.model.ProductCategory;

import java.util.List;

public interface ProductService {

    void add(Product product);

    void update(Product product);

    void delete(Long id);

    Product getById(Long id);

    List<Product> getAll();
}
