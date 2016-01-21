package com.id.get.redis.integration.key;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import com.id.get.redis.domain.Node;
import com.id.get.redis.domain.NodeType;
import com.id.get.redis.domain.RedisVersion;
import com.id.get.redis.integration.JedisCommand;

public class ListKeys extends JedisCommand {
	private int db;
	private Set<Node> nodes = new TreeSet<Node>();

	public Set<Node> getNodes() {
		return nodes;
	}

	public ListKeys(int id, int db) {
		super(id);
		this.db = db;
	}

	@Override
	public void command() {
		jedis.select(db);
		Set<String> nodekeys = jedis.keys("*");
		Iterator<String> it = nodekeys.iterator();
		while (it.hasNext()) {
			String key = (String)it.next();
			NodeType nodeType = getValueType(key);
			
			Node node = new Node(id, db, key, nodeType);
			nodes.add(node);
		}
	}

	@Override
	public RedisVersion getSupportVersion() {
		return RedisVersion.REDIS_1_0;
	}

	

}
