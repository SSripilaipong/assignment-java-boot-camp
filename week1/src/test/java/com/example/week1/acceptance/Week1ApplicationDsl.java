package com.example.week1.acceptance;

import com.example.week1.delivery.address.AddressResponse;
import com.example.week1.payment.PaymentMethodResponse;
import com.example.week1.sales.cart.response.CartItemResponse;
import com.example.week1.sales.cart.response.CartItemsResponse;
import com.example.week1.sales.cart.response.CartSummaryResponse;
import com.example.week1.sales.product.Product;
import com.example.week1.sales.product.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;

@Component
public class Week1ApplicationDsl {

    @Autowired
    private Week1ApplicationRestApiDriver driver;

    public void userLogin(String username, String password) {
        driver.loginWithUsernameAndPassword(username, password);
    }

    public void confirmUserLogin(String username) {
        String currentUsername = driver.getCurrentUsername();
        assertEquals(username, currentUsername);
    }

    public Products searchForProductsWithKeyword(String keyword) {
        return driver.searchForProductWithKeyword(keyword);
    }

    public void confirmNumberOfProducts(Products products, int numberOfProduct) {
        assertEquals(numberOfProduct, products.size());
    }

    public void confirmAllProductNamesContainKeyword(Products products, String keyword) {
        for(Product product: products) {
            assertTrue(product.getName().contains(keyword));
        }
    }

    public Product getProductDetail(int id) {
        return driver.getProductDetailById(id);
    }

    public void addItemToCart(int productId, int quantity) {
        driver.addItemToCartWithProductId(productId, quantity);
    }

    public CartItemsResponse getCartItems() {
        return driver.getCartItems();
    }

    public void clearCart() {
        driver.clearCart();
    }

    public CartSummaryResponse summarizeCart() {
        return driver.summarizeCart();
    }

    public AddressResponse loadDefaultAddress() {
        return driver.getDefaultAddress();
    }

    public void selectAddressToCart(Integer id) {
        driver.setAddressIdOfMyCart(id);
    }

    public PaymentMethodResponse loadDefaultPaymentMethod() {
        return driver.getDefaultPaymentMethod();
    }

    public void confirmPaymentMethod(
            PaymentMethodResponse paymentMethod, String method, String ownerName, String cardNumber,
            String expire, String cvv) {
        assertNotEquals(paymentMethod, null);
        assertEquals(paymentMethod.getMethod(), method);
        assertEquals(paymentMethod.getOwnerName(), ownerName);
        assertEquals(paymentMethod.getCardNumber(), cardNumber);
        assertEquals(paymentMethod.getExpire(), expire);
        assertEquals(paymentMethod.getCvv(), cvv);
    }

    public void confirmProductDetail(Product product, String name, String description, String brand, String occasion, Double price) {
        assertEquals(name, product.getName());
        assertEquals(description, product.getDescription());
        assertEquals(brand, product.getBrand());
        assertEquals(occasion, product.getOccasion());
        assertEquals(price, product.getPrice());
    }

    public void confirmCartItemDetail(CartItemResponse item, String productName, int quantity, double pricePerUnit) {
        assertEquals(productName, item.getProductName());
        assertEquals(quantity, item.getQuantity());
        assertEquals(pricePerUnit, item.getPricePerUnit());
    }

    public void confirmCartIsEmpty() {
        assertEquals(0, getCartItems().size());
    }

    public void confirmCartSummaryContainsItem(
            CartSummaryResponse summary, int productId, String productName, int quantity, double pricePerUnit) {
        assertThat(summary.getItems(), hasItem(new CartItemResponse(productId, productName, quantity, pricePerUnit)));
    }

    public void confirmNumberOfCartItemInCartSummary(CartSummaryResponse summary, int size) {
        assertEquals(size, summary.getItems().size());
    }

    public void confirmTotalPriceOfCartSummary(CartSummaryResponse summary, double totalPrice) {
        assertEquals(totalPrice, summary.getTotalPrice());
    }
}
