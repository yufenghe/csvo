package com.id.get.redis.integration.zset;

import java.util.Map;

import com.id.get.redis.domain.NodeType;
import com.id.get.redis.integration.I18nFile;
import com.id.get.redis.integration.JedisCommand;
import com.id.get.redis.presentation.RedisClient;

public abstract class AddZSet extends JedisCommand {
	protected int db;
	protected String key;
	protected Map<String, Double> values;
	
	public AddZSet(int id, int db, String key, Map<String, Double> values) {
		super(id);
		this.db = db;
		this.key = key;
		this.values = values;
	}

	@Override
	protected void command() {
		jedis.select(db);
		if(jedis.exists(key) && getValueType(key) != NodeType.SORTEDSET)
			throw new RuntimeException(RedisClient.i18nFile.getText(I18nFile.ZSETEXIST)+key);
		addZSet();
			
		
	}
	
	protected abstract void addZSet();

}
