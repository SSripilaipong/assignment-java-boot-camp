package com.example.week1.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public void addItemToMyCart(String username, CartItem item) {
        Optional<Cart> matchedCart = cartRepository.findById(username);
        Cart cart = matchedCart.orElseGet(() -> new Cart(username));

        cart.addItem(item);

        cartRepository.save(cart);
    }

    public Cart getMyCart(String username) {
        return new Cart();
    }

    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
}
