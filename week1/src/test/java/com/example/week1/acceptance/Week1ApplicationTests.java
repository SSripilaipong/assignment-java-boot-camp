package com.example.week1.acceptance;

import com.example.week1.delivery.address.AddressResponse;
import com.example.week1.sales.cart.response.CartItemResponse;
import com.example.week1.sales.cart.response.CartItemsResponse;
import com.example.week1.sales.cart.response.CartSummaryResponse;
import com.example.week1.sales.product.Product;
import com.example.week1.sales.product.Products;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Week1ApplicationTests {

	@Autowired
	private Week1ApplicationDsl dsl;

	private static final String USERNAME = "Santhapon";
	private static final String PASSWORD = "Admin1234";

	@Test
	void shouldBeAbleToLogin() {
		loginWithDefaultUser();
		assertTrue(dsl.confirmUserLogin(USERNAME));
	}

	@Test
	void shouldBeAbleToSearchForProductsWithKeyword() {
		Products products = dsl.searchForProductsWithKeyword("NMD");
		assertEquals(4, products.size());
		assertTrue(dsl.confirmAllProductNamesContainKeyword(products, "NMD"));
	}

	@Test
	void shouldBeAbleToGetDetailOfAProduct() {
		Product product = dsl.getProductDetail(2);
		assertEquals("POCA SHOE NMD Sneakers Fashion", product.getName());
		assertEquals("These are the best shoes.", product.getDescription());
		assertEquals("Poca Shoes", product.getBrand());
		assertEquals("Casual", product.getOccasion());
		assertEquals(399.00, product.getPrice());
	}

	@Test
	void shouldBeAbleToAddItemToCart() {
		loginWithDefaultUser();
		dsl.clearCart();
		dsl.addItemToCart(2, 1);
		CartItemsResponse items = dsl.getCartItems();
		assertEquals("POCA SHOE NMD Sneakers Fashion", items.get(0).getProductName());
		assertEquals(1, items.get(0).getQuantity());
		assertEquals(399.0, items.get(0).getPricePerUnit());
	}

	@Test
	void shouldBeAbleToClearCart() {
		loginWithDefaultUser();
		dsl.clearCart();
		dsl.addItemToCart(2, 1);
		dsl.clearCart();
		assertEquals(0, dsl.getCartItems().size());
	}

	@Test
	void shouldBeAbleToSummarizeCart() {
		loginWithDefaultUser();
		dsl.clearCart();
		dsl.addItemToCart(0, 1);
		dsl.addItemToCart(2, 3);
		CartSummaryResponse summary = dsl.summarizeCart();

		assertEquals(2, summary.getItems().size());
		assertEquals(new CartItemResponse(0, "Adidas NMD R1 Pimeknit Core Black", 1, 9900.00),
				summary.getItems().get(0));
		assertEquals(new CartItemResponse(2, "POCA SHOE NMD Sneakers Fashion", 3, 399.00),
				summary.getItems().get(1));
		assertEquals(11097.00, summary.getTotalPrice());
	}

	@Test
	void shouldBeAbleToLoadSavedDefaultAddress() {
		loginWithDefaultUser();
		AddressResponse address = dsl.loadDefaultAddress();
		assertEquals("Santhapon Sripilaipong", address.getFullName());
		assertEquals("Somewhere in Thailand", address.getAddress());
		assertEquals("12345", address.getPostCode());
		assertEquals("Somewhere", address.getDistrict());
		assertEquals("Krung Thep Maha Nakhon (Bangkok)", address.getProvince());
		assertEquals("0999999999", address.getPhone());
	}

	void loginWithDefaultUser() {
		dsl.userLogin(USERNAME, PASSWORD);
	}

}
