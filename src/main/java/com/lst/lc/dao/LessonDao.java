package com.lst.lc.dao;

import java.util.List;

import com.lst.lc.entities.CourseLesson;

public interface LessonDao {
	
	public void addLesson(CourseLesson courseLesson);
	
	public CourseLesson getLesson(int id);
	
	public List<CourseLesson> getLessons();
	
	public List<CourseLesson> getLessonsOfCourse(int courseId);

}
