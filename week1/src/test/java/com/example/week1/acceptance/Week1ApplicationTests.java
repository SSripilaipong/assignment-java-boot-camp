package com.example.week1.acceptance;

import com.example.week1.delivery.address.AddressResponse;
import com.example.week1.payment.response.PaymentMethodResponse;
import com.example.week1.sales.cart.response.CartItemsResponse;
import com.example.week1.sales.cart.response.CartSummaryResponse;
import com.example.week1.sales.product.Product;
import com.example.week1.sales.product.Products;
import org.junit.jupiter.api.Test;
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
		dsl.confirmUserLogin(USERNAME);
	}

	@Test
	void shouldBeAbleToSearchForProductsWithKeyword() {
		Products products = dsl.searchForProductsWithKeyword("NMD");
		dsl.confirmNumberOfProducts(products, 4);
		dsl.confirmAllProductNamesContainKeyword(products, "NMD");
	}

	@Test
	void shouldBeAbleToGetDetailOfAProduct() {
		Product product = dsl.getProductDetail(2);
		dsl.confirmProductDetail(product, "POCA SHOE NMD Sneakers Fashion",
				"These are the best shoes.", "Poca Shoes", "Casual", 399.00);
	}

	@Test
	void shouldBeAbleToAddItemToCart() {
		loginWithDefaultUser();
		dsl.clearCart();
		dsl.addItemToCart(2, 1);
		CartItemsResponse items = dsl.getCartItems();
		dsl.confirmCartItemDetail(items.get(0),
				"POCA SHOE NMD Sneakers Fashion", 1, 399.0);
	}

	@Test
	void shouldBeAbleToClearCart() {
		loginWithDefaultUser();
		dsl.clearCart();
		dsl.addItemToCart(2, 1);
		dsl.clearCart();
		dsl.confirmCartIsEmpty();
	}

	@Test
	void shouldBeAbleToSummarizeCartItemsAndPrice() {
		loginWithDefaultUser();
		dsl.clearCart();
		dsl.addItemToCart(0, 1);
		dsl.addItemToCart(2, 3);
		CartSummaryResponse summary = dsl.summarizeCart();

		dsl.confirmNumberOfCartItemInCartSummary(summary, 2);
		dsl.confirmCartSummaryContainsItem(summary, 0, "Adidas NMD R1 Pimeknit Core Black", 1, 9900.00);
		dsl.confirmCartSummaryContainsItem(summary, 2, "POCA SHOE NMD Sneakers Fashion", 3, 399.00);
		dsl.confirmTotalPriceOfCartSummary(summary, 11097.00);
	}

	@Test
	void shouldBeAbleToLoadSavedDefaultAddress() {
		loginWithDefaultUser();
		AddressResponse address = dsl.loadDefaultAddress();
		dsl.confirmAddress(address, "Santhapon Sripilaipong", "Somewhere in Thailand",
				"12345", "Somewhere", "Krung Thep Maha Nakhon (Bangkok)",
				"0999999999");
	}

	@Test
	void shouldBeAbleToSelectAddressToDeliverTheirCart() {
		loginWithDefaultUser();
		dsl.clearCart();
		dsl.selectAddressToCart(dsl.loadDefaultAddress().getId());
		CartSummaryResponse summary = dsl.summarizeCart();

		dsl.confirmAddressInCartSummary(summary, "Somewhere in Thailand");
	}

	@Test
	void shouldBeAbleToLoadSavedDefaultPaymentMethod() {
		loginWithDefaultUser();
		PaymentMethodResponse paymentMethod = dsl.loadDefaultPaymentMethod();
		dsl.confirmPaymentMethod(paymentMethod, "creditCard", "Santhapon Sripilaipong",
				"1111222233334444", "11/12", "999");
	}

	@Test
	void shouldBeAbleToSelectPaymentMethodForTheirCart() {
		loginWithDefaultUser();
		dsl.selectPaymentMethodForCart(1);
		CartSummaryResponse summary = dsl.summarizeCart();

		dsl.confirmPaymentCardNumberInCartSummary(summary, "1111222233334444");
	}

	void loginWithDefaultUser() {
		dsl.userLogin(USERNAME, PASSWORD);
	}

}
