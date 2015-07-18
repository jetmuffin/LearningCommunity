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
import com.lst.lc.web.bean.StatusMessage;

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
	public StatusMessage login(HttpSession session, String email, String password) {

		User user = userDao.validateUser(email, password);
		StatusMessage statusMessage;
		String message = null;
		if (user == null) {
			message = "邮箱错误";
			statusMessage = new StatusMessage(0, message);
		} else if (user.getPassword().equals(password)) {
			message = "登录成功";
			session.setAttribute("loginUser", user);
			statusMessage = new StatusMessage(1, message);
		} else {
			message = "密码错误";
			statusMessage = new StatusMessage(0, message);
		}
		return statusMessage;
	}

	@RequestMapping(value = "/index/{userId}", method = RequestMethod.GET)
	public String index(Model model) {

		return "frontend/user/index";
	}

	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public StatusMessage register(String userName, String email, String password,
			String captcha,HttpSession session) {
		
		System.out.println(session.getAttribute("validationCode"));
		System.out.println(captcha);
		//TODO 验证邮箱是否存在
		//User user = new User(userName, email, password, "男", 0, "0", " ", "1");
		//userDao.addUser(user);
		String message = "注册成功";
		StatusMessage statusMessage = new StatusMessage(1, message);
		return statusMessage;
	}
}
