package com.id.get.redis.integration;

import java.util.SortedSet;
import java.util.TreeSet;

import com.id.get.redis.domain.RedisVersion;
import com.id.get.redis.integration.server.QueryServerVersion;
import com.id.get.redis.presentation.RedisClient;

public abstract class JedisCommandFactory {
	private int id;
	protected SortedSet<JedisCommand> commands = new TreeSet<JedisCommand>();
	
	public JedisCommandFactory(int id) {
		this.id = id;
	}
	
	public JedisCommand getCommand() {
		QueryServerVersion queryVersion = new QueryServerVersion(id);
		queryVersion.execute();
		RedisVersion version = queryVersion.getVersionInfo();
		
		for (JedisCommand command: commands) {
			if (command.getSupportVersion().getVersion() <= version.getVersion()) {
				return command;
			}
		}
		throw new RuntimeException(RedisClient.i18nFile.getText(I18nFile.VERSIONNOTSUPPORT));
	}
}
