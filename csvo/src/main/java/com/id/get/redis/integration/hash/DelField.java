package com.id.get.redis.integration.hash;

import com.id.get.redis.domain.RedisVersion;
import com.id.get.redis.integration.JedisCommand;

public class DelField extends JedisCommand {
	private int db;
	private String key;
	private String[] fields;
	public DelField(int id, int db, String key, String[] fields) {
		super(id);
		this.db = db;
		this.key = key;
		this.fields = fields;
	}
	@Override
	protected void command() {
		jedis.select(db);
		jedis.hdel(key, fields);
		
	}
	@Override
	public RedisVersion getSupportVersion() {
		return RedisVersion.REDIS_2_0;
	}

}
