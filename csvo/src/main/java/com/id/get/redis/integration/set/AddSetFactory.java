package com.id.get.redis.integration.set;

import java.util.Set;

import com.id.get.redis.integration.JedisCommandFactory;

public class AddSetFactory extends JedisCommandFactory {

	public AddSetFactory(int id, int db, String key, Set<String> values) {
		super(id);
		commands.add(new AddSet10(id, db, key, values));
		commands.add(new AddSet24(id, db, key, values));
	}
	public AddSetFactory(int id, int db, String key, String[] values) {
		super(id);
		commands.add(new AddSet10(id, db, key, values));
		commands.add(new AddSet24(id, db, key, values));
	}

}
