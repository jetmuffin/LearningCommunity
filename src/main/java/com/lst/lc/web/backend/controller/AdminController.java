package com.lst.lc.web.backend.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lst.lc.dao.AdminDao;
import com.lst.lc.entities.Admin;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	@Qualifier("adminDao")
	private AdminDao adminDao;

	public AdminController() {
		super();
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "backend/admin/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, HttpSession session, String email,
			String password) {

		Admin admin = adminDao.validateAdmin(email, password);
		if (admin == null) {
			session.setAttribute("loginInfo", "邮箱错误");
			return "backend/admin/login";
		} else if(admin.getPassword().equals(password)){
			session.setAttribute("loginAdmin", admin);
			return "backend/admin/index";
		}else{
			session.setAttribute("loginInfo", "密码错误");
			return "backend/admin/login";
		}
		
	}

}
