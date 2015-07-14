package com.lst.lc.page;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.lst.lc.dao.impl.BaseDao;

@Repository("pageDao")
public class PageDao extends BaseDao {

	public <T> Criteria getCriteria(Class<T> c) {
		return getSession().createCriteria(c);
	}
	
	public <T> long getTotalRowCount(Class<T> c) {
		return (long) getCriteria(c).setProjection(Projections.rowCount())
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
