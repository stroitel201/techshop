package com.stroitel.techshop.rest;

import com.stroitel.techshop.domain.Cart;
import com.stroitel.techshop.domain.Contacts;
import com.stroitel.techshop.domain.Token;
import com.stroitel.techshop.domain.UserAccount;
import com.stroitel.techshop.dto.*;
import com.stroitel.techshop.security.jwt.JwtTokenProvider;
import com.stroitel.techshop.service.CartService;
import com.stroitel.techshop.service.ContactsService;
import com.stroitel.techshop.service.TokenService;
import com.stroitel.techshop.service.UserAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user/")
public class UserRestController {

    private UserAccountService userAccountService;
    private JwtTokenProvider jwtTokenProvider;
    private TokenService tokenService;
    private CartService cartService;
    private ContactsService contactsService;

    public UserRestController(UserAccountService userAccountService, JwtTokenProvider jwtTokenProvider, TokenService tokenService, CartService cartService, ContactsService contactsService) {
        this.userAccountService = userAccountService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenService = tokenService;
        this.cartService = cartService;
        this.contactsService = contactsService;
    }

    @GetMapping
    public ResponseEntity getUser(HttpServletRequest servletRequest){

        String token = jwtTokenProvider.resolveToken(servletRequest);
        Token tokenFromBd = tokenService.findByToken(token);

        if(tokenFromBd.isValid()) {
            UserAccount userAccount = userAccountService
                    .findByUsername(jwtTokenProvider
                            .getUsername(token));
            return ResponseEntity.ok(new UserAccountDto(userAccount));
        }
        else return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @GetMapping("contacts")
    public ResponseEntity getUserContacts(HttpServletRequest servletRequest){

        String token = jwtTokenProvider.resolveToken(servletRequest);
        Token tokenFromBd = tokenService.findByToken(token);

        if(tokenFromBd.isValid()) {
            UserAccount userAccount = userAccountService
                    .findByUsername(jwtTokenProvider
                            .getUsername(token));
            return ResponseEntity.ok(new ContactsDto(contactsService.getContacts(userAccount.getEmail())));
        }
        else return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @PostMapping("contacts")
    public ResponseEntity updateUserContacts(@Valid @RequestBody ContactsDto contactsDto, HttpServletRequest servletRequest){

        String token = jwtTokenProvider.resolveToken(servletRequest);
        Token tokenFromBd = tokenService.findByToken(token);

        if(tokenFromBd.isValid()) {
            UserAccount userAccount = userAccountService
                    .findByUsername(jwtTokenProvider
                            .getUsername(token));

            Contacts contacts = new Contacts(userAccount, contactsDto.getPhone(), contactsDto.getAddress(), contactsDto.getCityAndRegion());

            return ResponseEntity.ok(new ContactsDto(contactsService.updateUserContacts(contacts, userAccount.getEmail())));
        }
        else return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @GetMapping("cart")
    public ResponseEntity getUserCart(HttpServletRequest servletRequest){

        String token = jwtTokenProvider.resolveToken(servletRequest);
        Token tokenFromBd = tokenService.findByToken(token);

        if(tokenFromBd.isValid()) {
            UserAccount userAccount = userAccountService
                    .findByUsername(jwtTokenProvider
                            .getUsername(token));

            return ResponseEntity.ok(new CartDto(cartService.getCartOrCreate(userAccount.getEmail())));
        }
        else return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @PostMapping("cart")
    public ResponseEntity addItemToUserCart(@Valid @RequestBody AddItemDto addItemDto, HttpServletRequest servletRequest){

        String token = jwtTokenProvider.resolveToken(servletRequest);
        Token tokenFromBd = tokenService.findByToken(token);

        if(tokenFromBd.isValid()) {
            UserAccount userAccount = userAccountService
                    .findByUsername(jwtTokenProvider
                            .getUsername(token));

            return ResponseEntity.ok(new CartDto(cartService.addToCart(userAccount.getEmail(), addItemDto.getId(), addItemDto.getQuantity())));
        }
        else return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @PutMapping("cart")
    public ResponseEntity setDeliveryToUserCart(@RequestBody BooleanDto booleanDto, HttpServletRequest servletRequest){

        String token = jwtTokenProvider.resolveToken(servletRequest);
        Token tokenFromBd = tokenService.findByToken(token);

        if(tokenFromBd.isValid()) {
            UserAccount userAccount = userAccountService
                    .findByUsername(jwtTokenProvider
                            .getUsername(token));

            return ResponseEntity.ok(new CartDto(cartService.setDelivery(userAccount.getEmail(), booleanDto.getDeliveryIncluded())));
        }
        else return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("cart")
    public ResponseEntity clearUserCart(HttpServletRequest servletRequest){

        String token = jwtTokenProvider.resolveToken(servletRequest);
        Token tokenFromBd = tokenService.findByToken(token);

        if(tokenFromBd.isValid()) {
            UserAccount userAccount = userAccountService
                    .findByUsername(jwtTokenProvider
                            .getUsername(token));

            return ResponseEntity.ok(new CartDto(cartService.clearCart(userAccount.getEmail())));
        }
        else return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @PostMapping("logout")
    public ResponseEntity logout(HttpServletRequest servletRequest){
        String token = jwtTokenProvider.resolveToken(servletRequest);
        Token tokenFromBd = tokenService.findByToken(token);

        tokenService.delete(tokenFromBd);
        return new ResponseEntity(HttpStatus.OK);
    }
}
