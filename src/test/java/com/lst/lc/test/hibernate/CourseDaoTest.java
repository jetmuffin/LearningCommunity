package com.lst.lc.test.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.junit.Test;

import com.lst.lc.entities.Course;

public class CourseDaoTest extends BaseTestDao{
	
	@Test
	public void test(){
		System.out.println(search("西游记").size());
	}
	
	public List<Course> search(String key) {
		String hql = "from Course as course where course.title like ?";
		Query query = query(hql).setString(0, "%"+key+"%");
		return query.list();
	}

}
