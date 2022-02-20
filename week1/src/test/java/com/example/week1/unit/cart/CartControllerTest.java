package com.example.week1.unit.cart;

import com.example.week1.TestRequester;
import com.example.week1.cart.CartService;
import com.example.week1.cart.request.CartItemAddingRequest;
import com.example.week1.cart.response.CartItemAddedResponse;
import com.example.week1.cart.response.CartItemsResponse;
import com.example.week1.product.ProductService;
import com.example.week1.user.UserTokenManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static com.example.week1.unit.cart.CartDummyFactory.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartControllerTest {

    @MockBean
    CartService cartService;

    @MockBean
    ProductService productService;

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

    @Test
    void shouldReturnStatusCode201AfterAddedItemToCart() {
        when(tokenManager.decodeTokenToUsername("MyToken")).thenReturn("MyUsername");

        HttpStatus statusCode = requester.postWithToken("/cart/items", "MyToken",
                new CartItemAddingRequest(1234, 999), Object.class).getStatusCode();

        assertEquals(HttpStatus.CREATED, statusCode);
    }

    @Test
    void shouldReturnMyCartItems() {
        when(tokenManager.decodeTokenToUsername("MyToken")).thenReturn("MyUsername");
        when(productService.getProduct(getDummyProductId())).thenReturn(getDummyProduct());
        when(cartService.getMyCart("MyUsername")).thenReturn(getDummyCartWithDummyItem());

        CartItemsResponse cartItemsResponse = requester.getWithToken(
                "/cart/items", "MyToken", CartItemsResponse.class).getBody();

        assert cartItemsResponse != null;
        assertEquals(getDummyCartItemResponse(), cartItemsResponse.get(0));
    }

}
