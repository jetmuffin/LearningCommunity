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
	
	private Page<T> page;
	private List<T> list;

	public Page<T> getPage(int pageNow, int pageSize, Class<T> c) {
		int totalRowCount = pageDao.getTotalRowCount(c);
		int temp = totalRowCount / pageSize;
		int totalPageCount = (totalRowCount % pageSize) == 0 ? temp : temp + 1;
		if(pageNow > totalPageCount)
			return null;
		page = new Page<T>();
		page.setPageNow(pageNow);
		page.setTotalPageCount(totalPageCount);
		list = pageDao.getPageList(pageSize, pageNow, c);
		page.setList(list);
		return page;
	}
}
