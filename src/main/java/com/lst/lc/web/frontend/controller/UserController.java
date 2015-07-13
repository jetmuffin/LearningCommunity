package com.lst.lc.web.frontend.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lst.lc.dao.UserDao;
import com.lst.lc.entities.Admin;
import com.lst.lc.entities.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;

	public UserController() {
		super();
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "frontend/user/login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		return "frontend/user/register";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession session, String email, String password) {
		User user = userDao.validateUser(email, password);
		if (user == null) {
			session.setAttribute("loginInfo", "邮箱错误");
			return "backend/user/login";
		} else if (user.getPassword().equals(password)) {
			session.setAttribute("loginUser", user);
			return "backend/user/index";
		} else {
			session.setAttribute("loginInfo", "密码错误");
			return "backend/user/login";
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(String userName, String email, String password,
			String gender, String avatar, String motto, String city) {
		
//		User user = new User(userName, email, password, gender, 0, rank, avatar, "user");
		return "frontend/user/register";
	}

}
