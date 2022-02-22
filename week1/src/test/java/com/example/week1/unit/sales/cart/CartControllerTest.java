package com.example.week1.unit.sales.cart;

import com.example.week1.TestRequester;
import com.example.week1.delivery.address.Address;
import com.example.week1.delivery.address.AddressService;
import com.example.week1.payment.PaymentService;
import com.example.week1.sales.cart.Cart;
import com.example.week1.sales.cart.CartService;
import com.example.week1.sales.cart.request.SelectCartAddressRequest;
import com.example.week1.sales.cart.request.CartItemAddingRequest;
import com.example.week1.sales.cart.request.SelectCartPaymentMethodRequest;
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
    AddressService addressService;

    @MockBean
    PaymentService paymentService;

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

    @Test
    void shouldReturnAddressWhenSummarizeCart() {
        when(tokenManager.decodeTokenToUsername("MyToken")).thenReturn("MyUsername");
        when(addressService.getAddress(5678)).thenReturn(new Address("", "MyAddress", "", "", "", ""));
        when(cartService.getMyCart("MyUsername")).thenReturn(new Cart("MyUsername", 5678, null));

        CartSummaryResponse response = requester.getWithToken("/cart", "MyToken", CartSummaryResponse.class).getBody();

        assert response != null;
        assertEquals("MyAddress", response.getAddress());
    }

    @Test
    void shouldSetAddressForCart() {
        when(tokenManager.decodeTokenToUsername("MyToken")).thenReturn("MyUsername");
        when(addressService.isMyAddress("MyUsername", 1234)).thenReturn(true);

        SelectCartAddressRequest request = new SelectCartAddressRequest(1234);
        requester.putWithToken("/cart/address", "MyToken", request, Object.class);

        verify(cartService).setMyCartAddressId("MyUsername", 1234);
    }

    @Test
    void shouldSetPaymentMethodForCart() {
        when(tokenManager.decodeTokenToUsername("MyToken")).thenReturn("MyUsername");
        when(paymentService.isMyPaymentMethod("MyUsername", 1234)).thenReturn(true);

        SelectCartPaymentMethodRequest request = new SelectCartPaymentMethodRequest(1234);
        requester.putWithToken("/cart/paymentMethod", "MyToken", request, Object.class);

        verify(cartService).setMyCartPaymentMethodId("MyUsername", 1234);
    }

    private CartSummaryResponse summarizeCartWithMock() {
        when(productService.getProduct(getDummyProductA().getId())).thenReturn(getDummyProductA());
        when(productService.getProduct(getDummyProductB().getId())).thenReturn(getDummyProductB());
        when(tokenManager.decodeTokenToUsername("MyToken")).thenReturn("MyUsername");
        when(cartService.getMyCart("MyUsername")).thenReturn(getDummyCartWithProductAAndB());

        return requester.getWithToken("/cart", "MyToken", CartSummaryResponse.class).getBody();
    }

}
