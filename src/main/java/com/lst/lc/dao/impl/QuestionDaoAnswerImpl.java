package com.lst.lc.dao.impl;

import java.util.List;

import org.hibernate.Query;
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionAnswer> getAllQuestionAnswers(int questionId){
		String hql = "from QuestionAnswer as questionAnswer where questionAnswer.question.questionId = ?";
		Query query = query(hql);
		query.setInteger(0, questionId);
		return query.list();
	}

}
