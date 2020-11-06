package com.stroitel.techshop.service;

import com.stroitel.techshop.domain.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(long categoryId);

    Category findByTitle(String title);

    Category create(Category newCategory);

    Category update(long categoryId, Category changedCategory);

    void delete(long categoryId);
}

