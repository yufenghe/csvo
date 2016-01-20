package com.id.get.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.id.get.constant.Config;
import com.id.get.model.RedisServer;

/** 
 * <br>类 名: RedisClientController 
 * <br>描 述: redis操作类
 * <br>作 者: yufenghe 
 * <br>创 建： 2016年1月20日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
@Controller
@RequestMapping(value="/redis")
public class RedisClientController {

	@Resource
	private PropertiesConfiguration redisClientConfig;
	
	@RequestMapping(value="/show")
	public String showClientPage() {
		return "redis/dbView";
	}
	
	@RequestMapping(value="/server")
	@ResponseBody
	public List<String> showServer() {
		List<String> list = new ArrayList<String>();
		
		int maxId = redisClientConfig.getInt(Config.SERVER_MAX_ID);
		for(int i=1; i<=maxId; i++) {
			list.add(redisClientConfig.getString(Config.SERVER_HOST));
		}
		
		return list;
	}
	
	@RequestMapping(value="/client")
	@ResponseBody
	public List<RedisServer> showRedisClientConfig() {
		List<RedisServer> list = new ArrayList<RedisServer>();
		int maxId = redisClientConfig.getInt(Config.SERVER_MAX_ID);
		RedisServer server = null;
		for(int i=1; i<=maxId; i++) {
			server = new RedisServer();
			server.setId(i);
			server.setName(redisClientConfig.getString(Config.SERVER_NAME));
			server.setHost(redisClientConfig.getString(Config.SERVER_HOST));
			server.setPort(redisClientConfig.getString(Config.SERVER_PORT));
			server.setAuth(redisClientConfig.getString(Config.SERVER_AUTH));
			list.add(server);
		}
		
		return list;
	}
}
