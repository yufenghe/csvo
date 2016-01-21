package com.id.get.redis.integration.key;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import com.id.get.redis.domain.DataNode;
import com.id.get.redis.domain.NodeType;
import com.id.get.redis.domain.RedisVersion;
import com.id.get.redis.dto.Order;
import com.id.get.redis.dto.OrderBy;
import com.id.get.redis.integration.ConfigFile;
import com.id.get.redis.integration.JedisCommand;

public class ListContainerKeys extends JedisCommand {
	private int db;
	private String key;
	private Set<DataNode> keys = new TreeSet<DataNode>();
	private Order order;
	private OrderBy orderBy;
	private boolean flat;
	
	public Set<DataNode> getKeys() {
		return keys;
	}

	public ListContainerKeys(int id, int db, String key, boolean flat, Order order, OrderBy orderBy) {
		super(id);
		this.db = db;
		this.key = key;
		this.order = order;
		this.flat = flat;
		this.orderBy = orderBy;
	}
	
	public ListContainerKeys(int id, int db, String key, boolean flat, Order order) {
		super(id);
		this.db = db;
		this.key = key;
		this.order = order;
		this.flat = flat;
		this.orderBy = OrderBy.NAME;
	}
	
	public ListContainerKeys(int id, int db, String key, boolean flat) {
		super(id);
		this.db = db;
		this.key = key;
		this.flat = flat;
		this.order = Order.Ascend;
		this.orderBy = OrderBy.NAME;
	}

	@Override
	public void command() {
		jedis.select(db);
		Set<String> nodekeys = null;
		int length;
		if (key != null) {
			nodekeys = jedis.keys(key + "*");
			length = key.length();
		} else {
			nodekeys = jedis.keys("*");
			length = 0;
		}

		Iterator<String> it = nodekeys.iterator();
		while (it.hasNext()) {
			String nextKey = it.next();
			String[] ckey = nextKey.substring(length).split(ConfigFile.getSeparator());
			if (ckey.length == 1) {
				NodeType nodeType = getValueType(nextKey);
				long size = getSize(nextKey);
				boolean persist = isPersist(nextKey);
				DataNode node;
				if(!flat)
					node = new DataNode(id, db, ckey[0], nodeType, size, persist, order, orderBy);
				else
					node = new DataNode(id, db, nextKey, nodeType, size, persist, order, orderBy);
				keys.add(node);
			}
		}
	}

	@Override
	public RedisVersion getSupportVersion() {
		return RedisVersion.REDIS_1_0;
	}

}
