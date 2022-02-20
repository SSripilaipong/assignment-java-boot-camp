package com.example.week1.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public void addItemToMyCart(String username, CartItem item) {
        Cart cart = getMyCart(username);

        cart.addItem(item);

        cartRepository.save(cart);
    }

    public Cart getMyCart(String username) {
        return cartRepository.findById(username).orElseGet(() -> new Cart(username));
    }

    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void clearCart(String username) {
        cartRepository.deleteById(username);
    }
}
