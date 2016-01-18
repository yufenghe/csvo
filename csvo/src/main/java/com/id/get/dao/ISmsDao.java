package com.id.get.dao;

import java.util.List;
import java.util.Map;

import com.id.get.model.SmsStatusInfo;

public interface ISmsDao {
	
	/**
	 * 
	 * <br>描 述：查询分页状态数据
	 * <br>作 者：yufenghe 
	 * <br>历 史: (版本) 作者 时间 注释
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> querySmsStatusInfo(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	 * <br>描 述：查询短信状态报告数目
	 * <br>作 者：yufenghe 
	 * <br>历 史: (版本) 作者 时间 注释
	 * @return long
	 * @throws Exception
	 */
	public long querySmsStatusCount(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	 * <br>描 述：ibatis查询短信状态报告数目
	 * <br>作 者：yufenghe 
	 * <br>历 史: (版本) 作者 时间 注释
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<SmsStatusInfo> querySmsStatusList(Map<String, Object> map) throws Exception;
}
