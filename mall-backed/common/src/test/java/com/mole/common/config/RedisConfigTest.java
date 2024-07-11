package com.mole.common.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = RedisConfig.class)
public class RedisConfigTest {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Test
	public void testRedisConnection() {
		// 写入数据到Redis
		redisTemplate.opsForValue().set("testKey", "testValue");

		// 从Redis读取数据
		Object value = redisTemplate.opsForValue().get("testKey");

		// 验证读取的数据
		assertEquals("testValue", value);
	}
}
