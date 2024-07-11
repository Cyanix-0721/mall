package com.mole.auth.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class JwtUtilTest {

	@Test
	public void testCreateToken() {
		Map<String, Object> map = new HashMap<>();
		map.put("username", "zhangsan");
		map.put("role", "user");
		JwtUtil jwtUtil = new JwtUtil(86400000);
		System.out.println(jwtUtil.generateToken("zhangsan", map));
	}
}