package com.lst.lc.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lst.lc.dao.QuestionDao;
import com.lst.lc.entities.Question;
import com.lst.lc.utils.DateUtils;

/**
 * @author innerac
 *
 */
@Repository("questionDao")
public class QuestionDaoImpl extends BaseDao implements QuestionDao {

	@Override
	public void addQuestion(Question question) {
		save(question);
	}

	@Override
	public void updateQuestion(Question question) {
		update(question);
	}

	@Override
	public Question getQuestion(int questionId) {
		return get(Question.class, questionId);
	}

	@Override
	public void update(int questionId, String title, String tag, String content) {
		String hql = "update Question as question set question.title = ?, question.content = ?, question.tag = ? where question.questionId = ?";
		Query query = query(hql);
		query.setString(0, title).setString(1, content).setString(2, tag)
				.setInteger(3, questionId).executeUpdate();
	}

	@Override
	public List<Question> getTopFiveRecently() {
		Date end = DateUtils.getDateBefore(new Date(), 7);
		String hql = "from Question as question where question.time > ? order by question.readNums*0.2+question.answerNums*0.8 desc";
		Query query = query(hql);
		query.setDate(0, end);
		query.setMaxResults(5);
		List<Question> lists = query.list();
		return lists;
	}

}
