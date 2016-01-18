package com.id.get.model;

import java.util.ArrayList;
import java.util.List;

/** 
 * <br>类 名: ModelView 
 * <br>描 述: 页面展示数据
 * <br>作 者: yufenghe 
 * <br>创 建： 2016年1月7日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class ModelView<T> {
	private long total;
	
	private List<T> rows = new ArrayList<T>();

	public long getTotal() {
		return total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
