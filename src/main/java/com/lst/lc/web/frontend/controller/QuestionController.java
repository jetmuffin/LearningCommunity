package com.lst.lc.web.frontend.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lst.lc.dao.QueryDao;
import com.lst.lc.dao.QuestionAnswerDao;
import com.lst.lc.dao.QuestionDao;
import com.lst.lc.entities.Course;
import com.lst.lc.entities.Direction;
import com.lst.lc.entities.Question;
import com.lst.lc.entities.QuestionAnswer;
import com.lst.lc.entities.User;
import com.lst.lc.page.Page;
import com.lst.lc.page.PageHandler;
import com.lst.lc.web.service.QuestionPageHandler;

@Controller
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	@Qualifier("questionDao")
	private QuestionDao questionDao;

	@Autowired
	@Qualifier("questionAnswerDao")
	private QuestionAnswerDao questionAnswerDao;

	@Autowired
	@Qualifier("queryDao")
	private QueryDao queryDao;

	@Autowired
	private QuestionPageHandler questionPageHandler;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		return "frontend/question/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Model model, String title, String tag, String content,
			HttpSession session, RedirectAttributes redirectAttributes) {
		User user = (User) session.getAttribute("user");
		Question question = new Question(user, title, content, new Date(), 0,
				0, tag, null);
		questionDao.addQuestion(question);
		model.addAttribute("question", question);
		redirectAttributes.addFlashAttribute("questionMsg", "问题发布成功");
		return "redirect:/question/view/" + question.getQuestionId();
	}

	@RequestMapping(value = "/view/{questionId}", method = RequestMethod.GET)
	public String detail(Model model, @PathVariable int questionId,
			String pageNum, String pageSize) {

		int pageNow = 1;
		int pagesize = 10;
		if (pageSize != null) {
			pagesize = Integer.valueOf(pageSize);
		}
		if (pageSize != null) {
			pageNow = Integer.valueOf(pageNum);
		}
		Question question = questionDao.getQuestion(questionId);

		model.addAttribute("question", question);
		model.addAttribute("answers",
				questionPageHandler.getAnswers(questionId, pageNow, pagesize));
		return "frontend/question/view";
	}

	/**
	 * 问题列表
	 * 
	 * @param model
	 * @param pageNum
	 * @param pageSize
	 * @param type
	 *            　排序类型，取指1,2,3，１表示按照回答数排序，2表示按照阅读数排序，3表示按照时间排序
	 * @return
	 */
	@RequestMapping(value = "/questions", method = RequestMethod.GET)
	public String list(Model model, String pageNum, String pageSize, String type) {
		int pageNow = 1;
		int pagesize = 10;
		int sorttype = 1;
		if (pageSize != null) {
			pagesize = Integer.valueOf(pageSize);
		}
		if (pageSize != null) {
			pageNow = Integer.valueOf(pageNum);
		}
		if (type != null) {
			sorttype = Integer.valueOf(type);
		}
		model.addAttribute("page",
				questionPageHandler.getQuestions(pageNow, pagesize, sorttype));

		return "frontend/question/list";
	}

	@RequestMapping(value = "/edit/{questionId}", method = RequestMethod.GET)
	public String edit(@PathVariable int questionId, Model model) {
		Question question = questionDao.getQuestion(questionId);
		model.addAttribute("question", question);
		return "frontend/question/edit";
	}

	@RequestMapping(value = "/edit/{questionId}", method = RequestMethod.POST)
	public String edit(@PathVariable int questionId, Model model, String title,
			String tag, String content, RedirectAttributes redirectAttributes) {
		questionDao.update(questionId, title, tag, content);
		redirectAttributes.addFlashAttribute("questionMsg", "问题修改成功");
		return "redirect:/question/view/" + questionId;
	}

}
