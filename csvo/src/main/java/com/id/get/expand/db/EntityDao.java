package com.id.get.expand.db;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface EntityDao<E, PK extends Serializable> {

	public void deleteById(PK id,String method) throws DataAccessException;

	public List<E> findAll() throws DataAccessException;

	public Object getById(PK id,String method) throws DataAccessException;

	public boolean isUnique(E entity, String uniquePropertyNames) throws DataAccessException;

	/**
	 * 插入数据
	 * @return
	 */
	public Object save(E entity,Map<String, Object> map,String method) throws DataAccessException;

	/** 更新数据 */
	public int update(E entity,Map<String, Object> map,String method) throws DataAccessException;

}
