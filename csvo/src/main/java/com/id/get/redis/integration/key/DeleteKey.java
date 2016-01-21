package com.id.get.redis.integration.key;

import com.id.get.redis.domain.RedisVersion;
import com.id.get.redis.integration.JedisCommand;


public class DeleteKey extends JedisCommand {
	private int db;
	private String key;
	
	public DeleteKey(int id, int db, String key) {
		super(id);
		this.db = db;
		this.key = key;
	}

	@Override
	public void command() {
		jedis.select(db);
		jedis.del(key);
	}

	@Override
	public RedisVersion getSupportVersion() {
		return RedisVersion.REDIS_1_0;
	}

}
