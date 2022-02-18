package com.example.week1.acceptance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Week1ApplicationTests {

	@Autowired
	private Week1ApplicationDsl dsl;

	@Test
	void shouldBeAbleToLogin() {
		dsl.userLogin("Santhapon", "Admin1234");
		Assertions.assertTrue(dsl.confirmUserLogin("Santhapon"));
	}

}
