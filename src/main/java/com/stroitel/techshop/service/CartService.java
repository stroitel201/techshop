package com.stroitel.techshop.service;

import com.stroitel.techshop.domain.Cart;
import com.stroitel.techshop.domain.CartItem;

import java.util.List;

public interface CartService {

    Cart getCartOrCreate(String userEmail);

    Cart addToCart(String userEmail, long productId, int quantity);

    Cart addAllToCart(String userEmail, List<CartItem> itemsToCopy);

    Cart setDelivery(String userEmail, boolean deliveryIncluded);

    Cart clearCart(String userEmail);

    //Cart deleteFromCart(String userEmail, long productId);
}
