package com.benclaus.koperasi.dao;

import java.io.Serializable;
import java.util.List;

import com.benclaus.koperasi.utility.Constant;
import com.ibatis.common.util.PaginatedList;

/**
 * @author Lambok
 *
 */
public class Page implements Serializable {
	private List list;
	private int pageIndex;
	private int totalSize;
	private int totalPage;
	private int pageSize;
	private boolean hasNext;
	private boolean hasPrev;

	public Page(PaginatedList list, int totalSize) {
		this.list = list;
		this.pageIndex = list.getPageIndex()+1;
		this.totalSize = totalSize;
		this.totalPage = ((totalSize - 1) / list.getPageSize()) + 1;
		this.hasNext = list.isNextPageAvailable();
		this.hasPrev = list.isPreviousPageAvailable();
	}
	
	public Page(List list, int totalSize) {
		this.list = list;
		this.totalSize = totalSize;
		this.totalPage = ((totalSize - 1) / Constant.PAGESIZE) + 1;
		this.pageIndex = 1;
	}
	
	public Page(List list, int totalSize, int pageIndex) {
		if ((pageIndex * Constant.PAGESIZE) > list.size()) {
			this.list = list.subList(((pageIndex-1) * Constant.PAGESIZE), totalSize);		
		}else{
			this.list = list.subList(((pageIndex-1) * Constant.PAGESIZE), (pageIndex * Constant.PAGESIZE));
		}		
		this.totalSize = totalSize;
		this.totalPage = ((totalSize - 1) / Constant.PAGESIZE) + 1;		
		this.pageIndex = pageIndex;
	}	
	
	public List getList() { return list; }
	
	/**
	 * Returns the hasNext.
	 * @return boolean
	 */
	public boolean isHasNext() {
		return hasNext;
	}

	/**
	 * Returns the hasPrev.
	 * @return boolean
	 */
	public boolean isHasPrev() {
		return hasPrev;
	}

	/**
	 * Returns the pageIndex.
	 * @return int
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * Returns the pageSize.
	 * @return int
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * Returns the totalPage.
	 * @return int
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * Returns the totalSize.
	 * @return int
	 */
	public int getTotalSize() {
		return totalSize;
	}
}
