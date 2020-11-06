package com.stroitel.techshop.service.impl;

import com.stroitel.techshop.dao.CategoryDAO;
import com.stroitel.techshop.domain.Category;
import com.stroitel.techshop.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;

    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll().stream()
                .sorted(Comparator.comparing(Category::getTitle))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Category findById(long categoryId) {
        return categoryDAO.findById(categoryId).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public Category findByTitle(String title) {
        return categoryDAO.findByTitle(title);
    }

    @Transactional
    @Override
    public Category create(Category newCategory) {
        return categoryDAO.save(newCategory);
    }

    @Transactional
    @Override
    public Category update(long categoryId, Category changedCategory) {
        Optional<Category> originalCategory = categoryDAO.findById(categoryId);
        if (originalCategory.isPresent()){
            changedCategory.setId(originalCategory.get().getId());
            return categoryDAO.save(changedCategory);
        }
        else return null;
    }

    @Transactional
    @Override
    public void delete(long categoryId) {
        categoryDAO.deleteById(categoryId);
    }
}