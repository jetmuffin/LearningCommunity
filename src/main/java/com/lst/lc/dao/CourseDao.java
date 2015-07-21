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
	
	public Query getAllCoursesOfCategory(int categoryId);
	
	public Query getAllCoursesOfCategoryByNums(int categoryId);

	void update(int courseId, String title, String description,
			String difficulty, int categoryId, int directionId, String enabled);
	
	public Query getQuery(String hql);

	void update(int courseId, String title, String description,
			String difficulty, int categoryId, int directionId, String enabled,
			String imageUrl);

	public void delete(int courseId);
	
	public List<Course> search(String key);
	
}
