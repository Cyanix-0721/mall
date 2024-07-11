package com.mole.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminServiceTest {
	@Autowired
	private AdminService adminService;

	@Test
	public void testFindAll() {
		System.out.println("testFindAll");
		System.out.println(adminService.findAll());
	}
}
