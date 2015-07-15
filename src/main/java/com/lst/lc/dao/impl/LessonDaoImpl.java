package com.lst.lc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lst.lc.dao.LessonDao;
import com.lst.lc.entities.CourseLesson;

@Repository("lessonDao")
public class LessonDaoImpl extends BaseDao implements LessonDao {

	@Override
	public void addLesson(CourseLesson courseLesson) {
		save(courseLesson);
	}

	@Override
	public CourseLesson getLesson(int id) {
		return get(CourseLesson.class, id);
	}

	@Override
	public List<CourseLesson> getLessons() {
		return getAll("CourseLesson");
	}

	@Override
	public List<CourseLesson> getLessonsOfCourse(int courseId) {
		String hql = "from CourseLesson as lesson where lesson.course.courseId = ?";
		Query query = query(hql);
		query.setInteger(0, courseId);
		return query.list();
	}

}
