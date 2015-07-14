package com.lst.lc.test.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.junit.Test;

import com.lst.lc.entities.Category;

public class PageDaoTest extends BaseTestDao{
	
	@Test
	public void test(){
		List<Category> categories = getPageList(2, 1, Category.class);
	}
	
	public <T> Criteria getCriteria(Class<T> c) {
		return getSession().createCriteria(c);
	}
	
	public <T> int getTotalRowCount(Class<T> c) {
		return (int) getCriteria(c).setProjection(Projections.rowCount())
				.uniqueResult();
	}
	
	public <T> List<T> getPageList(int pageSize, int pageNow, Class<T> c){
		Criteria criteria = getCriteria(c);
		criteria.setFirstResult((pageNow-1)*pageSize);
		criteria.setMaxResults(pageSize);
		List<T> list = criteria.list();
		return list;
	}

}
