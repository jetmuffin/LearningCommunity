package com.lst.lc.dao;

import java.util.List;

import com.lst.lc.entities.CourseLesson;
import com.lst.lc.entities.User;
import com.lst.lc.web.bean.LearnStatus;

public interface LessonDao {
	
	public void addLesson(CourseLesson courseLesson);
	
	public CourseLesson getLesson(int id);
	
	public List<CourseLesson> getLessons();
	
	public List<CourseLesson> getLessonsOfCourse(int courseId);

	void update(int lessonId, String title, String summary, String type,
			String videoUrl, String content);

	public LearnStatus learnStatus(int userId, int courseId);
	
}
