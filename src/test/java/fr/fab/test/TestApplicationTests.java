package fr.fab.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.fab.test.controllers.UserController;

@SpringBootTest
class TestApplicationTests {

	@Autowired
	private UserController userController;

	@Test
	void contextLoads() {
		assertNotNull(userController);
	}

}
