package com.lst.lc.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lst.lc.dao.QuestionAnswerDao;
import com.lst.lc.entities.QuestionAnswer;
import com.lst.lc.entities.User;
import com.lst.lc.web.bean.Answer;
import com.mysql.fabric.xmlrpc.base.Array;

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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Answer> getAllAnswers(int questionId){
		String hql = "from QuestionAnswer as questionAnswer where questionAnswer.question.questionId = ?";
		Query query = query(hql);
		query.setInteger(0, questionId);
		List<QuestionAnswer> questionAnswers= query.list();
		List<Answer> answers = new ArrayList<Answer>();

		for(QuestionAnswer questionAnswer : questionAnswers){
			answers.add(toAnswer(questionAnswer));
		}
		return query.list();
	}
	
	public Answer toAnswer(QuestionAnswer questionAnswer){
		Answer answer = null;
		Integer answerId = null;
		Date time = null;
		String content = null;
		String head = null;
		Integer userId = null;
		String userName = null;
		int integral = 0;
		String rank = null;
		String avatar = null;
		
		User user = questionAnswer.getUser();
		answerId = questionAnswer.getAnswerId();
		time = questionAnswer.getTime();
		content = questionAnswer.getContent();
		head = questionAnswer.getHead();
		userId = user.getUserId();
		userName = user.getUserName();
		integral = user.getIntegral();
		rank = user.getRank();
		avatar = user.getAvatar();
		
		answer = new Answer(answerId, time, content, head, userId, userName, integral, rank, avatar);
		
		return answer;
	}

}
