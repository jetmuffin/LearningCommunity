package com.lst.lc.web.service;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lst.lc.dao.QueryDao;
import com.lst.lc.dao.QuestionAnswerDao;
import com.lst.lc.entities.Question;
import com.lst.lc.entities.QuestionAnswer;
import com.lst.lc.page.Page;
import com.lst.lc.page.PageHandler;

@Service
public class QuestionPageHandler {

	@Autowired
	private PageHandler<Question> questionPageHandler;
	
	@Autowired
	private PageHandler<QuestionAnswer> answerPageHandler;

	@Autowired
	@Qualifier("queryDao")
	private QueryDao queryDao;

	@Autowired
	@Qualifier("questionAnswerDao")
	private QuestionAnswerDao questionAnswerDao;

	public Page<Question> getQuestions(int pageNum, int pageSize, int type) {
		// 按照回答数量排序,type=1
		String hql1 = "from Question as question order by question.answerNums desc";
		// 按照阅读数量排序,type=2
		String hql2 = "from Question as question order by question.readNums desc";
		// 按照发布时间排序,type=3
		String hql3 = "from Question as question order by question.time desc";
		String hql = null;
		if (type == 1)
			hql = hql1;
		else if (type == 2)
			hql = hql2;
		else
			hql = hql3;
		Query query = queryDao.getQuery(hql);
		Page<Question> page = questionPageHandler.getPage(pageNum, pageSize,
				Question.class, query);
		return page;
	}

	public Page<QuestionAnswer> getAnswers(int questionId, int pageNum,
			int pageSize) {

		String hql = "from QuestionAnswer as questionAnswer where questionAnswer.question.questionId = ?";
		Query query = queryDao.getQuery(hql);
		query.setInteger(0, questionId);
		Page<QuestionAnswer> page = answerPageHandler.getPage(pageNum, pageSize,
				QuestionAnswer.class, query);
		return page;
	}
}
