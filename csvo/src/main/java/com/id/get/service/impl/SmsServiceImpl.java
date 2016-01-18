package com.id.get.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.id.get.dao.ISmsDao;
import com.id.get.model.Pager;
import com.id.get.model.SmsStatusInfo;
import com.id.get.service.ISmsService;

@Service("smsServiceImpl")
public class SmsServiceImpl implements ISmsService {

	@Autowired
	private ISmsDao smsDao;

	/* (non-Javadoc)
	 * @see com.uuzz.lottery.sms.service.ISmsService#querySmsStatusDetail(java.util.Map)
	 */
	public Pager<SmsStatusInfo> querySmsStatusDetail(Pager<SmsStatusInfo> pager, Map<String, Object> map)
			throws Exception {
		/** 查询短信数据记录 **/
		List<SmsStatusInfo> resultList = smsDao.querySmsStatusList(map);
		
		/** 查询短信数据总数 **/
		long totalCount = smsDao.querySmsStatusCount(map);
		
		/** 初始化分页信息 **/
		pager.init(resultList, totalCount);
		
		return pager;
	}
}
