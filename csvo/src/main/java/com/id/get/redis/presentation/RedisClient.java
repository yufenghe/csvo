package com.id.get.redis.presentation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.id.get.redis.domain.Language;
import com.id.get.redis.integration.ConfigFile;
import com.id.get.redis.integration.I18nFile;

public class RedisClient {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(RedisClient.class);

	public static Language language = ConfigFile.getLanguage();
	public static final I18nFile i18nFile = new I18nFile();

	private static final String DB_PREFIX = "db";
	private static final String NODE_TYPE = "type";
	private static final String NODE_ID = "id";
	private static final String ITEM_OPENED = "open";
	private static final String FAVORITE = "favorite";

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		RedisClient window = new RedisClient();
	}

	
}
