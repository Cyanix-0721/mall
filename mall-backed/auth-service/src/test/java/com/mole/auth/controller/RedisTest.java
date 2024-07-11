package com.mole.auth.controller;

import com.mole.common.config.RedisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = RedisConfig.class)
public class RedisTest {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void testRedisConnection() {
		// 写入数据到Redis
		redisTemplate.opsForValue().set("testKey", "testValue");

		// 从Redis读取数据
		Object value = redisTemplate.opsForValue().get("testKey");

		// 验证读取的数据
		assertEquals("testValue", value);
	}

	@Test
	public void testTokenStorage() {
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();

		// 模拟用户名和访问令牌
		String username = "testUser";
		String accessToken = "testAccessToken";
		String refreshToken = "testRefreshToken";

		// 模拟令牌的过期时间
		long ttl = 60 * 60 * 1000; // 1 hour in milliseconds

		// 将访问令牌存储到Redis
		ops.set(username + ":access", accessToken, ttl, TimeUnit.MILLISECONDS);

		// 将刷新令牌存储到Redis
		ops.set(username + ":refresh", refreshToken, ttl, TimeUnit.MILLISECONDS);

		// 从Redis读取访问令牌
		String valueAccess = ops.get(username + ":access");
		String valueRefresh = ops.get(username + ":refresh");

		// 验证读取的访问令牌
		assertEquals(accessToken, valueAccess);

		// 验证读取的刷新令牌
		assertEquals(refreshToken, valueRefresh);
	}
}
