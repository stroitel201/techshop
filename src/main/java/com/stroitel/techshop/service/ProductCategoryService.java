package com.stroitel.techshop.service;

import com.stroitel.techshop.model.Product;
import com.stroitel.techshop.model.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    void save(ProductCategory productCategory);

    void delete(Long id);

    ProductCategory getById(Long id);

    List<ProductCategory> getAll();
}
