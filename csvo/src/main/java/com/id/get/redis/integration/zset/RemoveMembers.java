package com.id.get.redis.integration.zset;

import com.id.get.redis.domain.RedisVersion;
import com.id.get.redis.integration.JedisCommand;

public class RemoveMembers extends JedisCommand {
	private int db;
	private String key;
	private String[] members;
	
	public RemoveMembers(int id, int db, String key, String[] members) {
		super(id);
		this.db = db;
		this.key = key;
		this.members = members;
	}

	@Override
	protected void command() {
		jedis.select(db);
		jedis.zrem(key, members);
		
	}

	@Override
	public RedisVersion getSupportVersion() {
		return RedisVersion.REDIS_1_2;
	}

}
