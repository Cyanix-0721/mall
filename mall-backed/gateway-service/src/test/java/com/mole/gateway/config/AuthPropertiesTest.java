package com.mole.gateway.config;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class AuthPropertiesTest {

	@Test
	public void testGetExcludePaths() {
		AuthProperties authProperties = new AuthProperties();
		System.out.println(authProperties.getExcludePaths());
	}
}