package com.id.get.model;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.id.get.util.fmt.SimpleDateSerializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Test {
	@JsonProperty("server_area_code")
	private String serverAreaCode;
	
	@JsonProperty("multiple")
	private int multiple;
	
	@JsonProperty("bet_num")
	private long betNum;
	
	@JsonSerialize(using=SimpleDateSerializer.class,as=SimpleDateSerializer.class)
	@JsonProperty("date")
	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getServerAreaCode() {
		return serverAreaCode;
	}

	public int getMultiple() {
		return multiple;
	}

	public long getBetNum() {
		return betNum;
	}

	public void setServerAreaCode(String serverAreaCode) {
		this.serverAreaCode = serverAreaCode;
	}

	public void setMultiple(int multiple) {
		this.multiple = multiple;
	}

	public void setBetNum(long betNum) {
		this.betNum = betNum;
	}
	
	
}

