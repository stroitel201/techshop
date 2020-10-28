package com.stroitel.techshop.service;

import com.stroitel.techshop.model.Product;
import com.stroitel.techshop.model.ProductCategory;
import com.stroitel.techshop.repo.ProductCategoryRepo;
import com.stroitel.techshop.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo repo;

    @Override
    public void add(Product product) {

        repo.save(product);
    }

    @Override
    public void update(Product product) {

        getById(product.getId()).setName(product.getName());
    }

    @Override
    public void delete(Long id) {

        repo.deleteById(id);
    }

    @Override
    public Product getById(Long id) {

        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAll() {

        List<Product> list = new ArrayList<>();
        repo.findAll().forEach(list::add);
        return list;
    }
}
