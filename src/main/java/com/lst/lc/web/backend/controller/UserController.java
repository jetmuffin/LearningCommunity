package com.lst.lc.web.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class UserController {
	
	@Controller
	@RequestMapping("/manage/user")
	public class AdminController {

		public AdminController() {
			super();
		}
		
		@RequestMapping(value = "/login", method = RequestMethod.GET)
		public String login(Model model) {
			return "backend/admin/login";
		}
		
		@RequestMapping(value = "/index", method = RequestMethod.GET)
		public String index(Model model) {
			return "backend/index/index";
		}
	}
}
