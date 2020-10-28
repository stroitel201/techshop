package com.stroitel.techshop.repo;

import com.stroitel.techshop.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {

}
