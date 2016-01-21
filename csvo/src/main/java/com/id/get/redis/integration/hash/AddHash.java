package com.id.get.redis.integration.hash;

import java.util.Map;

import com.id.get.redis.domain.NodeType;
import com.id.get.redis.domain.RedisVersion;
import com.id.get.redis.integration.I18nFile;
import com.id.get.redis.integration.JedisCommand;
import com.id.get.redis.presentation.RedisClient;

public class AddHash extends JedisCommand {
	protected int db;
	protected String key;
	protected Map<String, String> values;
	
	public AddHash(int id, int db, String key, Map<String, String> values) {
		super(id);
		this.db = db;
		this.key = key;
		this.values = values;
	}

	@Override
	protected void command() {
		jedis.select(db);
		if(jedis.exists(key) && getValueType(key) != NodeType.HASH)
			throw new RuntimeException(RedisClient.i18nFile.getText(I18nFile.HASHEXIST)+ key);
		jedis.hmset(key, values);
	}

	@Override
	public RedisVersion getSupportVersion() {
		return RedisVersion.REDIS_2_0;
	}

}
