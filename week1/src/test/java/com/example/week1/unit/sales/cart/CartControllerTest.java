package com.example.week1.unit.sales.cart;

import com.example.week1.TestRequester;
import com.example.week1.sales.cart.CartService;
import com.example.week1.sales.cart.request.CartItemAddingRequest;
import com.example.week1.sales.cart.response.CartItemAddedResponse;
import com.example.week1.sales.cart.response.CartItemsResponse;
import com.example.week1.sales.cart.response.CartSummaryResponse;
import com.example.week1.sales.product.ProductService;
import com.example.week1.user.UserTokenManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static com.example.week1.unit.sales.cart.CartDummyFactory.*;

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

    @Test
    void shouldClearCart() {
        when(tokenManager.decodeTokenToUsername("MyToken")).thenReturn("MyUsername");

        HttpStatus statusCode = requester.deleteWithToken(
                "/cart", "MyToken", CartItemsResponse.class).getStatusCode();

        assert statusCode.equals(HttpStatus.OK);
        verify(cartService).clearCart("MyUsername");
    }

    @Test
    void shouldShowAllItemsWhenSummarizeCart() {
        CartSummaryResponse cartSummaryResponse = summarizeCartWithMock();

        assert cartSummaryResponse != null;
        assertEquals(2, cartSummaryResponse.getItems().size());
        assertEquals(getDummyCartItemResponseA(), cartSummaryResponse.getItems().get(0));
        assertEquals(getDummyCartItemResponseB(), cartSummaryResponse.getItems().get(1));
    }

    @Test
    void shouldReturnTotalPriceWhenSummarizeCart() {
        CartSummaryResponse cartSummaryResponse = summarizeCartWithMock();

        assert cartSummaryResponse != null;
        assertEquals(getTotalPriceOfDummyCartWithProductAAndB(), cartSummaryResponse.getTotalPrice());
    }

    private CartSummaryResponse summarizeCartWithMock() {
        when(productService.getProduct(getDummyProductA().getId())).thenReturn(getDummyProductA());
        when(productService.getProduct(getDummyProductB().getId())).thenReturn(getDummyProductB());
        when(tokenManager.decodeTokenToUsername("MyToken")).thenReturn("MyUsername");
        when(cartService.getMyCart("MyUsername")).thenReturn(getDummyCartWithProductAAndB());

        return requester.getWithToken("/cart", "MyToken", CartSummaryResponse.class).getBody();
    }

}
