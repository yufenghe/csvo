package com.id.get.redis.integration.set;

import java.util.Set;

import com.id.get.redis.domain.RedisVersion;

public class RemoveSet10 extends RemoveSet {

	public RemoveSet10(int id, int db, String key, Set<String> values) {
		super(id, db, key, values);
	}

	@Override
	protected void command() {
		jedis.select(db);
		for(String value: values)
			jedis.srem(key, value);
	}

	@Override
	public RedisVersion getSupportVersion() {
		return RedisVersion.REDIS_1_0;
	}

}
