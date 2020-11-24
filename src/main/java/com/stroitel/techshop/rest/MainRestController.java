package com.stroitel.techshop.rest;

import com.stroitel.techshop.domain.Category;
import com.stroitel.techshop.dto.CategoryDto;
import com.stroitel.techshop.dto.UserProductDto;
import com.stroitel.techshop.service.CategoryService;
import com.stroitel.techshop.service.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/main/")
public class MainRestController {

    private final CategoryService categoryService;
    private final ProductService productService;

    public MainRestController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("categories")
    public ResponseEntity getCategories(){

        return ResponseEntity.ok(categoryService.findAll()
                    .stream()
                    .map(CategoryDto::new)
                    .collect(Collectors.toList()));
    }

    @GetMapping("categories/{id}")
    public ResponseEntity getCategory(@PathVariable("id") Long id){

        return ResponseEntity.ok(new CategoryDto(categoryService.findById(id)));
    }

    @GetMapping("products")
    public ResponseEntity getProducts(@RequestParam(name = "name", required = false) String name,
                                      @RequestParam(name = "category", required = false) String categoryTitle,
                                      @RequestParam(name = "page", defaultValue = "0") Integer pageNumber,
                                      @RequestParam(name = "size", defaultValue = "20") Integer pageSize){

        if(name != null)
            return ResponseEntity.ok((productService.findByName(name, PageRequest.of(pageNumber, pageSize)))
                        .stream()
                        .map(UserProductDto::new)
                        .collect(Collectors.toList()));
        if(categoryTitle != null){
            Category category = categoryService.findByTitle(categoryTitle);
            return ResponseEntity.ok(productService.findByCategory(category, PageRequest.of(pageNumber, pageSize))
                        .stream()
                        .map(UserProductDto::new)
                        .collect(Collectors.toList()));
        }
        else return ResponseEntity.ok(productService.findAll(PageRequest.of(pageNumber, pageSize))
                    .stream()
                    .map(UserProductDto::new)
                    .collect(Collectors.toList()));
    }

    @GetMapping("products/{id}")
    public ResponseEntity getProduct(@PathVariable("id") Long id){

        return ResponseEntity.ok(new UserProductDto(productService.getProduct(id)));
    }

}
