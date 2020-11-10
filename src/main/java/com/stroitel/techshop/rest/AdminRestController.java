package com.stroitel.techshop.rest;

import com.stroitel.techshop.domain.*;
import com.stroitel.techshop.dto.*;
import com.stroitel.techshop.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestController {

    private final UserAccountService userAccountService;
    private final CartService cartService;
    private final ContactsService contactsService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final OrderService orderService;

    @Value("${upload.path}")
    private String uploadPath;

    public AdminRestController(UserAccountService userAccountService, CartService cartService,
                               ContactsService contactsService, CategoryService categoryService,
                               ProductService productService, OrderService orderService) {
        this.userAccountService = userAccountService;
        this.cartService = cartService;
        this.contactsService = contactsService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("users")
    public ResponseEntity getUsers(){

        return ResponseEntity.ok(userAccountService.getAllUsers()
                .stream()
                .map(AdminUserDto::new)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id) {

        UserAccount user = userAccountService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = new AdminUserDto(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping("users/{id}")
    public ResponseEntity activateUser(@PathVariable(name = "id") Long id){

        UserAccount user = userAccountService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(new AdminUserDto(userAccountService.activate(user)));
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity deactivateUser(@PathVariable(name = "id") Long id){

        UserAccount user = userAccountService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(new AdminUserDto(userAccountService.deactivate(user)));
    }

    @GetMapping("users/{id}/cart")
    public ResponseEntity getUserCart(@PathVariable(name = "id") Long id){

        UserAccount user = userAccountService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(new CartDto(cartService.getCartOrCreate(user.getEmail())));
    }

    @GetMapping("users/{id}/contacts")
    public ResponseEntity getUserContacts(@PathVariable(name = "id") Long id){

        UserAccount user = userAccountService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(new ContactsDto(contactsService.getContacts(user.getEmail())));
    }

    @GetMapping("categories")
    public ResponseEntity getCategories(){

        return ResponseEntity.ok(categoryService.findAll().stream().map(CategoryDto::new).collect(Collectors.toList()));
    }

    @PostMapping("categories")
    public ResponseEntity addCategories(@Valid @RequestBody CategoryDto categoryDto){

        Category category = new Category();
        category.setTitle(categoryDto.getTitle());
        return ResponseEntity.ok(new CategoryDto(categoryService.create(category)));
    }

    @GetMapping("categories/{id}")
    public ResponseEntity getCategory(@PathVariable(name = "id") Long id){

        return ResponseEntity.ok(new CategoryDto(categoryService.findById(id)));
    }

    @PutMapping("categories/{id}")
    public ResponseEntity updateCategory(@PathVariable(name = "id") Long id, @Valid @RequestBody CategoryDto categoryDto){

        Category category = new Category();
        category.setTitle(categoryDto.getTitle());

        return ResponseEntity.ok(new CategoryDto(categoryService.update(id, category)));
    }

    @GetMapping("products")
    public ResponseEntity getProducts(){

        return ResponseEntity.ok(productService.findAll()
                .stream()
                .map(AdminProductDto::new)
                .collect(Collectors.toList()));
    }

    @PostMapping("products")
    public ResponseEntity addProduct(@Valid @RequestBody ExProductDto exProductDto,
                                     @RequestParam("file") MultipartFile file) throws IOException {

        Product product = new Product();
        product.setName(exProductDto.getName());
        product.setDescription(exProductDto.getDescription());
        product.setPrice(exProductDto.getPrice());

        if(file != null){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists())
                uploadDir.mkdir();
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));
            product.setPictureRef(resultFileName);
        }
        return ResponseEntity.ok(new AdminProductDto(productService.create(product, exProductDto.getCategory())));
    }

    @GetMapping("products/{id}")
    public ResponseEntity getProduct(@PathVariable("id") Long id){

        return ResponseEntity.ok(new AdminProductDto(productService.getProduct(id)));
    }

    @PostMapping("products/{id}")
    public ResponseEntity availableProduct(@PathVariable("id") Long id){

        Map<Boolean, List<Long>> map = new HashMap<>();
        List<Long> list = new ArrayList<>();
        list.add(id);

        map.put(true, list);
        productService.updateAvailability(map);

        return ResponseEntity.ok(new AdminProductDto(productService.getProduct(id)));
    }

    @PutMapping("products/{id}")
    public ResponseEntity updateProduct(@PathVariable("id") Long id, @Valid @RequestBody ExProductDto exProductDto,
                                        @RequestParam(value = "file", required = false) MultipartFile file) throws IOException{

        Product product = new Product();
        product.setName(exProductDto.getName());
        product.setDescription(exProductDto.getDescription());
        product.setPrice(exProductDto.getPrice());

        if(file != null){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists())
                uploadDir.mkdir();
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));
            product.setPictureRef(resultFileName);
        }
        return ResponseEntity.ok(new AdminProductDto(productService.update(id, product, exProductDto.getCategory())));
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Long id){

        return ResponseEntity.ok(new AdminProductDto(productService.delete(id)));
    }

    @GetMapping("orders")
    public ResponseEntity getOrders(@RequestParam(name = "user", required = false, defaultValue = "0") Long id){

        if(id != 0){
            return ResponseEntity.ok(orderService.getUserOrders(userAccountService.findById(id))
                    .stream()
                    .map(OrderDto::new)
                    .collect(Collectors.toList()));
        }
        else return ResponseEntity.ok(orderService.getAllOrders()
                .stream()
                .map(OrderDto::new)
                .collect(Collectors.toList()));
    }

    @GetMapping("orders/{id}")
    public ResponseEntity getOrder(@PathVariable("id") Long id){

        return ResponseEntity.ok(new OrderDto(orderService.getById(id)));
    }

    @PutMapping("orders/{id}")
    public ResponseEntity setExecutedOrder(@PathVariable("id") Long id, @RequestBody BooleanDto booleanDto){

        return ResponseEntity.ok(new OrderDto(orderService.setExecuted(id, booleanDto.getBoolValue())));
    }
}