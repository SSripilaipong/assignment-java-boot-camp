package com.example.week1.sales.cart;

import com.example.week1.delivery.address.AddressService;
import com.example.week1.payment.PaymentService;
import com.example.week1.sales.cart.request.CartItemAddingRequest;
import com.example.week1.sales.cart.request.SelectCartAddressRequest;
import com.example.week1.sales.cart.request.SelectCartPaymentMethodRequest;
import com.example.week1.sales.cart.response.CartItemAddedResponse;
import com.example.week1.sales.cart.response.CartItemsResponse;
import com.example.week1.sales.cart.response.CartSummaryResponse;
import com.example.week1.sales.product.ProductService;
import com.example.week1.user.UserTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserTokenManager tokenManager;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PaymentService paymentService;

    @PostMapping(
            value = "/cart/items",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CartItemAddedResponse addMyCartItem(
            @RequestHeader("Authorization") String token, @RequestBody CartItemAddingRequest request) {
        cartService.addItemToMyCart(tokenManager.decodeTokenToUsername(token), request.toCartItem());
        return new CartItemAddedResponse("OK");
    }

    @GetMapping("/cart/items")
    public CartItemsResponse getMyCartItems(@RequestHeader("Authorization") String token) {
        return CartItemsResponse.fromCart(
                productService, cartService.getMyCart(tokenManager.decodeTokenToUsername(token)));
    }

    @GetMapping("/cart")
    public CartSummaryResponse summarizeCart(@RequestHeader("Authorization") String token) {
        return CartSummaryResponse.fromCart(
                paymentService, addressService, productService, cartService.getMyCart(tokenManager.decodeTokenToUsername(token)));
    }

    @DeleteMapping("/cart")
    public void clearMyCart(@RequestHeader("Authorization") String token) {
        cartService.clearCart(tokenManager.decodeTokenToUsername(token));
    }

    @PutMapping("/cart/address")
    public void setMyCartAddress(
            @RequestHeader("Authorization") String token, @RequestBody SelectCartAddressRequest request) {
        String username = tokenManager.decodeTokenToUsername(token);
        if (addressService.isMyAddress(username, request.getAddressId())) {
            cartService.setMyCartAddressId(username, request.getAddressId());
        } else {
            // do something
        }
    }

    @PutMapping("/cart/paymentMethod")
    public void setMyCartPaymentMethod(
            @RequestHeader("Authorization") String token, @RequestBody SelectCartPaymentMethodRequest request) {
        String username = tokenManager.decodeTokenToUsername(token);
        if (paymentService.isMyPaymentMethod(username, request.getPaymentMethodId())) {
            cartService.setMyCartPaymentMethodId(username, request.getPaymentMethodId());
        } else {
            // do something
        }
    }

}