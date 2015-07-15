package com.lst.lc.dao;

import java.util.List;

import com.lst.lc.entities.LessonComment;

public interface LessonCommentDao {
	
	public void addLessonComment(LessonComment lessonComment);
	
	public List<LessonComment> getCommentsOfLesson(int lessonId);
	
	public void delete(int commentId);

}
