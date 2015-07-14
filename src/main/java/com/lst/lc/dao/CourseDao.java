package com.lst.lc.dao;

import java.util.List;

import org.hibernate.Query;

import com.lst.lc.entities.Course;

public interface CourseDao {
	
	public void addCourse(Course course);
	
	public Course getCourse(int id);
	
	public void updateCourse(Course course);
	
	public List<Course> getEnabledCourses();
	
	public List<Course> getAllCourses();
	
	public List<Course> getCoursesOrderByNums();
	
	public Query getAllCoursesOfCategory(int categoryId);
	
	public Query getAllCoursesOfCategoryByNums(int categoryId);
	
}
