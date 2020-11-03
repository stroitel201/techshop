package com.stroitel.techshop.rest;

import com.stroitel.techshop.domain.UserAccount;
import com.stroitel.techshop.dto.UserAccountDto;
import com.stroitel.techshop.security.jwt.JwtTokenProvider;
import com.stroitel.techshop.service.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/users/")
public class UserRestController {

    private UserAccountService userAccountService;
    private JwtTokenProvider jwtTokenProvider;

    public UserRestController(UserAccountService userAccountService, JwtTokenProvider jwtTokenProvider) {
        this.userAccountService = userAccountService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping
    public ResponseEntity hello(HttpServletRequest servletRequest){

        UserAccount userAccount = userAccountService
                .findByUsername(jwtTokenProvider
                        .getUsername(jwtTokenProvider
                                .resolveToken(servletRequest)));
        return ResponseEntity.ok(new UserAccountDto(userAccount));
    }
}
