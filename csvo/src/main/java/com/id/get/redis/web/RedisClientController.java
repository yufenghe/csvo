package com.id.get.redis.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.id.get.model.ModelView;
import com.id.get.redis.domain.DBNode;
import com.id.get.redis.domain.Node;
import com.id.get.redis.domain.RedisServer;
import com.id.get.redis.service.NodeService;
import com.id.get.redis.service.ServerService;

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

	@Autowired
	private ServerService serverService;
	@Autowired
	private NodeService	nodeService;
	
	@RequestMapping(value="/show")
	public String showClientPage() {
		return "redis/dbView";
	}
	
	@RequestMapping(value="/server", method=RequestMethod.POST)
	@ResponseBody
	public List<String> showServer() {
		return serverService.getServers();
	}
	
	@RequestMapping(value="/serverInfo", method=RequestMethod.POST)
	@ResponseBody
	public List<RedisServer> showRedisClientConfig() {
		return serverService.getServerInfos();
	}
	
	@RequestMapping(value="/showdb")
	@ResponseBody
	public List<DBNode> showDb(int id) {
		return serverService.showDbs(id);
	}
	
	@RequestMapping(value="/showkeys", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<Node> queryDbData(@RequestParam(required=false)String id, @RequestParam(required=false)String db) {
//		ModelView<Node> mv = new ModelView<Node>();
//		if(StringUtils.isBlank(id) || StringUtils.isBlank(db)) {
//			return mv;
//		}
		
		Set<Node> set = nodeService.listContainers(Integer.parseInt(id), Integer.parseInt(db), "", false);
//		mv.setTotal(set.size());
		List<Node> list = new ArrayList<Node>();
		list.addAll(set);
//		mv.setRows(list);
		return list;
	}
}
