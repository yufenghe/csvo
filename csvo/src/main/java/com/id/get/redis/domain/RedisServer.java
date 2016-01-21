package com.id.get.redis.domain;

/**
 * 
 * <br>类 名: RedisServer 
 * <br>描 述: redis服务器属性实体
 * <br>作 者: yufenghe 
 * <br>创 建： 2016年1月20日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class RedisServer {
	/**
	 * 标识号
	 */
	private int id;
	
	/**
	 * 名称（别名）
	 */
	private String name;
	
	/**
	 * 地址
	 */
	private String host;
	
	/**
	 * 端口号
	 */
	private String port;
	
	/**
	 * 授权
	 */
	private String auth;
	
	public RedisServer() {
		
	}
	
	public RedisServer(int id, String name, String addr, String port, String auth) {
		super();
		this.id = id;
		this.name = name;
		this.host = addr;
		this.port = port;
		this.auth = auth;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}
	
}
