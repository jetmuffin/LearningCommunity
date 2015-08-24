package com.lst.lc.web.frontend.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lst.lc.dao.FeedbackDao;
import com.lst.lc.entities.Feedback;
import com.lst.lc.entities.User;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {
        
        @Autowired
        @Qualifier("feedbackDao")
        private FeedbackDao feedbackDao;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		return "frontend/feedback/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Model model, String title, String content,
			RedirectAttributes redirectAttributes, HttpSession session) {
	        User user = (User) session.getAttribute("loginUser");
	        Feedback feedback = new Feedback(user, new Date(), title, content, 0);
		feedbackDao.add(feedback);
		redirectAttributes.addFlashAttribute("feedbackMsg", "反馈信息已经收到，谢谢您的支持");
		return "frontend/user/add";
	}

}
