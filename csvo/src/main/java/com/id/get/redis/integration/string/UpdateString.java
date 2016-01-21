package com.id.get.redis.integration.string;

import com.id.get.redis.domain.RedisVersion;
import com.id.get.redis.integration.JedisCommand;
import com.id.get.redis.integration.key.Expire;
import com.id.get.redis.integration.key.TTLs;

public class UpdateString extends JedisCommand {
	private int db;
	private String key;
	private String value;
	
	public UpdateString(int id, int db, String key, String value) {
		super(id);
		this.db = db;
		this.key = key;
		this.value = value;
	}

	@Override
	protected void command() {
		TTLs command1 = new TTLs(id, db, key);
		command1.execute(jedis);
		int ttl = (int) command1.getSecond();
		
		jedis.select(db);
		jedis.set(key, value);
		
		Expire command2 = new Expire(id, db, key, ttl);
		command2.execute(jedis);
	}

	@Override
	public RedisVersion getSupportVersion() {
		return RedisVersion.REDIS_1_0;
	}

}
