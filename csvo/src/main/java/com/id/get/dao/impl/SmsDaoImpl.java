
package com.id.get.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.id.get.dao.ISmsDao;
import com.id.get.expand.db.CustomIbatisDAO;
import com.id.get.model.SmsStatusInfo;

/**
 * 数据库操作类
 * @author Administrator
 *
 */
@Repository("smsDao")
public class SmsDaoImpl extends CustomIbatisDAO<SmsStatusInfo, Long> implements ISmsDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/****/
	public final static String SQL_QUERY_SMS_STATUS_DETAIL_BASE = "SELECT T.SYSCODE,T.SENDTIME,T.SPID,T.SERVERIP,T.MOBILE,T.TYPE,T.TOTALPART,T.SMSTYPE,T.SEQID,T.CONTENT,P.STATE,P.SPSENDTIME,P.UPDATETIME,P.SPMTNUMBER FROM((SELECT SYSCODE,SENDTIME,SPID,SERVERIP,MOBILE,TYPE,TOTALPART,1 AS SMSTYPE,SPMTID AS SEQID,SENDCONTENT AS CONTENT FROM SMS_DETAIL_INFO) UNION ALL (SELECT SYSCODE,SENDTIME,SPID,SERVERIP,MOBILE,2 AS TYPE,1 AS TOTALPART,2 AS SMSTYPE,SMSID AS SEQID,CHECKCODE AS CONTENT FROM SMS_VOICE_DETAIL_INFO)) T LEFT JOIN SMS_MT_REPORT P ON P.SPMTID=T.SEQID AND P.SPID=T.SPID ";
	/****/
	public final static String SQL_QUERY_SMS_STATUS_DETAIL_BASE_CONDITION = " WHERE T.SENDTIME>=? AND T.SENDTIME<=DATE_ADD(?, INTERVAL 1 DAY) ";
	/****/
	public final static String SQL_QUERY_SMS_STATUS_DETAIL_OTHER_CONDITION = " AND T.MOBILE=? ";
	/****/
	public final static String SQL_QUERY_SMS_STATUS_DETAIL_ORDER = " ORDER BY T.SENDTIME DESC LIMIT ?,? ";
	/****/
	public final static String SQL_QUERY_SMS_STATUS_DETAIL_COUNT_BASE = "SELECT COUNT(1) AS COUNT FROM((SELECT SYSCODE,SENDTIME,SPID,SERVERIP,MOBILE,TYPE,TOTALPART,1 AS SMSTYPE,SPMTID AS SEQID FROM SMS_DETAIL_INFO) UNION ALL (SELECT SYSCODE,SENDTIME,SPID,SERVERIP,MOBILE,1 AS TYPE,1 AS TOTALPART,2 AS SMSTYPE,SMSID AS SEQID FROM SMS_VOICE_DETAIL_INFO)) T LEFT JOIN SMS_MT_REPORT P ON P.SPMTID=T.SEQID AND P.SPID=T.SPID ";



	/* (non-Javadoc)
	 * @see com.uuzz.lottery.sms.dao.ISmsDao#querySmsStatusInfo(java.util.Map)
	 */
	public List<Map<String, Object>> querySmsStatusInfo(Map<String, Object> map)
			throws Exception {
		StringBuffer sql = new StringBuffer(SQL_QUERY_SMS_STATUS_DETAIL_BASE);
		sql.append(SQL_QUERY_SMS_STATUS_DETAIL_BASE_CONDITION);
		Object[] args = null;
		if(map.get("phone") != null) {
			sql.append(SQL_QUERY_SMS_STATUS_DETAIL_OTHER_CONDITION);
			
			args = new Object[]{
					map.get("start"),
					map.get("end"),
					map.get("phone"),
					map.get("startIndex"),
					map.get("pageSize")
			};
		}
		else {
			args = new Object[]{
					map.get("start"),
					map.get("end"),
					map.get("startIndex"),
					map.get("pageSize")
			};
		}
		
		sql.append(SQL_QUERY_SMS_STATUS_DETAIL_ORDER);
		return jdbcTemplate.queryForList(sql.toString(), args);
	}
	
	/* (non-Javadoc)
	 * @see com.uuzz.lottery.sms.dao.ISmsDao#querySmsStatusSize(java.util.Map)
	 */
	public long querySmsStatusCount(Map<String, Object> map) throws Exception {
		StringBuffer sql = new StringBuffer(SQL_QUERY_SMS_STATUS_DETAIL_COUNT_BASE);
		sql.append(SQL_QUERY_SMS_STATUS_DETAIL_BASE_CONDITION);
		
		Object[] args = null;
		if(map.get("phone") != null) {
			sql.append(SQL_QUERY_SMS_STATUS_DETAIL_OTHER_CONDITION);
			
			args = new Object[]{
					map.get("start"),
					map.get("end"),
					map.get("phone")
			};
		}
		else {
			args = new Object[]{
					map.get("start"),
					map.get("end")
			};
			
		}
		return jdbcTemplate.queryForLong(sql.toString(), args);
	}

	/* (non-Javadoc)
	 * @see com.id.get.dao.ISmsDao#querySmsStatusList(java.util.Map)
	 */
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<SmsStatusInfo> querySmsStatusList(Map<String, Object> map)
			throws Exception {
		return getSqlMapClientTemplate().queryForList("selectSmsStatusInfo", map);
	}
}
