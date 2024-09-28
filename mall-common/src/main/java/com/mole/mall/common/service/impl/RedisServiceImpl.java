package com.mole.mall.common.service.impl;

import com.mole.mall.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis操作实现类
 * Created by macro on 2020/3/3.
 * Modified by Cyanix-0721 on 2024/9/28.
 */
@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public void set(String key, Object value, long time) {
		// 设置带有过期时间的键值对
		redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
	}

	@Override
	public void set(String key, Object value) {
		// 设置键值对
		redisTemplate.opsForValue().set(key, value);
	}

	@Override
	public Object get(String key) {
		// 获取键对应的值
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public Boolean del(String key) {
		// 删除键
		return redisTemplate.delete(key);
	}

	@Override
	public Long del(List<String> keys) {
		// 批量删除键
		return redisTemplate.delete(keys);
	}

	@Override
	public Boolean expire(String key, long time) {
		// 设置键的过期时间
		return redisTemplate.expire(key, time, TimeUnit.SECONDS);
	}

	@Override
	public Long getExpire(String key) {
		// 获取键的过期时间
		return redisTemplate.getExpire(key, TimeUnit.SECONDS);
	}

	@Override
	public Boolean hasKey(String key) {
		// 判断键是否存在
		return redisTemplate.hasKey(key);
	}

	@Override
	public Long incr(String key, long delta) {
		// 增加键的值
		return redisTemplate.opsForValue().increment(key, delta);
	}

	@Override
	public Long decr(String key, long delta) {
		// 减少键的值
		return redisTemplate.opsForValue().increment(key, - delta);
	}

	@Override
	public Object hGet(String key, String hashKey) {
		// 获取哈希键对应的值
		return redisTemplate.opsForHash().get(key, hashKey);
	}

	@Override
	public Boolean hSet(String key, String hashKey, Object value, long time) {
		// 设置哈希键值对并设置过期时间
		redisTemplate.opsForHash().put(key, hashKey, value);
		return expire(key, time);
	}

	@Override
	public void hSet(String key, String hashKey, Object value) {
		// 设置哈希键值对
		redisTemplate.opsForHash().put(key, hashKey, value);
	}

	@Override
	public Map<Object, Object> hGetAll(String key) {
		// 获取哈希表中所有键值对
		return redisTemplate.opsForHash().entries(key);
	}

	@Override
	public Boolean hSetAll(String key, Map<String, Object> map, long time) {
		// 批量设置哈希键值对并设置过期时间
		redisTemplate.opsForHash().putAll(key, map);
		return expire(key, time);
	}

	@Override
	public void hSetAll(String key, Map<String, ?> map) {
		// 批量设置哈希键值对
		redisTemplate.opsForHash().putAll(key, map);
	}

	@Override
	public void hDel(String key, Object... hashKey) {
		// 删除哈希键
		redisTemplate.opsForHash().delete(key, hashKey);
	}

	@Override
	public Boolean hHasKey(String key, String hashKey) {
		// 判断哈希键是否存在
		return redisTemplate.opsForHash().hasKey(key, hashKey);
	}

	@Override
	public Long hIncr(String key, String hashKey, Long delta) {
		// 增加哈希键的值
		return redisTemplate.opsForHash().increment(key, hashKey, delta);
	}

	@Override
	public Long hDecr(String key, String hashKey, Long delta) {
		// 减少哈希键的值
		return redisTemplate.opsForHash().increment(key, hashKey, - delta);
	}

	@Override
	public Set<Object> sMembers(String key) {
		// 获取集合中的所有成员
		return redisTemplate.opsForSet().members(key);
	}

	@Override
	public Long sAdd(String key, Object... values) {
		// 向集合中添加成员
		return redisTemplate.opsForSet().add(key, values);
	}

	@Override
	public Long sAdd(String key, long time, Object... values) {
		// 向集合中添加成员并设置过期时间
		Long count = redisTemplate.opsForSet().add(key, values);
		expire(key, time);
		return count;
	}

	@Override
	public Boolean sIsMember(String key, Object value) {
		// 判断值是否是集合中的成员
		return redisTemplate.opsForSet().isMember(key, value);
	}

	@Override
	public Long sSize(String key) {
		// 获取集合的大小
		return redisTemplate.opsForSet().size(key);
	}

	@Override
	public Long sRemove(String key, Object... values) {
		// 移除集合中的成员
		return redisTemplate.opsForSet().remove(key, values);
	}

	@Override
	public List<Object> lRange(String key, long start, long end) {
		// 获取列表中的元素
		return redisTemplate.opsForList().range(key, start, end);
	}

	@Override
	public Long lSize(String key) {
		// 获取列表的长度
		return redisTemplate.opsForList().size(key);
	}

	@Override
	public Object lIndex(String key, long index) {
		// 获取列表中指定索引的元素
		return redisTemplate.opsForList().index(key, index);
	}

	@Override
	public Long lPush(String key, Object value) {
		// 向列表中添加元素
		return redisTemplate.opsForList().rightPush(key, value);
	}

	@Override
	public Long lPush(String key, Object value, long time) {
		// 向列表中添加元素并设置过期时间
		Long index = redisTemplate.opsForList().rightPush(key, value);
		expire(key, time);
		return index;
	}

	@Override
	public Long lPushAll(String key, Object... values) {
		// 批量向列表中添加元素
		return redisTemplate.opsForList().rightPushAll(key, values);
	}

	@Override
	public Long lPushAll(String key, Long time, Object... values) {
		// 批量向列表中添加元素并设置过期时间
		Long count = redisTemplate.opsForList().rightPushAll(key, values);
		expire(key, time);
		return count;
	}

	@Override
	public Long lRemove(String key, long count, Object value) {
		// 移除列表中的元素
		return redisTemplate.opsForList().remove(key, count, value);
	}
}