package com.stroitel.techshop.rest;

import com.stroitel.techshop.domain.UserAccount;
import com.stroitel.techshop.dto.AdminUserDto;
import com.stroitel.techshop.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestController {

    private final UserAccountService userAccountService;

    public AdminRestController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
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
}