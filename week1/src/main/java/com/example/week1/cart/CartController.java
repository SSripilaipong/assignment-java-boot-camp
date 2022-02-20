package com.example.week1.cart;

import com.example.week1.cart.request.CartItemAddingRequest;
import com.example.week1.cart.response.CartItemAddedResponse;
import com.example.week1.cart.response.CartItemsResponse;
import com.example.week1.product.ProductService;
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

    @DeleteMapping("/cart")
    public void clearMyCart(@RequestHeader("Authorization") String token) {
        cartService.clearCart(tokenManager.decodeTokenToUsername(token));
    }

}
