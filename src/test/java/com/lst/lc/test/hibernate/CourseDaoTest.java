package com.lst.lc.test.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.junit.Test;

import com.lst.lc.entities.Course;
import com.lst.lc.entities.RelUserCourse;
import com.lst.lc.entities.User;

public class CourseDaoTest extends BaseTestDao{
	
	@Test
	public void test(){
		System.out.println(getUsers(10).size());
	}
	
	public List<Course> search(String key) {
		String hql = "from Course as course where course.title like ?";
		Query query = query(hql).setString(0, "%"+key+"%");
		return query.list();
	}
	
	public List<User> getUsers(int courseId) {
		List<User> users = new ArrayList<User>();
		Course course = get(Course.class, courseId);
		Set<RelUserCourse> ruc = course.getRelUserCourses();
		Object[] rucs = ruc.toArray();
		int length = rucs.length / 2;
		if(length == 0)
			return users;
		if(length > 5)
			length = 5;
		for(int i = 0; i < length; i++){
			users.add(((RelUserCourse)rucs[i]).getUser());
		}
		return users;
	}

}
