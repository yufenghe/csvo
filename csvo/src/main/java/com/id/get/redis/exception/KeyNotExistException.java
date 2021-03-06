package com.id.get.redis.exception;

import com.id.get.redis.integration.I18nFile;
import com.id.get.redis.presentation.RedisClient;

public class KeyNotExistException extends RuntimeException {
	private static final long serialVersionUID = 958469726423878744L;
	protected int id;
	protected int db;
	private String key;
	
	public KeyNotExistException(int id, int db, String key){
		this.id = id;
		this.db = db;
		this.key = key;
	}

	@Override
	public String getMessage() {
		return RedisClient.i18nFile.getText(I18nFile.KEYNOTEXIST)+": "+key;
	}
}
