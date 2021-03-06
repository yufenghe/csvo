package com.id.get.redis.integration.zset;

import java.util.Set;

import redis.clients.jedis.Tuple;

import com.id.get.redis.domain.RedisVersion;
import com.id.get.redis.integration.JedisCommand;

public class ListZSet extends JedisCommand {
	public Set<Tuple> getValues() {
		return values;
	}

	private int db;
	private String key;
	private Set<Tuple> values;
	
	public ListZSet(int id, int db, String key) {
		super(id);
		this.db = db;
		this.key = key;
	}

	@Override
	protected void command() {
		jedis.select(db);
		values = jedis.zrangeWithScores(key, 0, -1);
	}

	@Override
	public RedisVersion getSupportVersion() {
		return RedisVersion.REDIS_1_2;
	}

}
