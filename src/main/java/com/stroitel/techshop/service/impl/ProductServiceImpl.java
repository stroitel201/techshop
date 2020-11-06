package com.stroitel.techshop.service.impl;

import com.stroitel.techshop.dao.ProductDAO;
import com.stroitel.techshop.domain.Category;
import com.stroitel.techshop.domain.Product;
import com.stroitel.techshop.service.CategoryService;
import com.stroitel.techshop.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductDAO productDAO, CategoryService categoryService) {
        this.productDAO = productDAO;
        this.categoryService = categoryService;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return productDAO.findAll().stream()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll(PageRequest request) {
        return productDAO.findAll(request).getContent();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findByCategory(Category category, PageRequest request) {
        return productDAO.findByCategoryOrderByName(category, request).getContent();
    }


    @Transactional(readOnly = true)
    @Override
    public List<Product> findByAvailability(String available, PageRequest request) {
        Page<Product> pagedList;
        if ("all".equals(available)) {
            pagedList = productDAO.findAll(request);
        } else {
            boolean availability = Boolean.parseBoolean(available);
            pagedList = productDAO.findByAvailableOrderByName(availability, request);
        }
        return pagedList.getContent();
    }

    @Transactional(readOnly = true)
    @Override
    public Product getProduct(long productId) {
        return productDAO.findById(productId).get();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(long productId) {
        return productDAO.findById(productId);
    }

    @Transactional
    @Override
    public Product create(Product product, String categoryTitle) {
        return saveInternal(product, categoryTitle, true);
    }

    @Transactional
    @Override
    public Product update(long productId, Product product, String categoryTitle){
        Product original = getProduct(productId);
        product.setId(original.getId());
        return saveInternal(product, categoryTitle, original.isAvailable()); // keep original availability
    }


    private Product saveInternal(Product changed, String categoryTitle, boolean available) {
        Category category = categoryService.findByTitle(categoryTitle);
        if (category != null) {
            changed.setCategory(category);
            changed.setAvailable(available);
            return productDAO.save(changed);
        }
        else return null;
    }

    @Transactional
    @Override
    public void updateAvailability(Map<Boolean, List<Long>> productIdsByAvailability) {
        for (Map.Entry<Boolean, List<Long>> e : productIdsByAvailability.entrySet()) {
            Boolean targetAvailability = e.getKey();
            List<Product> productsToUpdate = e.getValue().stream()
                    .map(this::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .filter(product -> product.isAvailable() != targetAvailability)
                    .collect(Collectors.toList());
            for (Product product : productsToUpdate) {
                product.setAvailable(targetAvailability);
                productDAO.save(product);
            }
        }
    }

    @Transactional
    @Override
    public Product delete(long id) {

        Product product = productDAO.findById(id).get();
        product.setAvailable(false);
        return productDAO.save(product);
    }
}