package com.lst.lc.test.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.junit.Test;

import com.lst.lc.entities.User;

public class UserDaoTest extends BaseTestDao {
	
	@Test
	public void test(){
		System.out.println(getTopFive().size());
	}

	public List<User> getTopFive() {
		String hql = "from User as user order by user.blogs.size desc";
		Query query = query(hql).setMaxResults(5);
		return query.list();
	}
}
