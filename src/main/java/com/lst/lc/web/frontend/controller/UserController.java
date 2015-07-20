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
import com.lst.lc.utils.EncryptUtils;
import com.lst.lc.web.bean.StatusMessage;
import com.lst.lc.web.service.LogHandler;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
	
	@Autowired
	private LogHandler logHandler;

	public UserController() {
		super();
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "frontend/user/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public StatusMessage login(HttpSession session, String email,
			String password) throws Exception {
		password = EncryptUtils.encryptMD5(password.getBytes());
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
			//写进日志，积分加1
			logHandler.toLog(user, "登录网站");
			logHandler.updateIntegral(user.getUserId(), "login");
			
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
	public StatusMessage register(String userName, String email,
			String password, String captcha, HttpSession session) throws Exception {

		StatusMessage statusMessage;
		String message = null;

		// 先判断邮箱是否已经被注册
		if (userDao.ifEmailExisted(email)) {
			message = "该邮箱已被注册";
			statusMessage = new StatusMessage(0, message);
		}
		// 判断验证码
		else if (!captcha.equals(session.getAttribute("validationCode"))) {
			message = "验证码错误";
			statusMessage = new StatusMessage(0, message);
		} else {
			User user = new User(userName, email, EncryptUtils.encryptMD5(password.getBytes()), "未知", 10, "菜鸟",
					"", "user");
			userDao.addUser(user);
			message = "注册成功，您已登录";
			statusMessage = new StatusMessage(1, message);
			session.setAttribute("loginUser", user);
		}
		return statusMessage;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session,RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("logoutMsg", "退出成功");
		session.removeAttribute("loginUser");
		return "redirect:/user/login";
	}
	
	@RequestMapping(value = "/completeInfo", method = RequestMethod.GET)
	public String completeInfo(Model model,HttpSession session){
		return "frontend/user/complete";
	}
}
