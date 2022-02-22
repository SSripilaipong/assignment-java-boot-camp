package com.example.week1.acceptance;

import com.example.week1.TestRequester;
import com.example.week1.delivery.address.AddressResponse;
import com.example.week1.payment.response.PaymentMethodResponse;
import com.example.week1.sales.cart.request.CartItemAddingRequest;
import com.example.week1.sales.cart.request.SelectCartAddressRequest;
import com.example.week1.sales.cart.response.CartItemsResponse;
import com.example.week1.sales.cart.response.CartSummaryResponse;
import com.example.week1.sales.product.Product;
import com.example.week1.sales.product.Products;
import com.example.week1.user.response.CurrentUserResponse;
import com.example.week1.user.request.LoginRequest;
import com.example.week1.user.response.LoginSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Week1ApplicationRestApiDriver {

    @Autowired
    private TestRequester requester;

    private String userToken;

    public void loginWithUsernameAndPassword(String username, String password) {
        ResponseEntity<LoginSuccessResponse> response =
                requester.post("/login", new LoginRequest(username, password), LoginSuccessResponse.class);
        assert response.getStatusCode() == HttpStatus.OK && response.getBody() != null;
        userToken = response.getBody().getToken();
    }

    public String getCurrentUsername() {
        ResponseEntity<CurrentUserResponse> response =
                requester.getWithToken("/user/me", userToken, CurrentUserResponse.class);
        assert response.getStatusCode() == HttpStatus.OK && response.getBody() != null;
        return response.getBody().getUsername();
    }

    public Products searchForProductWithKeyword(String keyword) {
        return requester.get(String.format("/products?keyword=%s", keyword), Products.class).getBody();
    }

    public Product getProductDetailById(int id) {
        return requester.get(String.format("/products/%d", id), Product.class).getBody();
    }

    public void addItemToCartWithProductId(int productId, int quantity) {
        HttpStatus statusCode = requester.postWithToken("/cart/items", userToken,
                new CartItemAddingRequest(productId, quantity), Object.class).getStatusCode();

        assert statusCode == HttpStatus.CREATED;
    }

    public CartItemsResponse getCartItems() {
        return requester.getWithToken(
                "/cart/items", userToken, CartItemsResponse.class).getBody();
    }

    public void clearCart() {
        assert requester.deleteWithToken("/cart", userToken, Object.class)
                .getStatusCode().equals(HttpStatus.OK);
    }

    public CartSummaryResponse summarizeCart() {
        return requester.getWithToken(
                "/cart", userToken, CartSummaryResponse.class).getBody();
    }

    public AddressResponse getDefaultAddress() {
        return requester.getWithToken(
                "/delivery/address/default", userToken, AddressResponse.class).getBody();
    }

    public void setAddressIdOfMyCart(Integer id) {
        requester.putWithToken("/cart/address", userToken, new SelectCartAddressRequest(id), Object.class);
    }

    public PaymentMethodResponse getDefaultPaymentMethod() {
        return requester.getWithToken(
                "/payment/method/default", userToken, PaymentMethodResponse.class).getBody();
    }
}
