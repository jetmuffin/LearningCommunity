package com.lst.lc.dao.impl;

import org.springframework.stereotype.Repository;

import com.lst.lc.dao.QuestionAnswerDao;
import com.lst.lc.entities.QuestionAnswer;

@Repository("questionAnswerDao")
public class QuestionDaoAnswerImpl extends BaseDao implements QuestionAnswerDao {

	@Override
	public void addQuestionAnswer(QuestionAnswer questionAnswer) {
		save(questionAnswer);
	}

	@Override
	public void updateQuestionAnswer(QuestionAnswer questionAnswer) {
		update(questionAnswer);
	}

	@Override
	public QuestionAnswer getQuestionAnswer(int answerId) {
		return get(QuestionAnswer.class, answerId);
	}

}
