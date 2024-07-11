package com.mole.user.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminControllerTest {
	@Autowired
	AdminController adminController;

	@Test
	public void testFindAll() {
		System.out.println("testFindAll");
		System.out.println(adminController.findAllAdmins());
	}
}
