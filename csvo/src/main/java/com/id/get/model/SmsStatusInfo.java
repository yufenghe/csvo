package com.id.get.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.id.get.util.fmt.TimestampSerializer;

/** 
 * <br>类 名: SmsStatusInfo 
 * <br>描 述: 短信发送状态信息
 * <br>作 者: yufenghe 
 * <br>创 建： 2016年1月6日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class SmsStatusInfo {
	/** 查询表的主键 **/
//	private String id;
	
	/** 业务系统代码 **/
	private String sysCode;
	
	/** 短信发送请求时间 **/
	@JsonSerialize(using=TimestampSerializer.class)
//	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date sendTime;
	
	/** 服务提供商代码 **/
	private String spId;
	
	/** 服务器ip **/
	private String serverIp;
	
	/** 手机号 **/
	private String mobile;
	
	/** 发送类型，1:用户主动获取 2：系统推送 **/
	private String type;
	
	/** 当前短信条数 **/
//	private String totalPart;
	
	/** 运营商发送时间 **/
//	@JsonSerialize(using=TimestampSerializer.class)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date spSendTime;
	
	/** 发送状态 **/
	private String state;
	
	/** 短信下行特服号 **/
//	private String spmtNumber;
	
	/** 短信类型（1：普通短信;2：语音验证码） **/
	private String smsType;
	
	/** 序列码 **/
	private String seqId;
	
	/**发送状态报告时间**/
//	@JsonSerialize(using=TimestampSerializer.class)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date updateTime;
	
	/**内容**/
	private String content;

//	public String getId() {
//		return id;
//	}

	public String getSysCode() {
		return sysCode;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public String getSpId() {
		return spId;
	}

	public String getServerIp() {
		return serverIp;
	}

	public String getMobile() {
		return mobile;
	}

	public String getType() {
		return type;
	}

//	public String getTotalPart() {
//		return totalPart;
//	}

	public Date getSpSendTime() {
		return spSendTime;
	}

	public String getState() {
		return state;
	}

//	public String getSpmtNumber() {
//		return spmtNumber;
//	}

//	public void setId(String id) {
//		this.id = id;
//	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public void setSpId(String spId) {
		this.spId = spId;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setType(String type) {
		this.type = type;
	}

//	public void setTotalPart(String totalPart) {
//		this.totalPart = totalPart;
//	}

	public void setSpSendTime(Date spSendTime) {
		this.spSendTime = spSendTime;
	}

	public void setState(String state) {
		this.state = state;
	}

//	public void setSpmtNumber(String spmtNumber) {
//		this.spmtNumber = spmtNumber;
//	}

	public String getSmsType() {
		return smsType;
	}

	public String getSeqId() {
		return seqId;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
