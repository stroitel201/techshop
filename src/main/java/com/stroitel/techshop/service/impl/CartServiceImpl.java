package com.stroitel.techshop.service.impl;

import com.stroitel.techshop.dao.CartDAO;
import com.stroitel.techshop.domain.Cart;
import com.stroitel.techshop.domain.CartItem;
import com.stroitel.techshop.domain.Product;
import com.stroitel.techshop.domain.UserAccount;
import com.stroitel.techshop.service.CartService;
import com.stroitel.techshop.service.ProductService;
import com.stroitel.techshop.service.UserAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartDAO cartDAO;
    private final UserAccountService userAccountService;
    private final ProductService productService;

    public CartServiceImpl(CartDAO cartDAO, UserAccountService userAccountService, ProductService productService) {
        this.cartDAO = cartDAO;
        this.userAccountService = userAccountService;
        this.productService = productService;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Cart getCartOrCreate(String userEmail) {
        UserAccount account = userAccountService.findByEmail(userEmail);
        Optional<Cart> cartOptional = cartDAO.findById(account.getId());
        if(cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            cart.calculateItemsCost();
            return cart;
        }
        else return createCart(account);
    }

    private Cart createCart(UserAccount account) {
        return cartDAO.save(new Cart(account));
    }

    @Transactional
    @Override
    public Cart addToCart(String userEmail, long productId, int quantity){
        Cart cart = getCartOrCreate(userEmail);
        Product product = productService.getProduct(productId);
        if (product.isAvailable()) {
            cart.update(product, quantity);
            return cartDAO.save(cart);
        } else {
            return cart;
        }
    }

    @Transactional
    @Override
    public Cart addAllToCart(String userEmail, List<CartItem> itemsToAdd) {
        Cart cart = getCartOrCreate(userEmail);
        boolean updated = false;
        for (CartItem item : itemsToAdd) {
            Optional<Product> product = productService.findById(item.getProduct().getId());
            if (product.isPresent() && product.get().isAvailable()) {
                cart.update(product.get(), item.getQuantity());
                updated = true;
            }
        }
        return updated ? cartDAO.save(cart) : cart;
    }

    @Transactional
    @Override
    public Cart setDelivery(String userEmail, boolean deliveryIncluded) {
        Cart cart = getCartOrCreate(userEmail);
        cart.setDeliveryIncluded(deliveryIncluded);
        return cartDAO.save(cart);
    }

    @Transactional
    @Override
    public Cart clearCart(String userEmail) {
        Cart cart = getCartOrCreate(userEmail);
        cart.clear();
        return cartDAO.save(cart);
    }
}
