package com.lst.lc.test.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.junit.Test;

import com.lst.lc.entities.RelUserCourse;
import com.lst.lc.entities.RelUserCourseId;
import com.lst.lc.utils.ListUtils;
import com.lst.lc.web.bean.LearnStatus;

public class LessonDaoTest extends BaseTestDao {

	@Test
	public void test(){
		System.out.println(learnStatus(1, 10).getProgress());
	}
	
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
