package com.id.get.redis.integration.key;

import com.id.get.redis.domain.RedisVersion;
import com.id.get.redis.integration.JedisCommand;

public class RestoreKey extends JedisCommand {
	private int db;
	private String key;
	private byte[] value;
	
	public RestoreKey(int id, int db, String key, byte[] value) {
		super(id);
		this.db = db;
		this.key = key;
		this.value = value;
	}

	@Override
	protected void command() {
		jedis.select(db);
		jedis.restore(key, 0, value);
	}

	@Override
	public RedisVersion getSupportVersion() {
		return RedisVersion.REDIS_2_6;
	}

}
