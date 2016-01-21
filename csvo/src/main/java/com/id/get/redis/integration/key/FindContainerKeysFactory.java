package com.id.get.redis.integration.key;

import java.util.List;

import com.id.get.redis.domain.NodeType;
import com.id.get.redis.integration.JedisCommandFactory;

public class FindContainerKeysFactory extends JedisCommandFactory {

	public FindContainerKeysFactory(int id, int db, String container, String keyPattern) {
		super(id);
		commands.add(new FindContainerKeys28(id, db, container, keyPattern));
		commands.add(new FindContainerKeys10(id, db, container, keyPattern));
	}
	
	public FindContainerKeysFactory(int id, int db, String container, List<NodeType> valueTypes, String keyPattern, boolean forward) {
		super(id);
		commands.add(new FindContainerKeys28(id, db, container, keyPattern, valueTypes, forward));
		commands.add(new FindContainerKeys10(id, db, container, keyPattern, valueTypes, forward));
	}

	public FindContainerKeys getListContainerAllKeys(){
		return (FindContainerKeys) getCommand();
	}
}
