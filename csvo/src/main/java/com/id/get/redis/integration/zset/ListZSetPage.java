package com.id.get.redis.integration.zset;

import java.util.Set;

import redis.clients.jedis.Tuple;

import com.id.get.redis.domain.RedisVersion;
import com.id.get.redis.integration.ListPage;

public class ListZSetPage extends ListPage {
	private Set<Tuple> page;
	
	public ListZSetPage(int id, int db, String key, int start, int end) {
		super(id, db, key, start, end);
	}

	@Override
	protected void command() {
		jedis.select(db);
		page = jedis.zrangeWithScores(key, start, end);
	}

	@Override
	public RedisVersion getSupportVersion() {
		return RedisVersion.REDIS_1_2;
	}

	public Set<Tuple> getPage() {
		return page;
	}

}
