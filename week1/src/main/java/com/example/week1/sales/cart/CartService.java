package com.example.week1.sales.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if(cartRepository.existsById(username)) {
            cartRepository.deleteById(username);
        }
    }

    @Transactional
    public void setMyCartAddressId(String username, Integer addressId) {
        Cart cart = getMyCart(username);
        cart.setAddressId(addressId);
        cartRepository.save(cart);
    }

    @Transactional
    public void setMyCartPaymentMethodId(String username, int paymentMethodId) {
        Cart cart = getMyCart(username);
        cart.setPaymentMethodId(paymentMethodId);
        cartRepository.save(cart);
    }

}
