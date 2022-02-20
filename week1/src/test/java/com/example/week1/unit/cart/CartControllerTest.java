package com.example.week1.unit.cart;

import com.example.week1.TestRequester;
import com.example.week1.cart.CartService;
import com.example.week1.cart.request.CartItemAddingRequest;
import com.example.week1.cart.response.CartItemAddedResponse;
import com.example.week1.user.UserTokenManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartControllerTest {

    @MockBean
    CartService cartService;

    @MockBean
    UserTokenManager tokenManager;

    @Autowired
    TestRequester requester;

    @Test
    void shouldAddItemToCartService() {
        when(tokenManager.decodeTokenToUsername("MyToken")).thenReturn("MyUsername");

        CartItemAddingRequest cartItemAddingRequest = new CartItemAddingRequest(1234, 999);
        requester.postWithToken("/cart/items", "MyToken", cartItemAddingRequest, CartItemAddedResponse.class);

        verify(cartService).addItemToMyCart("MyUsername", cartItemAddingRequest.toCartItem());
    }
}
