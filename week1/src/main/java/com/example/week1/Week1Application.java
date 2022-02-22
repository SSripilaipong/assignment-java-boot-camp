package com.example.week1;

import com.example.week1.delivery.address.Address;
import com.example.week1.delivery.address.AddressService;
import com.example.week1.payment.PaymentMethod;
import com.example.week1.payment.PaymentService;
import com.example.week1.sales.product.Product;
import com.example.week1.sales.product.ProductService;
import com.example.week1.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Week1Application {

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private PaymentService paymentService;

	@PostConstruct
	public void createInitialUser() {
		userService.register("Santhapon", "Admin1234");
	}

	@PostConstruct
	public void createInitialProducts() {
		productService.addNewProduct(new Product(0, "Adidas NMD R1 Pimeknit Core Black", 9900.00, "", "", ""));
		productService.addNewProduct(new Product(1, "Adidas NMD R1 PK Japan Triple Black", 12900.00, "", "", ""));
		productService.addNewProduct(new Product(2, "POCA SHOE NMD Sneakers Fashion", 399.00, "These are the best shoes.", "Poca Shoes", "Casual"));
		productService.addNewProduct(new Product(3, "Something Irrelevant", 99999.0, "", "", ""));
		productService.addNewProduct(new Product(4, "Adidas NMD R1 Color Core Black", 7990.00, "", "", ""));
	}

	@PostConstruct
	public void setInitialDefaultAddress() {
		Address address = new Address("Santhapon Sripilaipong", "Somewhere in Thailand",
				"12345", "Somewhere", "Krung Thep Maha Nakhon (Bangkok)",
				"0999999999");
		addressService.setMyDefaultAddress("Santhapon", address);
	}

	@PostConstruct
	public void setInitialDefaultPaymentMethod() {
		PaymentMethod paymentMethod = new PaymentMethod(1, "creditCard", "Santhapon Sripilaipong",
				"1111222233334444", "11/12", "999");
		paymentService.setMyDefaultPaymentMethod("Santhapon", paymentMethod);
	}

	public static void main(String[] args) {
		SpringApplication.run(Week1Application.class, args);
	}

}
