package com.lst.lc.web.backend.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lst.lc.dao.CategoryDao;
import com.lst.lc.entities.Admin;
import com.lst.lc.entities.Category;


@Controller
@RequestMapping("/manage/category")
public class CategoryController {

	@Autowired
	@Qualifier("categoryDao")
	private CategoryDao categoryDao;

	public CategoryController() {
		super();
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		return "backend/category/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpSession session, String name, String description,
			String enabled) {
		Admin admin = (Admin) session.getAttribute("admin");
		Category category = new Category(admin, name, new Date(),
				description, enabled);
		categoryDao.addCategory(category);
		return "redirect:/manage/category/categories";
	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public String list(Model model) {
		List<Category> categories = categoryDao.getAllCategories();
		model.addAttribute("categoryies", categories);
		return "backend/category/list";
	}
	
	

}
