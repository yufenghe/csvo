package com.id.get.redis.integration.set;

import java.util.List;

import redis.clients.jedis.SortingParams;

import com.id.get.redis.domain.RedisVersion;
import com.id.get.redis.integration.ListPage;

public class ListSetPage extends ListPage {
	private List<String> page;
	
	public List<String> getPage() {
		return page;
	}
	public ListSetPage(int id, int db, String key, int start, int end) {
		super(id, db, key, start, end);
	}

	@Override
	protected void command() {
		jedis.select(db);
		SortingParams sp = new SortingParams();
		sp.alpha();
		sp.limit(start, end-start);
		page = jedis.sort(key, sp);
		
	}

	@Override
	public RedisVersion getSupportVersion() {
		return RedisVersion.REDIS_1_0;
	}
}
