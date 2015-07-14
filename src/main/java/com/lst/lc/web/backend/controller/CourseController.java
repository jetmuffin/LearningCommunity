package com.lst.lc.web.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.lst.lc.dao.CategoryDao;
import com.lst.lc.dao.CourseDao;
import com.lst.lc.dao.DirectionDao;
import com.lst.lc.entities.Category;
import com.lst.lc.entities.Course;
import com.lst.lc.entities.Direction;
import com.lst.lc.page.Page;
import com.lst.lc.page.PageHandler;

@Controller
@RequestMapping("/manage/course")
public class CourseController {

	@Autowired
	@Qualifier("categoryDao")
	private CategoryDao categoryDao;

	@Autowired
	@Qualifier("directionDao")
	private DirectionDao directionDao;

	@Autowired
	@Qualifier("courseDao")
	private CourseDao courseDao;

	@Autowired
	private PageHandler<Course> pageHandler;

	public CourseController() {
		super();
	}

	@RequestMapping(value = "/courses/{pageNum}/{pageSize}", method = RequestMethod.GET)
	public String listCourse(Model model, @PathVariable long pageNum, @PathVariable long pageSize) {
		int pageNow = (int) pageNum;
		int pagesize = (int) pageSize;
		System.out.println(pageNow + pagesize);
		Page<Course> page = pageHandler
				.getPage(pageNow, pagesize, Course.class);
		model.addAttribute("module", "course");
		model.addAttribute("page", page);
		return "backend/course/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		List<Direction> directions = directionDao.getEnabledDirections();
		model.addAttribute("directions", directions);
		return "backend/course/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(String title, String description, String difficulty,
			String imageUrl, int categoryId, int directionId,
			MultipartFile image) {
		System.out.println(categoryId);
		System.out.println(directionId);
		System.out.println(image.getOriginalFilename());
		return null;
	}
}
