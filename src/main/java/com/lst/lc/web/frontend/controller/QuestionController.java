package com.lst.lc.web.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/question")
public class QuestionController {
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		return "frontend/question/add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Model model, String title, String tag, String content,RedirectAttributes redirectAttributes){
		
		//添加问题的方法
		
		//然后question放在model里面
		
		//一般删除更新添加操作，要给结果信息
		redirectAttributes.addFlashAttribute("questionMsg", "问题发布成功");
		return "frontend/question/view";
	}
	
	
	@RequestMapping(value = "/view/{questionId}", method = RequestMethod.GET)
	public String detail(Model model, int questionId){
		
		//获取question放进model
		
		return "frontend/question/view";
	}
	
	

}
