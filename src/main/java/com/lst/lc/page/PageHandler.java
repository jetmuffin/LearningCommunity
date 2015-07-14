package com.lst.lc.page;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PageHandler<T> {

	@Autowired
	@Qualifier("pageDao")
	private PageDao pageDao;

	/**
	 * 获取分页page，默认排序（以时间排序）
	 * 
	 * @param pageNow
	 *            页码
	 * @param pageSize
	 *            　每页数据数量
	 * @param c
	 *            　类
	 * @return
	 */
	public Page<T> getPage(int pageNow, int pageSize, Class<T> c) {
		Page<T> page = initPage(pageNow, pageSize, c);
		// 没有数据
		if (page == null) {
			Page<T> emptyPage = new Page<T>();
			emptyPage.setPageNow(1);
			emptyPage.setTotalPageCount(0);
			return emptyPage;
		} else {
			int pagesize = (int) page.getPageNow();
			List<T> list = pageDao.getPageList(pageSize, pagesize, c);
			page.setList(list);
			return page;
		}

	}

	/**
	 * 获取分页page，自定义排序
	 * @param pageNow 页码
	 * @param pageSize 每页数据数量
	 * @param c 类
	 * @param query 自定义查询
	 * @return
	 */
	public Page<T> getPage(int pageNow, int pageSize, Class<T> c, Query query) {
		Page<T> page = initPage(pageNow, pageSize, c);
		List<T> list = pageDao.getPageList(pageSize, pageNow, query);
		page.setList(list);
		return page;
	}

	/**
	 * 初始化分页page的页码相关信息
	 * @param pageNow　页码
	 * @param pageSize 每页数据数量
	 * @param c　类
	 * @return
	 */
	public Page<T> initPage(int pageNow, int pageSize, Class<T> c) {
		// 获取全部数据数
		long totalRowCount = pageDao.getTotalRowCount(c);
		if (totalRowCount == 0)
			return null;
		/*
		 * 计算页码数
		 */
		long temp = totalRowCount / pageSize;
		long totalPageCount = (totalRowCount % pageSize) == 0 ? temp : temp + 1;
		if (pageNow > totalPageCount) {
			pageNow = 1;
		}
		Page<T> page = new Page<T>();
		page.setPageNow(pageNow);
		page.setTotalPageCount(totalPageCount);
		return page;
	}
}
