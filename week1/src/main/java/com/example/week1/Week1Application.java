package com.example.week1;

import com.example.week1.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Week1Application {

	@Autowired
	private UserService userService;

	@PostConstruct
	public void createInitialUser() {
		userService.register("Santhapon", "Admin1234");
	}

	public static void main(String[] args) {
		SpringApplication.run(Week1Application.class, args);
	}

}
