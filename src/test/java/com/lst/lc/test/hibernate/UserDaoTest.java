package com.lst.lc.test.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.junit.Test;

import com.lst.lc.entities.Course;
import com.lst.lc.entities.RelUserCourse;
import com.lst.lc.entities.RelUserCourseId;
import com.lst.lc.entities.User;

public class UserDaoTest extends BaseTestDao {
	
	@Test
	public void test(){
		init();
		learn(6, 10);
		destroy();
	}

	public List<User> getTopFive() {
		String hql = "from User as user order by user.blogs.size desc";
		Query query = query(hql).setMaxResults(5);
		return query.list();
	}
	
	public void learn(int userId, int courseId) {
		User user = get(User.class, userId);
		Course course = get(Course.class, courseId);
		RelUserCourseId id = new RelUserCourseId(userId, courseId);
		RelUserCourse userCourse = new RelUserCourse(id, course, user, new Date(), 0);
		save(userCourse);
	}
}
