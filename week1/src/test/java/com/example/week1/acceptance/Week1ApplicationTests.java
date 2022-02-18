package com.example.week1.acceptance;

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

	void loginWithDefaultUser() {
		dsl.userLogin(USERNAME, PASSWORD);
	}

}
