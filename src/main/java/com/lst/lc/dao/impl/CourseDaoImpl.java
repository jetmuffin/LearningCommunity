package com.lst.lc.dao.impl;

import com.lst.lc.dao.CourseDao;
import com.lst.lc.entities.Course;

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

}
