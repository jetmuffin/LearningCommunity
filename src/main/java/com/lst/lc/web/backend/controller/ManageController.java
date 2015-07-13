package com.lst.lc.web.backend.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lst.lc.dao.AdminDao;
import com.lst.lc.entities.Admin;

@Controller
@RequestMapping("/manage")
public class ManageController {

	@Autowired
	@Qualifier("adminDao")
	private AdminDao adminDao;

	public ManageController() {
		super();
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "backend/admin/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession session, String email, String password,
			RedirectAttributes redirectAttributes) {

		Admin admin = adminDao.validateAdmin(email, password);
		if (admin == null) {
			redirectAttributes.addFlashAttribute("loginInfo", "邮箱错误");
			return "redirect:/manage/login";
		} else if (admin.getPassword().equals(password)) {
			session.setAttribute("loginAdmin", admin);
			return "redirect:/manage/index";
		} else {
			redirectAttributes.addFlashAttribute("loginInfo", "密码错误");
			return "redirect:/manage/login";
		}
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		return "backend/index/index";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String user(Model model) {
		model.addAttribute("module", "user");
		return "backend/user/list";
	}
	
	@RequestMapping(value = "/adduser", method = RequestMethod.GET)
	public String addUser(Model model) {
		model.addAttribute("module", "user");
		return "backend/user/add";
	}
	
	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public String course(Model model) {
		model.addAttribute("module", "course");
		return "backend/course/list";
	}
	
	@RequestMapping(value = "/addcourse", method = RequestMethod.GET)
	public String addCourse(Model model) {
		model.addAttribute("module", "course");
		return "backend/course/add";
	}

}
