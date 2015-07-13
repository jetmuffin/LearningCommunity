package com.lst.lc.web.backend.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lst.lc.dao.CategoryDao;
import com.lst.lc.dao.DirectionDao;
import com.lst.lc.entities.Admin;
import com.lst.lc.entities.Category;
import com.lst.lc.entities.Direction;

@Controller
@RequestMapping("/manage/category")
public class CategoryController {

	@Autowired
	@Qualifier("categoryDao")
	private CategoryDao categoryDao;

	@Autowired
	@Qualifier("directionDao")
	private DirectionDao directionDao;

	public CategoryController() {
		super();
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		List<Direction> directions = directionDao.getAllDirections();
		model.addAttribute("directions", directions);
		return "backend/category/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpSession session, String name, String description,
			String enabled, int directionId,
			RedirectAttributes redirectAttributes) {
		Admin admin = (Admin) session.getAttribute("admin");
		Direction direction = directionDao.getDirection(directionId);
		Category category = new Category(admin, direction, name, new Date(),
				description, enabled, null);
		categoryDao.addCategory(category);
		redirectAttributes.addFlashAttribute("categoryMsg", "添加分类信息成功");
		return "redirect:/manage/category/categories";
	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public String list(Model model) {
		List<Category> categories = categoryDao.getAllCategories();
		model.addAttribute("categories", categories);
		return "backend/category/list";
	}

	@RequestMapping(value = "/edit/{categoryId}", method = RequestMethod.GET)
	public String edit(@PathVariable int categoryId, Model model) {
		Category category = categoryDao.getCategory(categoryId);
		model.addAttribute("category", category);
		List<Direction> directions = directionDao.getAllDirections();
		model.addAttribute("directions", directions);
		return "backend/category/edit";
	}

	@RequestMapping(value = "/edit/{categoryId}", method = RequestMethod.POST)
	public String edit(@PathVariable int categoryId, String name,
			String description, String enabled, int directionId,
			RedirectAttributes redirectAttributes) {
		categoryDao.update(categoryId, name, description, enabled, directionId);
		redirectAttributes.addFlashAttribute("categoryMsg", "修改分类信息成功");
		return "redirect:/manage/category/categories";
	}

	@RequestMapping(value = "/delete/{categoryId}", method = RequestMethod.GET)
	public String delete(@PathVariable int categoryId,
			RedirectAttributes redirectAttributes) {
		categoryDao.delete(categoryId);
		redirectAttributes.addFlashAttribute("categoryMsg", "删除分类信息成功");
		return "redirect:/manage/category/categories";
	}
	
	@RequestMapping(value = "/direction/{directionId}.json", method = RequestMethod.GET)
	@ResponseBody
	public List<Category> getCategoriesOfDirection(@PathVariable int directionId){
		return categoryDao.getCategoriesOfDirection(directionId);
	}
}
