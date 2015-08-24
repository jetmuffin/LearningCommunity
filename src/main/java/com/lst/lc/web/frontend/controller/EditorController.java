package com.lst.lc.web.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/editor")
public class EditorController {
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("module", "editor");
		return "frontend/editor/index";
	}
	
	@RequestMapping(value = "/html", method = RequestMethod.GET)
	public String html(Model model) {
		model.addAttribute("module", "editor");
		return "frontend/editor/html";
	}
}
