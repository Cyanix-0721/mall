package com.mole.gateway.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class RedisTest {
	public static void main(String[] args) {
		// 创建RedisClient对象，连接指定的Redis服务器
		RedisClient redisClient = RedisClient.create("redis://default:114514@localhost:6379");

		// 创建连接
		StatefulRedisConnection<String, String> connection = redisClient.connect();

		// 获取同步命令
		RedisCommands<String, String> syncCommands = connection.sync();

		// 测试连接
		String pingResult = syncCommands.ping();
		if ("PONG".equals(pingResult)) {
			System.out.println("Redis连接成功");
		} else {
			System.out.println("Redis连接失败");
		}

		// 写入数据到Redis
		syncCommands.set("testKey", "testValue");

		// 从Redis读取数据
		String value = syncCommands.get("testKey");

		// 验证读取的数据
		if ("testValue".equals(value)) {
			System.out.println("读写测试成功");
		} else {
			System.out.println("读写测试失败");
		}

		// 关闭连接
		connection.close();
		redisClient.shutdown();
	}
}