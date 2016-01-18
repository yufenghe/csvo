package com.id.get.service;

import java.util.Map;

import com.id.get.model.Pager;
import com.id.get.model.SmsStatusInfo;

public interface ISmsService {
	
	/**
	 * 
	 * <br>描 述：查询短信状态报告信息
	 * <br>作 者：yufenghe 
	 * <br>历 史: (版本) 作者 时间 注释
	 * @param map
	 * @return Pager<SmsStatusInfo>
	 * @throws Exception
	 */
	public Pager<SmsStatusInfo> querySmsStatusDetail(Pager<SmsStatusInfo> pager, Map<String, Object> map) throws Exception;

}
