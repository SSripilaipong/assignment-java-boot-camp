package com.example.week1.unit.sales.cart;

import com.example.week1.sales.cart.Cart;
import com.example.week1.sales.cart.CartRepository;
import com.example.week1.sales.cart.CartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.week1.unit.sales.cart.CartDummyFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {
    @Mock
    private CartRepository cartRepository;

    @Test
    void shouldAddItemToCart() {
        when(cartRepository.findById("MyUsername")).thenReturn(Optional.of(new Cart("MyUsername")));
        ArgumentCaptor<Cart> arg = ArgumentCaptor.forClass(Cart.class);

        getCartServiceWithMock().addItemToMyCart("MyUsername", getDummyCartItem());

        verify(cartRepository).save(arg.capture());
        Cart savedCart = arg.getValue();

        assertEquals(getDummyCartWithDummyItem(), savedCart);
    }

    @Test
    void shouldCreateCartWhenCartNotExist() {
        when(cartRepository.findById("MyUsername")).thenReturn(Optional.empty());

        Cart myCart = getCartServiceWithMock().getMyCart("MyUsername");

        assertEquals(new Cart("MyUsername"), myCart);
    }

    @Test
    void shouldClearMyCartIfExists() {
        when(cartRepository.existsById("MyUsername")).thenReturn(true);
        getCartServiceWithMock().clearCart("MyUsername");

        verify(cartRepository).deleteById("MyUsername");
    }

    @Test
    void shouldSetMyCartAddressId() {
        when(cartRepository.findById("MyUsername")).thenReturn(Optional.of(new Cart("MyUsername")));

        getCartServiceWithMock().setMyCartAddressId("MyUsername", 1234);

        verify(cartRepository).save(new Cart("MyUsername", 1234, null));
    }

    @Test
    void shouldSetMyCartPaymentMethodId() {
        when(cartRepository.findById("MyUsername")).thenReturn(Optional.of(new Cart("MyUsername")));

        getCartServiceWithMock().setMyCartPaymentMethodId("MyUsername", 1234);

        verify(cartRepository).save(new Cart("MyUsername", null, 1234));
    }

    private CartService getCartServiceWithMock() {
        CartService cartService = new CartService();
        cartService.setCartRepository(cartRepository);
        return cartService;
    }
}
