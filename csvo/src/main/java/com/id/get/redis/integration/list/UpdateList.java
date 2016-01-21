package com.id.get.redis.integration.list;

import java.util.List;

import com.id.get.redis.integration.key.DeleteKey;
import com.id.get.redis.integration.key.Expire;
import com.id.get.redis.integration.key.TTLs;

public class UpdateList extends AddList {
	private int ttl;
	public UpdateList(int id, int db, String key, List<String> values,
			boolean headTail) {
		super(id, db, key, values, headTail, true);
	}

	@Override
	protected void beforeAdd() {
		TTLs command1 = new TTLs(id, db, key);
		command1.execute(jedis);
		ttl = (int) command1.getSecond();
		
		DeleteKey command = new DeleteKey(id, db, key);
		command.execute(jedis);
		
		
	}

	@Override
	protected void afterAdd() {
		Expire command2 = new Expire(id, db, key, ttl);
		command2.execute(jedis);
	}

}
