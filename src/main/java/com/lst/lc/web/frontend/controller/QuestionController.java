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
import com.lst.lc.entities.Question;
import com.lst.lc.entities.QuestionAnswer;
import com.lst.lc.entities.User;
import com.lst.lc.page.Page;
import com.lst.lc.page.PageHandler;

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
	private PageHandler<Question> pageHandler;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		return "frontend/question/add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Model model, String title, String tag, String content,HttpSession session,RedirectAttributes redirectAttributes){
		User user = (User) session.getAttribute("user");
		Question question = new Question(user, title, content, new Date(), 0, 0, tag, null);
		questionDao.addQuestion(question);
		model.addAttribute("question",question);
		redirectAttributes.addFlashAttribute("questionMsg", "问题发布成功");
		return "redirect:/question/view/"+question.getQuestionId();
	}
	
	
	@RequestMapping(value = "/view/{questionId}", method = RequestMethod.GET)
	public String detail(Model model,@PathVariable int questionId){
		System.err.println("questionId"+questionId);
		//获取question放进model
		Question question = questionDao.getQuestion(questionId);
		//questiongAnswear
		List<QuestionAnswer> answers = questionAnswerDao.getAllQuestionAnswers(questionId);
		
		model.addAttribute("question", question);
		model.addAttribute("answers",answers);
		return "frontend/question/view";
	}
	
	@RequestMapping(value = "/questions", method = RequestMethod.GET)
	public String list(Model model, String pageNum, String pageSize){
		int pageNow = 1;
		int pagesize = 10;
		if (pageSize != null) {
			pagesize = Integer.valueOf(pageSize);
		}
		if (pageSize != null) {
			pageNow = Integer.valueOf(pageNum);
		}
		String hql = "from Question as question order by question.answerNums desc";
		Query query = queryDao.getQuery(hql);
		Page<Question> page = pageHandler
				.getPage(pageNow, pagesize, Question.class, query);
		model.addAttribute("page", page);
		
		return "frontend/question/list";
	}
	

}
