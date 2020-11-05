package com.stroitel.techshop.rest;

import com.stroitel.techshop.domain.Contacts;
import com.stroitel.techshop.domain.Token;
import com.stroitel.techshop.domain.UserAccount;
import com.stroitel.techshop.dto.AdminUserDto;
import com.stroitel.techshop.dto.CartDto;
import com.stroitel.techshop.dto.ContactsDto;
import com.stroitel.techshop.dto.UserAccountDto;
import com.stroitel.techshop.service.CartService;
import com.stroitel.techshop.service.ContactsService;
import com.stroitel.techshop.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestController {

    private final UserAccountService userAccountService;
    private final CartService cartService;
    private final ContactsService contactsService;

    public AdminRestController(UserAccountService userAccountService, CartService cartService, ContactsService contactsService) {
        this.userAccountService = userAccountService;
        this.cartService = cartService;
        this.contactsService = contactsService;
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
}