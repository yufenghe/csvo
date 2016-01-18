package com.id.get.expand.db;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import com.ibatis.sqlmap.client.SqlMapClient;

@SuppressWarnings("deprecation")
@Component
public abstract class CustomIbatisDAO<E, PK extends Serializable> 
						extends SqlMapClientDaoSupport implements EntityDao<E, PK>{
	
	@Resource(name = "sqlMapClient")
    private SqlMapClient sqlMapClient;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomIbatisDAO.class);
	
    @PostConstruct
        public void initSqlMapClient(){
         super.setSqlMapClient(sqlMapClient);
    }

    
    //返回SQLMAP的命名空间名称
	public String getIbatisSqlMapNamespace() {
		throw new RuntimeException("not yet implement");
	}
	
   
	
	//SQLMAP 新增
	public String getInsertStatement(String method) {
		if(method.equals(""))
		{
			return getIbatisSqlMapNamespace() + ".insert";
		}else{
			return getIbatisSqlMapNamespace() +"."+ method;
		}
	}
	//SQLMAP 修改
	public String getUpdateStatement(String method) {
		
		if(method.equals(""))
		{
			return getIbatisSqlMapNamespace() + ".update";
		}else{
			return getIbatisSqlMapNamespace() +"."+ method;
		}

	}
	//SQLMAP 删除
	public String getDeleteStatement(String method) {
		if(method.equals(""))
		{
			return getIbatisSqlMapNamespace() + ".delete";
		}else{
			return getIbatisSqlMapNamespace() +"."+ method;
		}
	}
	//SQLMAP 查询
	public String getFindByPrimaryKeyStatement(String method) {
		if(method.equals(""))
		{
			return getIbatisSqlMapNamespace() + ".getById";
		}else{
			return getIbatisSqlMapNamespace() +"."+ method;
		}
	}
	
	
	public boolean isUnique(E entity, String uniquePropertyNames) {
		throw new UnsupportedOperationException();
	}
	
	
	/**
	 * 此处增加对版本控制的统一赋值 version updatedVersion
	 * 
	 * @param o
	 */
	protected void prepareObjectForSaveOrUpdate(E o,Map<String, Object> map) {
		try {
			
			if (o != null) {

				Field versionField = o.getClass().getDeclaredField("version");
				Field updatedVersionField = o.getClass().getDeclaredField(
						"updatedVersion");
				versionField.setAccessible(true);
				updatedVersionField.setAccessible(true);
				Object version = versionField.get(o);
				if (version == null) {
					versionField.set(o, System.nanoTime());
				}
				updatedVersionField.set(o, System.nanoTime());

			}else{
				
				Long version = (Long)map.get("version");
				
				if (version == null) {
					version = System.nanoTime();
				}
				
				map.put("version", version);
				map.put("updatedVersion", System.nanoTime());
				
			}
		} catch (Exception e) {
			LOGGER.error(
					"Hit exception when set Optimistic Lock with version column.",
					e);
		}
	}
	

	/**
	 * 新增
	 * @param entity
	 * @param map
	 * @return
	 */
	public Object save(E entity,Map<String, Object> map,String method) {
		prepareObjectForSaveOrUpdate(entity,map);
		if(entity!=null)
		{
			return getSqlMapClientTemplate().insert(getInsertStatement(method), entity);
		}else{
			return getSqlMapClientTemplate().insert(getInsertStatement(method), map);
		}
	}
	
	/**
	 * 修改
	 * @param entity
	 * @param map
	 * @throws VersionException 
	 */
	public int update(E entity,Map<String, Object> map,String method) throws DataAccessException {
		prepareObjectForSaveOrUpdate(entity,map);
		
		int result = 0;
		
		if(entity!=null)
		{
			result = getSqlMapClientTemplate().update(getUpdateStatement(method), entity);
		}else{
			result = getSqlMapClientTemplate().update(getUpdateStatement(method), map);	
		}

		if (result <= 0) {
			
		}
		
		return result;
	}
	

	/**
	 * 删除
	 */
	public void deleteById(PK id,String method) {
		getSqlMapClientTemplate().delete(getDeleteStatement(method), id);
	}

	public List<E> findAll() {
		throw new UnsupportedOperationException();
	}

	public Object getById(PK primaryKey,String method) {
		Object object = getSqlMapClientTemplate().queryForObject(
				getFindByPrimaryKeyStatement(method), primaryKey);
		return object;
	}
	
}
