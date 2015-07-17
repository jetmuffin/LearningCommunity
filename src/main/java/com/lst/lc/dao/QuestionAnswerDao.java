package com.lst.lc.dao;

import java.util.List;

import com.lst.lc.entities.QuestionAnswer;
import com.lst.lc.web.bean.Answer;

/**
 * @author innerac
 *
 */
public interface QuestionAnswerDao {
	
	public void addQuestionAnswer(QuestionAnswer questionAnswer);
	
	public void updateQuestionAnswer(QuestionAnswer questionAnswer);
	
	public QuestionAnswer getQuestionAnswer(int answerId);

	List<Answer> getAllAnswers(int questionId);

	List<QuestionAnswer> getAllQuestionAnswers(int questionId);
}
