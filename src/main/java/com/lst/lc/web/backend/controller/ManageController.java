package com.lst.lc.web.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/manage")
public class ManageController {

	public ManageController() {
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
}
