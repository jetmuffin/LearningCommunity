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
import com.lst.lc.entities.QuestionAnswer;
import com.lst.lc.entities.User;

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
		
		
		//User user = (User) session.getAttribute("user");
		
		User user = new User();
		
		//添加问题的方法
		Question question = new Question(user, title, content, new Date(), 0, 0, tag, null);
		
		questionDao.addQuestion(question);
		//然后question放在model里面
		model.addAttribute("question",question);
		//一般删除更新添加操作，要给结果信息
		redirectAttributes.addFlashAttribute("questionMsg", "问题发布成功");
		return "frontend/question/view";
	}
	
	
	@RequestMapping(value = "/view/{questionId}", method = RequestMethod.GET)
	public String detail(Model model, int questionId){
		
		//获取question放进model
		Question question = questionDao.getQuestion(questionId);
		//questiongAnswear
		List<QuestionAnswer> answers = questionAnswerDao.getAllQuestionAnswers(questionId);
		
		model.addAttribute("question", question);
		model.addAttribute("answers",answers);
		return "frontend/question/view";
	}
	
	

}
