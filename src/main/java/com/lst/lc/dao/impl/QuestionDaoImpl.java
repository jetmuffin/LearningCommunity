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
	public List<Question> getTopFiveRecently(int id) {
		Date end = DateUtils.getDateBefore(new Date(), 40);
		String hql = "from Question as question where question.time > ? and question.questionId != ? order by question.readNums*0.2+question.answerNums*0.8 desc";
		Query query = query(hql);
		query.setDate(0, end).setInteger(1, id);
		query.setMaxResults(5);
		List<Question> lists = query.list();
		return lists;
	}

	@Override
	public void addReadNums(int questionId) {
		String hql = "update Question as question set question.readNums = question.readNums + 1 where question.questionId = ?";
		Query query = query(hql).setInteger(0, questionId);
		query.executeUpdate();
	}

	@Override
	public List<Question> search(String key) {
		String hql = "from Question as question where question.title like ?";
		Query query = query(hql).setString(0, "%" + key + "%");
		return query.list();
	}

	@Override
	public List<Question> getQuestionOfUser(int userId) {
		String hql = "from Question as question where question.user.userId = ?";
		Query query = query(hql);
		query.setInteger(0, userId);
		return query.list();
	}

}
