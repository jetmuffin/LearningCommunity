package com.lst.lc.web.frontend.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		return "frontend/feedback/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Model model, String title, String content,
			RedirectAttributes redirectAttributes, HttpSession session) {

		// 添加反馈的方法

		// 然后feedback放在model里面，名字叫feedback

		// 一般删除更新添加操作，要给结果信息
		redirectAttributes.addFlashAttribute("feedbackMsg", "反馈信息已经收到，谢谢您的支持");
		return "frontend/user/add";
	}

}
