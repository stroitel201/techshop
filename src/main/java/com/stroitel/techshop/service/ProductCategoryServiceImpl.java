package com.stroitel.techshop.service;

import com.stroitel.techshop.model.ProductCategory;
import com.stroitel.techshop.repo.ProductCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepo repo;

    @Override
    public void save(ProductCategory productCategory) {

        repo.save(productCategory);
    }

    @Override
    public void delete(Long id) {

        repo.deleteById(id);
    }

    @Override
    public ProductCategory getById(Long id) {

        return repo.findById(id).orElse(null);
    }

    @Override
    public List<ProductCategory> getAll() {

        List<ProductCategory> list = new ArrayList<>();
        repo.findAll().forEach(list::add);
        return list;
    }
}
