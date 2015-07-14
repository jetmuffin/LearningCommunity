package com.lst.lc.web.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用分页页面
 * @author sloriac
 * @param <T>
 */
public class Page<T> {
	
	private int pageNow;
	private int totalPageCount;
	private boolean hasPre;
	private boolean hasNext;
	List<T> list = new ArrayList<T>();
	public Page() {
		super();
	}
	public Page(int pageNow, int totalPageCount, boolean hasPre,
			boolean hasNext, List<T> list) {
		super();
		this.pageNow = pageNow;
		this.totalPageCount = totalPageCount;
		this.hasPre = hasPre;
		this.hasNext = hasNext;
		this.list = list;
	}
	public int getPageNow() {
		return pageNow;
	}
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public boolean isHasPre() {
		return hasPre;
	}
	public void setHasPre(boolean hasPre) {
		this.hasPre = hasPre;
	}
	public boolean isHasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}

}
