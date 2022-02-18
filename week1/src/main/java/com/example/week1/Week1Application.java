package com.example.week1;

import com.example.week1.product.Product;
import com.example.week1.product.ProductService;
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

	@PostConstruct
	public void createInitialUser() {
		userService.register("Santhapon", "Admin1234");
	}

	@PostConstruct
	public void createInitialProducts() {
		productService.addNewProduct(new Product(0, "Adidas NMD R1 Pimeknit Core Black"));
		productService.addNewProduct(new Product(1, "Adidas NMD R1 PK Japan Triple Black"));
		productService.addNewProduct(new Product(2, "POCA SHOE NMD Sneakers Fashion"));
		productService.addNewProduct(new Product(3, "Something Irrelevant"));
		productService.addNewProduct(new Product(4, "Adidas NMD R1 Color Core Black"));
	}

	public static void main(String[] args) {
		SpringApplication.run(Week1Application.class, args);
	}

}
