package com.lst.lc.dao;

import java.util.List;

import org.hibernate.Query;

import com.lst.lc.entities.Course;
import com.lst.lc.entities.User;

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
	
	public List<Course> getOtherCourses(int courseId, int directionId);
	
	public void addNums(int courseId);
	
	public List<User> getUsers(int courseId);
	
	public List<Course> getCourseOfUser(int userId);
	
	public long getCount();
}
