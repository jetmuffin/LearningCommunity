package com.lst.lc.web.frontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lst.lc.dao.CategoryDao;
import com.lst.lc.dao.CourseDao;
import com.lst.lc.dao.DirectionDao;
import com.lst.lc.entities.Category;
import com.lst.lc.entities.Course;
import com.lst.lc.entities.Direction;
import com.lst.lc.page.Page;

@Controller
@RequestMapping("/course")
public class LessonController {

	@Autowired
	@Qualifier("categoryDao")
	private CategoryDao categoryDao;

	@Autowired
	@Qualifier("directionDao")
	private DirectionDao directionDao;

	@Autowired
	@Qualifier("courseDao")
	private CourseDao courseDao;
	
	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public String listCourse(Model model, String pageNum) {
		List<Direction> directions = directionDao.getEnabledDirections();
		List<Category> categories = categoryDao.getEnabledCategories();
		
		model.addAttribute("directions", directions);
		model.addAttribute("categories",categories);
		return "frontend/course/list";
	}
}
