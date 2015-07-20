package com.lst.lc.web.bean;

import java.util.ArrayList;
import java.util.List;

import com.lst.lc.entities.Question;
import com.lst.lc.entities.QuestionTag;

public class QuestionSide {
	
	private boolean ifLogin;
	private int questionNums;
	private int answerNums;
	private List<QuestionTag> tags = new ArrayList<QuestionTag>();
	private List<Question> questions = new ArrayList<Question>();
	public QuestionSide() {
		super();
	}
	
	public QuestionSide(boolean ifLogin, int questionNums, int answerNums,
			List<QuestionTag> tags, List<Question> questions) {
		super();
		this.ifLogin = ifLogin;
		this.questionNums = questionNums;
		this.answerNums = answerNums;
		this.tags = tags;
		this.questions = questions;
	}

	public int getAnswerNums() {
		return answerNums;
	}
	public void setAnswerNums(int answerNums) {
		this.answerNums = answerNums;
	}
	public List<QuestionTag> getTags() {
		return tags;
	}
	public void setTags(List<QuestionTag> tags) {
		this.tags = tags;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public boolean isIfLogin() {
		return ifLogin;
	}

	public void setIfLogin(boolean ifLogin) {
		this.ifLogin = ifLogin;
	}

	public int getQuestionNums() {
		return questionNums;
	}

	public void setQuestionNums(int questionNums) {
		this.questionNums = questionNums;
	}
	
}
