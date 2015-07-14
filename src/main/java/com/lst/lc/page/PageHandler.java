package com.lst.lc.page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PageHandler<T> {

	@Autowired
	@Qualifier("pageDao")
	private PageDao pageDao;

	public Page<T> getPage(int pageNow, int pageSize, Class<T> c) {
		Page<T> page = initPage(pageNow, pageSize, c);
		List<T> list = pageDao.getPageList(pageSize, pageNow, c);
		page.setList(list);
		return page;
	}
	
	public Page<T> getPage(int pageNow, int pageSize, Class<T> c, String hql) {
		Page<T> page = initPage(pageNow, pageSize, c);
		List<T> list = pageDao.getPageList(pageSize, pageNow, hql);
		page.setList(list);
		return page;
	}
	
	public Page<T> initPage(int pageNow, int pageSize, Class<T> c){
		long totalRowCount = pageDao.getTotalRowCount(c);
		long temp = totalRowCount / pageSize;
		long totalPageCount = (totalRowCount % pageSize) == 0 ? temp : temp + 1;
		if(pageNow > totalPageCount)
			return null;
		Page<T> page = new Page<T>();
		page.setPageNow(pageNow);
		page.setTotalPageCount(totalPageCount);
		return page;
	}
}
