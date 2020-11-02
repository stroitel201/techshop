package com.stroitel.techshop.rest;

import com.stroitel.techshop.domain.Contacts;
import com.stroitel.techshop.domain.Role;
import com.stroitel.techshop.domain.UserAccount;
import com.stroitel.techshop.dto.AuthenticationRequestDto;
import com.stroitel.techshop.dto.RegisterUserDto;
import com.stroitel.techshop.dto.UserAccountDto;
import com.stroitel.techshop.security.jwt.JwtTokenProvider;
import com.stroitel.techshop.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserAccountService userAccountService;

    @Autowired
    public AuthenticationRestController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserAccountService userAccountService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userAccountService = userAccountService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            UserAccount user = userAccountService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username);

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("register")
    public ResponseEntity register(@RequestBody RegisterUserDto registerUserDto) {
        UserAccount userAccount = new UserAccount();

        userAccount.setName(registerUserDto.getUsername());
        userAccount.setPassword(registerUserDto.getPassword());
        userAccount.setEmail(registerUserDto.getEmail());

        Contacts contacts = new Contacts(userAccount, registerUserDto.getPhone(), registerUserDto.getAddress(),
                registerUserDto.getCityAndRegion());

        userAccount.setContacts(contacts);
        try {
            userAccountService.create(userAccount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(new UserAccountDto(userAccountService.findByEmail(userAccount.getEmail())));
    }

}