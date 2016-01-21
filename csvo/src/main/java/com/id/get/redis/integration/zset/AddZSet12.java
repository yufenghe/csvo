package com.id.get.redis.integration.zset;

import java.util.Map;

import com.id.get.redis.domain.RedisVersion;

public class AddZSet12 extends AddZSet {

	public AddZSet12(int id, int db, String key, Map<String, Double> values) {
		super(id, db, key, values);
	}

	@Override
	public RedisVersion getSupportVersion() {
		return RedisVersion.REDIS_1_2;
	}

	protected void addZSet() {
		for(Map.Entry<String, Double> value: values.entrySet())
			jedis.zadd(key, value.getValue(), value.getKey());
	}

}
