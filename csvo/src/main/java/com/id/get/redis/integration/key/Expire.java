package com.id.get.redis.integration.key;

import com.id.get.redis.domain.RedisVersion;
import com.id.get.redis.integration.JedisCommand;

public class Expire extends JedisCommand {
	private int db;
	private String key;
	private int second;
	
	public Expire(int id, int db, String key, int l) {
		super(id);
		this.db = db;
		this.key = key;
		this.second = l;
	}

	@Override
	protected void command() {
		jedis.select(db);
		if(second != -1)
			jedis.expire(key, second);
		else
			jedis.persist(key);
		
	}

	@Override
	public RedisVersion getSupportVersion() {
		if(second != -1)
			return RedisVersion.REDIS_1_0;
		else
			return RedisVersion.REDIS_2_2;
	}

}
