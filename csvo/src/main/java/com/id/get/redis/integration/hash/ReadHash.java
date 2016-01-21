package com.id.get.redis.integration.hash;

import java.util.Map;

import com.id.get.redis.domain.RedisVersion;
import com.id.get.redis.integration.JedisCommand;

public class ReadHash extends JedisCommand {
	private int db;
	private String key;
	private Map<String, String> value;
	
	public ReadHash(int id, int db, String key) {
		super(id);
		this.db = db;
		this.key = key;
	}

	@Override
	protected void command() {
		jedis.select(db);
		value = jedis.hgetAll(key);
		
	}

	public Map<String, String> getValue() {
		return value;
	}

	@Override
	public RedisVersion getSupportVersion() {
		return RedisVersion.REDIS_2_0;
	}

}
