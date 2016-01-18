package com.id.get.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 
 * <br>类 名: Pager 
 * <br>描 述: 分页处理类
 * <br>作 者: yufenghe 
 * <br>创 建： 2016年1月6日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class Pager<T> {
	public static final int PAGE_SIZE = 20;
	
	/**当前页**/
	private Long currentPage = 1L;
	/**总页数**/
    public Long totalPages = 0L;
    /**每页显示的数据条数**/
    private Long pageSize = 0L;
    /**总数据数**/
    private Long totalSize = 0L;
    /**数据集**/
    private List<T> list = new ArrayList<T>();
    
    private Date start;
    
    private Date end;
    
    private String phone;
	
	public Pager() {
		
	}
	
	public Pager(Date start, Date end, long currentPage, long pageSize, String phone) {
		this.start = start;
		this.end = end;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.phone = phone;
	}

    public void init(List<T> list, long totalSize) {
        this.list = list;
        this.totalSize = totalSize==-1?(list==null?0:list.size()):totalSize;
        
        if ((this.totalSize % this.pageSize) == 0) {
            this.totalPages = this.totalSize / this.pageSize;
        } else {
            this.totalPages = this.totalSize / this.pageSize + 1;
        }
    }

	public Long getCurrentPage() {
		return currentPage;
	}

	public Long getTotalPages() {
		return totalPages;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public Long getTotalSize() {
		return totalSize;
	}

	public List<T> getList() {
		return list;
	}

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}

	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}

	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalSize(Long totalSize) {
		this.totalSize = totalSize;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String toString() {
		return "{start:" + this.start + ";end:" + this.end + ";currentPage:" + this.currentPage + ";pageSize:" + this.pageSize + ";phone:" + this.phone +  "}";
	}

}
