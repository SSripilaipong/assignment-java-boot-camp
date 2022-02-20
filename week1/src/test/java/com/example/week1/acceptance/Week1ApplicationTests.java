package com.example.week1.acceptance;

import com.example.week1.cart.response.CartItemsResponse;
import com.example.week1.product.Product;
import com.example.week1.product.Products;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Week1ApplicationTests {

	@Autowired
	private Week1ApplicationDsl dsl;

	private static final String USERNAME = "Santhapon";
	private static final String PASSWORD = "Admin1234";

	@Test
	void shouldBeAbleToLogin() {
		loginWithDefaultUser();
		Assertions.assertTrue(dsl.confirmUserLogin(USERNAME));
	}

	@Test
	void shouldBeAbleToSearchForProductsWithKeyword() {
		Products products = dsl.searchForProductsWithKeyword("NMD");
		Assertions.assertEquals(4, products.size());
		Assertions.assertTrue(dsl.confirmAllProductNamesContainKeyword(products, "NMD"));
	}

	@Test
	void shouldBeAbleToGetDetailOfAProduct() {
		Product product = dsl.getProductDetail(2);
		Assertions.assertEquals("POCA SHOE NMD Sneakers Fashion", product.getName());
		Assertions.assertEquals("These are the best shoes.", product.getDescription());
		Assertions.assertEquals("Poca Shoes", product.getBrand());
		Assertions.assertEquals("Casual", product.getOccasion());
		Assertions.assertEquals(399.00, product.getPrice());
	}

	@Test
	void shouldBeAbleToAddItemToCart() {
		loginWithDefaultUser();
		dsl.addItemToCart(2, 1);
		CartItemsResponse items = dsl.getCartItems();
		Assertions.assertEquals("POCA SHOE NMD Sneakers Fashion", items.get(0).getProductName());
		Assertions.assertEquals(1, items.get(0).getQuantity());
		Assertions.assertEquals(1, items.get(0).getPricePerUnit());
	}

	void loginWithDefaultUser() {
		dsl.userLogin(USERNAME, PASSWORD);
	}

}
