package com.lst.lc.web.bean;

import java.util.ArrayList;
import java.util.List;

import com.lst.lc.entities.Question;
import com.lst.lc.entities.QuestionTag;

public class QuestionSide {
	
	private int questionNums;
	private int answerNums;
	private List<QuestionTag> tags = new ArrayList<QuestionTag>();
	private List<Question> questions = new ArrayList<Question>();

}
