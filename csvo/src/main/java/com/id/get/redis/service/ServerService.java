package com.id.get.redis.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.id.get.constant.Config;
import com.id.get.redis.domain.DBNode;
import com.id.get.redis.domain.Node;
import com.id.get.redis.domain.RedisServer;
import com.id.get.redis.integration.ConfigFile;
import com.id.get.redis.integration.server.QueryDBAmount;
import com.id.get.redis.integration.server.QueryServerProperties;

@Service("serverService")
public class ServerService {
	private static Logger logger = LoggerFactory.getLogger(ServerService.class);
	
	@Resource
	private PropertiesConfiguration redisClientConfig;
	
	public List<String> getServers() {
		List<String> list = new ArrayList<String>();
		
		int maxId = redisClientConfig.getInt(Config.SERVER_MAX_ID);
		for(int i=1; i<=maxId; i++) {
			list.add(redisClientConfig.getString(Config.SERVER_HOST + i));
		}
		
		return list;
	}
	
	public List<RedisServer> getServerInfos() {
		List<RedisServer> list = new ArrayList<RedisServer>();
		int maxId = redisClientConfig.getInt(Config.SERVER_MAX_ID);
		RedisServer server = null;
		for(int i=1; i<=maxId; i++) {
			server = new RedisServer();
			server.setId(i);
			server.setName(redisClientConfig.getString(Config.SERVER_NAME + i));
			server.setHost(redisClientConfig.getString(Config.SERVER_HOST + i));
			server.setPort(redisClientConfig.getString(Config.SERVER_PORT + i));
			server.setAuth(redisClientConfig.getString(Config.SERVER_AUTH + i));
			list.add(server);
		}
		
		return list;
	}
	
	public RedisServer getById(int id) {
		try {
			RedisServer server = null;
			if (ConfigFile.read(ConfigFile.NAME + id) != null)
				server = new RedisServer(id, ConfigFile.read(ConfigFile.NAME + id),
						ConfigFile.read(ConfigFile.HOST + id),
						ConfigFile.read(ConfigFile.PORT + id),
						ConfigFile.read(ConfigFile.PASSWORD + id));

			return server;
		} catch (IOException e) {
			logger.error("根据ID获取redisserver异常", e);
			throw new RuntimeException(e.getMessage());
		}
	}

	public int addServer(String name, String host, String port, String password) {
		try {
			int id = redisClientConfig.getInt(Config.SERVER_MAX_ID) + 1;
			redisClientConfig.addProperty(Config.SERVER_NAME + id, name);
			redisClientConfig.addProperty(Config.SERVER_HOST + id, host);
			redisClientConfig.addProperty(Config.SERVER_PORT + id, port);
			redisClientConfig.addProperty(Config.SERVER_AUTH + id, password);
			redisClientConfig.addProperty(Config.SERVER_MAX_ID, id);

			redisClientConfig.save();
			return id;
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	public void delete(int id) {
		try {
			ConfigFile.delete(ConfigFile.NAME + id);
			ConfigFile.delete(ConfigFile.HOST + id);
			ConfigFile.delete(ConfigFile.PORT + id);
			ConfigFile.delete(ConfigFile.PASSWORD + id);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void update(int id, String name) {
		try {
			ConfigFile.write(ConfigFile.NAME + id, name);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void update(int id, String host, String port, String password) {
		try {
			ConfigFile.write(ConfigFile.HOST + id, host);
			ConfigFile.write(ConfigFile.PORT + id, port);
			ConfigFile.write(ConfigFile.PASSWORD + id, password);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void update(int id, String name, String host, String port, String password) {
		update(id, name);
		update(id, host, port, password);
	}

	public int listDBs(int id) {
		QueryDBAmount command = new QueryDBAmount(id);
		command.execute();
		return command.getDbAmount();
	}

	public int listDBs(RedisServer server) throws IOException {
		return listDBs(server.getId());
	}
	
	public Map<String, String[]> listInfo(int id) {
		QueryServerProperties command = new QueryServerProperties(id);
		command.execute();
		return command.getServerInfo();
	}
	
	public List<DBNode> showDbs(int id) {
		List<DBNode> nodeList = new ArrayList<DBNode>();
		int amount = this.listDBs(id);
		DBNode node = null;
		for(int i=0; i<amount; i++) {
			node = new DBNode();
			node.setId(i);
			node.setName("DB" + i);
			nodeList.add(node);
		}
				
		return nodeList;
	}
}
