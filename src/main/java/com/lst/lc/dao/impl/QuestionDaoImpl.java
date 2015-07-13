package com.lst.lc.dao.impl;

import org.springframework.stereotype.Repository;

import com.lst.lc.dao.QuestionDao;
import com.lst.lc.entities.Question;

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

}
