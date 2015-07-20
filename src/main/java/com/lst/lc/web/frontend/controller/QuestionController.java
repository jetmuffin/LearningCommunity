package com.lst.lc.web.frontend.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.lst.lc.dao.QuestionTagDao;
import com.lst.lc.dao.UserDao;
import com.lst.lc.entities.Course;
import com.lst.lc.entities.Direction;
import com.lst.lc.entities.Question;
import com.lst.lc.entities.QuestionAnswer;
import com.lst.lc.entities.QuestionTag;
import com.lst.lc.entities.User;
import com.lst.lc.page.Page;
import com.lst.lc.page.PageHandler;
import com.lst.lc.utils.StringUtils;
import com.lst.lc.web.bean.QuestionSide;
import com.lst.lc.web.service.LogHandler;
import com.lst.lc.web.service.QuestionPageHandler;

@Controller
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;

	@Autowired
	@Qualifier("questionDao")
	private QuestionDao questionDao;

	@Autowired
	@Qualifier("questionAnswerDao")
	private QuestionAnswerDao questionAnswerDao;

	@Autowired
	@Qualifier("questionTagDao")
	private QuestionTagDao questionTagDao;

	@Autowired
	private QuestionPageHandler questionPageHandler;

	@Autowired
	private LogHandler logHandler;

	@RequestMapping(value = "/ask", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("module", "question");
		return "frontend/question/add";
	}

	@RequestMapping(value = "/ask", method = RequestMethod.POST)
	public String add(Model model, String title, String tag, String content,
			HttpSession session, RedirectAttributes redirectAttributes) {
		User user = (User) session.getAttribute("loginUser");

		Set<QuestionTag> tagSet = new HashSet<QuestionTag>();
		List<String> tags = StringUtils.stringSplit(tag);
		for (int i = 0; i < tags.size(); i++) {
			QuestionTag questionTag = questionTagDao.getTagByName(tags.get(i));
			if (questionTag == null) {
				questionTag = new QuestionTag(tags.get(i), 1);
			} else {
				// 数量加１
				int number = questionTag.getNumber() + 1;
				questionTag.setNumber(number);
			}
			tagSet.add(questionTag);
		}

		Question question = new Question(user, title, content, new Date(), 0,
				0, tag, tagSet, null);
		questionDao.addQuestion(question);
		question.getQuestionId();
		// 写入日志，用户增加积分
		logHandler.toLog(user, "发布问题:" + question.getQuestionId());
		logHandler.updateIntegral(user.getUserId(), "addQuestion");

		model.addAttribute("question", question);
		redirectAttributes.addFlashAttribute("questionMsg", "问题发布成功");
		return "redirect:/question/view/" + question.getQuestionId();
	}

	@RequestMapping(value = "/view/{questionId}", method = RequestMethod.GET)
	public String detail(HttpSession session, Model model,
			@PathVariable int questionId, String pageNum, String pageSize) {

		int pageNow = 1;
		int pagesize = 10;
		if (pageSize != null) {
			pagesize = Integer.valueOf(pageSize);
		}
		if (pageSize != null) {
			pageNow = Integer.valueOf(pageNum);
		}
		Question question = questionDao.getQuestion(questionId);
		questionDao.addReadNums(questionId);
		User user = (User) session.getAttribute("loginUser");
		QuestionSide questionSide;
		List<QuestionTag> tags = questionTagDao.getTagsOrderByNum();
		List<Question> questions = questionDao.getTopFiveRecently();
		if (user == null) {
			questionSide = new QuestionSide(false, 0, 0, tags, questions);
		} else {
			User u = userDao.getById(user.getUserId());
			questionSide = new QuestionSide(true, u.getQuestions().size(), u
					.getQuestionAnswers().size(), tags, questions);
		}
		model.addAttribute("questionSide", questionSide);
		model.addAttribute("question", question);
		model.addAttribute("answers",
				questionPageHandler.getAnswers(questionId, pageNow, pagesize));
		model.addAttribute("module", "question");
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
	public String list(HttpSession session, Model model, String pageNum,
			String pageSize, String type) {
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
		User user = (User) session.getAttribute("loginUser");
		QuestionSide questionSide;
		List<QuestionTag> tags = questionTagDao.getTagsOrderByNum();
		List<Question> questions = questionDao.getTopFiveRecently();
		if (user == null) {
			questionSide = new QuestionSide(false, 0, 0, tags, questions);
		} else {
			User u = userDao.getById(user.getUserId());
			questionSide = new QuestionSide(true, u.getQuestions().size(), u
					.getQuestionAnswers().size(), tags, questions);
		}
		model.addAttribute("questionSide", questionSide);
		model.addAttribute("page",
				questionPageHandler.getQuestions(pageNow, pagesize, sorttype));
		model.addAttribute("module", "question");
		return "frontend/question/list";
	}

	@RequestMapping(value = "/edit/{questionId}", method = RequestMethod.GET)
	public String edit(@PathVariable int questionId, Model model) {
		Question question = questionDao.getQuestion(questionId);
		model.addAttribute("question", question);
		model.addAttribute("module", "question");
		return "frontend/question/edit";
	}

	@RequestMapping(value = "/edit/{questionId}", method = RequestMethod.POST)
	public String edit(@PathVariable int questionId, Model model, String title,
			String tag, String content, RedirectAttributes redirectAttributes) {
		questionDao.update(questionId, title, tag, content);
		redirectAttributes.addFlashAttribute("questionMsg", "问题修改成功");
		return "redirect:/question/view/" + questionId;
	}

	@RequestMapping(value = "/answer/{questionId}", method = RequestMethod.POST)
	public String answer(Model model, @PathVariable int questionId,
			String head, String content, HttpSession session,
			RedirectAttributes redirectAttributes) {

		User user = (User) session.getAttribute("loginUser");
		Question question = questionDao.getQuestion(questionId);
		QuestionAnswer answer = new QuestionAnswer(question, user, new Date(),
				content, head);
		questionAnswerDao.addQuestionAnswer(answer);
		// 写入日志
		logHandler.toLog(user, "回答了问题:" + questionId);
		logHandler.updateIntegral(user.getUserId(), "answerQuestion");

		redirectAttributes.addFlashAttribute("questionMsg", "回答成功");
		return "redirect:/question/view/" + questionId;
	}
}
