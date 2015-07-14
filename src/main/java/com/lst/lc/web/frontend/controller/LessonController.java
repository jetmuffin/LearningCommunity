package com.lst.lc.web.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lst.lc.entities.Course;
import com.lst.lc.page.Page;

@Controller
@RequestMapping("/course")
public class LessonController {

	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public String listCourse(Model model, String pageNum) {
		return "frontend/course/list";
	}
}
