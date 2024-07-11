package com.mole.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 这个类是Redis的配置类。
 * 它定义了创建LettuceConnectionFactory和RedisTemplate实例的bean，这些实例具有特定的配置。
 */
@Configuration
public class RedisConfig {

	// 从应用程序属性中获取的Redis主机值
	@Value("${spring.redis.host}")
	private String redisHost;

	// 从应用程序属性中获取的Redis端口值
	@Value("${spring.redis.port}")
	private int redisPort;

	// 从应用程序属性中获取的Redis密码值
	@Value("${spring.redis.password}")
	private String redisPassword;

	/**
	 * 这个方法创建了一个具有特定配置的LettuceConnectionFactory实例。
	 * 配置包括Redis主机和端口。
	 *
	 * @return 具有特定配置的LettuceConnectionFactory实例
	 */
	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(redisHost, redisPort);
		config.setPassword(redisPassword);
		return new LettuceConnectionFactory(config);
	}

	/**
	 * 这个方法创建了一个具有特定配置的RedisTemplate实例。
	 * 配置包括连接工厂，键序列化器和值序列化器。
	 *
	 * @return 具有特定配置的RedisTemplate实例
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		return template;
	}

	/**
	 * 这个方法创建了一个具有特定配置的StringRedisTemplate实例。
	 * 配置包括连接工厂，键序列化器和值序列化器。
	 *
	 * @return 具有特定配置的StringRedisTemplate实例
	 */
	@Bean
	public StringRedisTemplate stringRedisTemplate() {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(redisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		return template;
	}
}