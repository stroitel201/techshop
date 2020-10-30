package com.stroitel.techshop.dao;

import com.stroitel.techshop.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryDAO extends CrudRepository<Category, Long>, JpaRepository<Category, Long> {

    Category findByTitle(String title);
}

