package com.stroitel.techshop.rest;

import com.stroitel.techshop.domain.Contacts;
import com.stroitel.techshop.domain.Token;
import com.stroitel.techshop.domain.UserAccount;
import com.stroitel.techshop.dto.*;
import com.stroitel.techshop.security.jwt.JwtTokenProvider;
import com.stroitel.techshop.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user/")
public class UserRestController {

    private final UserAccountService userAccountService;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenService tokenService;
    private final CartService cartService;
    private final ContactsService contactsService;
    private final OrderService orderService;

    public UserRestController(UserAccountService userAccountService, JwtTokenProvider jwtTokenProvider,
                              TokenService tokenService, CartService cartService,
                              ContactsService contactsService, OrderService orderService) {
        this.userAccountService = userAccountService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenService = tokenService;
        this.cartService = cartService;
        this.contactsService = contactsService;
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity getUser(HttpServletRequest servletRequest){

        UserAccount userAccount = securityCheck(servletRequest);

        if (userAccount != null && userAccount.isActive())
                return ResponseEntity.ok(new UserAccountDto(userAccount));
        else return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @GetMapping("contacts")
    public ResponseEntity getUserContacts(HttpServletRequest servletRequest){

        UserAccount userAccount = securityCheck(servletRequest);

        if (userAccount != null && userAccount.isActive())
                return ResponseEntity.ok(new ContactsDto(contactsService.getContacts(userAccount.getEmail())));
        else return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @PostMapping("contacts")
    public ResponseEntity updateUserContacts(@Valid @RequestBody ContactsDto contactsDto, HttpServletRequest servletRequest){

        UserAccount userAccount = securityCheck(servletRequest);

        if (userAccount != null && userAccount.isActive()) {
                Contacts contacts = new Contacts(userAccount, contactsDto.getPhone(), contactsDto.getAddress(), contactsDto.getCityAndRegion());
                return ResponseEntity.ok(new ContactsDto(contactsService.updateUserContacts(contacts, userAccount.getEmail())));
        }
        else return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @GetMapping("cart")
    public ResponseEntity getUserCart(HttpServletRequest servletRequest){

        UserAccount userAccount = securityCheck(servletRequest);

        if (userAccount != null && userAccount.isActive())
                return ResponseEntity.ok(new CartDto(cartService.getCartOrCreate(userAccount.getEmail())));
        else return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @PostMapping("cart")
    public ResponseEntity addItemToUserCart(@Valid @RequestBody ExAddItemDto exAddItemDto, HttpServletRequest servletRequest){

        UserAccount userAccount = securityCheck(servletRequest);

        if (userAccount != null && userAccount.isActive())
                return ResponseEntity.ok(new CartDto(cartService.addToCart(userAccount.getEmail(), exAddItemDto.getId(), exAddItemDto.getQuantity())));
        else return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @PutMapping("cart")
    public ResponseEntity setDeliveryToUserCart(@RequestBody BooleanDto booleanDto, HttpServletRequest servletRequest){

        UserAccount userAccount = securityCheck(servletRequest);

        if (userAccount != null && userAccount.isActive())
                return ResponseEntity.ok(new CartDto(cartService.setDelivery(userAccount.getEmail(), booleanDto.getBoolValue())));
        else return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("cart")
    public ResponseEntity clearUserCart(HttpServletRequest servletRequest){

        UserAccount userAccount = securityCheck(servletRequest);

        if (userAccount != null && userAccount.isActive())
                return ResponseEntity.ok(new CartDto(cartService.clearCart(userAccount.getEmail())));
        else return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @PostMapping("logout")
    public ResponseEntity logout(HttpServletRequest servletRequest){

        String token = jwtTokenProvider.resolveToken(servletRequest);
        Token tokenFromBd = tokenService.findByToken(token);

        tokenService.delete(tokenFromBd);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("order")
    public ResponseEntity createOrder(HttpServletRequest servletRequest){

        UserAccount userAccount = securityCheck(servletRequest);

        if (userAccount != null && userAccount.isActive()) {

            return ResponseEntity.ok(new OrderDto(orderService.createOrder(userAccount)));
        }
        else return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    private UserAccount securityCheck(HttpServletRequest servletRequest){

        String token = jwtTokenProvider.resolveToken(servletRequest);
        Token tokenFromBd = tokenService.findByToken(token);

        if(tokenFromBd.isValid())
            return userAccountService
                    .findByUsername(jwtTokenProvider
                            .getUsername(token));
        else return null;
    }
}


