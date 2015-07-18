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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lst.lc.dao.UserDao;
import com.lst.lc.entities.Admin;
import com.lst.lc.entities.User;
import com.lst.lc.web.bean.LoginUser;

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

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public LoginUser login(HttpSession session, String email, String password) {

		User user = userDao.validateUser(email, password);
		LoginUser loginUser;
		String msg = null;
		if (user == null) {
			msg = "邮箱错误";
			loginUser = new LoginUser(0, msg);
		} else if (user.getPassword().equals(password)) {
			msg = "登录成功";
			session.setAttribute("loginUser", user);
			loginUser = new LoginUser(1, msg);
		} else {
			msg = "密码错误";
			loginUser = new LoginUser(0, msg);
		}
		return loginUser;
	}

	@RequestMapping(value = "/index/{userId}", method = RequestMethod.GET)
	public String index(Model model) {

		return "frontend/user/index";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		return "frontend/user/register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(String userName, String email, String password,
			String gender, String avatar, String motto, String city) {

		// User user = new User(userName, email, password, gender, 0, rank,
		// avatar, "user");
		return "frontend/user/register";
	}

}
