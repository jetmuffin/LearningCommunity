package com.lst.lc.dao;

import org.hibernate.Query;

public interface QueryDao {
	public Query getQuery(String hql);
}
