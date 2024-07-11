package com.mole.auth.controller;

import com.mole.common.dto.auth.AdminResult;
import com.mole.common.entity.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AdminLoginControllerTest {

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Autowired
	private AdminLoginController loginController;
	@Autowired
	private StringRedisTemplate redisTemplate;

/*	@Test
	public void LoginTest() {
		// Arrange
		String username = "testUser";
		String password = "pw114514";
		System.out.println("Original: " + password);
		String encryption = passwordEncoder.encode(password);
		System.out.println("Encrypted: " + encryption);
		System.out.println("Decrypted: " + passwordEncoder.matches(password, encryption));

		// Act
		ResponseEntity<Response<AdminResult>> response = loginController.loginAdmin(ad);

		// Assert
		ValueOperations<String, String> ops = redisTemplate.opsForValue();
		String accessToken = ops.get(username + ":access");
		String refreshToken = ops.get(username + ":refresh");

		assertNotNull(accessToken, "access token is null");
		assertNotNull(refreshToken, "refresh token is null");

		assertEquals(200, response.getStatusCodeValue());
		assertTrue(Objects.requireNonNull(response.getBody()).getData().isSuccess());
	}*/

	@Test
	public void registerTest() {
		String userName = "testUser";
		String password = "pw114514";
		loginController.registerAdmin(userName, password);
	}
}