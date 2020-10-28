package com.stroitel.techshop.rest;

import com.stroitel.techshop.model.Product;
import com.stroitel.techshop.model.ProductCategory;
import com.stroitel.techshop.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class restProductCategoryController {

    @Autowired
    private ProductCategoryService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductCategory>> getAllCategories(){

        List<ProductCategory> list = service.getAll();

        if(list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductCategory> getCategory(@PathVariable("id") Long id){

        ProductCategory productCategory = service.getById(id);

        if(productCategory == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(productCategory, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductCategory>> addCategory(@RequestBody ProductCategory productCategory){

        if(productCategory == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        service.save(productCategory);

        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductCategory> updateCategory(@RequestBody ProductCategory productCategory){

        if(productCategory == null)
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        service.save(productCategory);
        return new ResponseEntity<>(productCategory, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductCategory>> deleteCategory(@PathVariable("id") Long id){

        ProductCategory productCategory = service.getById(id);

        if(productCategory == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        service.delete(id);
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

}
