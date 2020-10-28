package com.stroitel.techshop.repo;

import com.stroitel.techshop.model.ProductCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepo extends CrudRepository<ProductCategory, Long> {

}
