package com.lst.lc.dao;

import com.lst.lc.entities.Course;

public interface CourseDao {
	
	public void addCourse(Course course);
	
	public Course getCourse(int id);
	
	public void updateCourse(Course course);
	
}
