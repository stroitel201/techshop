package com.stroitel.techshop.rest;

import com.stroitel.techshop.model.Product;
import com.stroitel.techshop.model.ProductCategory;
import com.stroitel.techshop.service.ProductCategoryService;
import com.stroitel.techshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/categories/{id}/products")
public class restProductController {

    @Autowired
    private ProductService service;
    @Autowired
    private ProductCategoryService categoryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getAllProducts(){

        List<Product> list = service.getAll();

        if(list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "{idp}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable("idp") Long id){

        Product product = service.getById(id);

        if(product == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> addProduct(@PathVariable("id") Long id, @RequestBody Product product){

        if(product == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        ProductCategory category = categoryService.getById(id);
        category.getProducts().add(product);
        categoryService.save(category);
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PutMapping(value = "{idp}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){

        if(product == null)
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        service.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping(value = "{idp}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> deleteProduct(@PathVariable("id") Long categoryId, @PathVariable("idp") Long productId){

        Product product = service.getById(productId);

        if(product == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ProductCategory productCategory = categoryService.getById(categoryId);
        productCategory.getProducts().remove(product);
        categoryService.save(productCategory);
        service.delete(productId);
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

}
