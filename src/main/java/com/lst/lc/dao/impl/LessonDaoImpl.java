package com.lst.lc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lst.lc.dao.LessonDao;
import com.lst.lc.entities.CourseLesson;
import com.lst.lc.entities.RelUserCourse;
import com.lst.lc.entities.RelUserCourseId;
import com.lst.lc.utils.ListUtils;
import com.lst.lc.web.bean.LearnStatus;

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

	@Override
	public void update(int lessonId,String title, String summary,String type, String videoUrl, String content){
		String hql = "update CourseLesson as lesson set lesson.title = ?, lesson.summary = ?, lesson.type = ?, lesson.videoUrl = ?, lesson.content = ? where lesson.lessonId = ?";
		Query query = query(hql);
		query.setString(0, title).setString(1, summary).setString(2, type).setString(3, videoUrl).setString(4, content).setInteger(5, lessonId).executeUpdate();
	}

	@Override
	public LearnStatus learnStatus(int userId, int courseId) {
		RelUserCourseId id = new RelUserCourseId(userId, courseId);
		List<RelUserCourse> rels = get(RelUserCourse.class, id);
		LearnStatus status;
		if(ListUtils.isNull(rels)){
			status = new LearnStatus(false, 0);
		} else {
			status = new LearnStatus(true, rels.get(0).getProgress());
		}
		return status;
	}
}
