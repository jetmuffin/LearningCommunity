package com.lst.lc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lst.lc.dao.CourseDao;
import com.lst.lc.entities.Course;

@Repository("courseDao")
public class CourseDaoImpl extends BaseDao implements CourseDao {

	@Override
	public void addCourse(Course course) {
		save(course);
	}

	@Override
	public Course getCourse(int id) {
		return get(Course.class, id);
	}

	@Override
	public void updateCourse(Course course) {
		update(course);
	}

	@Override
	public List<Course> getEnabledCourses() {
		String hqlString = "from Courses as course where course.enabled=?";
		Query query = query(hqlString);
		query.setString(0, "1");
		List<Course> ts = query.list();
		return ts;
	}

	@Override
	public List<Course> getAllCourses() {
		return getAll("Course");
	}

	@Override
	public List<Course> getCoursesOrderByNums() {
		// TODO Auto-generated method stub
		return null;
	}

}
