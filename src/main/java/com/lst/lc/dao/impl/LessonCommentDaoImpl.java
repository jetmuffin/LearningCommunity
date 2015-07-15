package com.lst.lc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lst.lc.dao.LessonCommentDao;
import com.lst.lc.entities.LessonComment;

@Repository("lessonCommentDao")
public class LessonCommentDaoImpl extends BaseDao implements LessonCommentDao{

	@Override
	public void addLessonComment(LessonComment lessonComment) {
		save(lessonComment);
	}

	@Override
	public List<LessonComment> getCommentsOfLesson(int lessonId) {
		String hql = "from LessonComment as comment where comment.courseLesson.lessonId = ?";
		Query query = query(hql);
		query.setInteger(0, lessonId);
		return query.list();
	}

	@Override
	public void delete(int commentId) {
		String hql = "delete LessonComment as comment where comment.commentId = ?";
		Query query = query(hql);
		query.setInteger(0, commentId);
		query.executeUpdate();
	}

}
