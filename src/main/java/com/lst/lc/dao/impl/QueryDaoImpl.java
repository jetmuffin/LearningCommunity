package com.lst.lc.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lst.lc.dao.QueryDao;

@Repository("queryDao")
public class QueryDaoImpl extends BaseDao implements QueryDao {

	@Override
	public Query getQuery(String hql) {
		return query(hql);
	}

}
