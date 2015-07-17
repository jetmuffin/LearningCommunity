package com.lst.lc.web.frontend.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lst.lc.dao.QuestionAnswerDao;
import com.lst.lc.dao.QuestionDao;
import com.lst.lc.entities.Question;
import com.lst.lc.entities.User;
import com.lst.lc.web.bean.Answer;

@Controller
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	@Qualifier("questionDao")
	private QuestionDao questionDao;
	
	@Autowired
	@Qualifier("questionAnswerDao")
	private QuestionAnswerDao questionAnswerDao;
	
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
		//获取question放进model
		Question question = questionDao.getQuestion(questionId);
		//questiongAnswear
		List<Answer> answers = questionAnswerDao.getAllAnswers(questionId);
		
		model.addAttribute("question", question);
		model.addAttribute("answers",answers);
		return "frontend/question/view";
	}
	
	

}
