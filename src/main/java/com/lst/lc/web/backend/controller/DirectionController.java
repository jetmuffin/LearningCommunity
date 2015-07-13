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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lst.lc.dao.DirectionDao;
import com.lst.lc.entities.Admin;
import com.lst.lc.entities.Direction;

@Controller
@RequestMapping("/manage/direction")
public class DirectionController {

	@Autowired
	@Qualifier("directionDao")
	private DirectionDao directionDao;

	public DirectionController() {
		super();
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		return "backend/direction/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpSession session, String name, String description,
			String enabled, RedirectAttributes redirectAttributes) {
		Admin admin = (Admin) session.getAttribute("admin");
		Direction direction = new Direction(admin, name, new Date(),
				description, enabled);
		directionDao.addDirection(direction);
		
		redirectAttributes.addFlashAttribute("directionMsg", "添加方向信息成功");
		return "redirect:/manage/direction/directions";
	}

	@RequestMapping(value = "/directions", method = RequestMethod.GET)
	public String list(Model model) {
		List<Direction> directions = directionDao.getAllDirections();
		model.addAttribute("directions", directions);
		return "backend/direction/list";
	}
	
	@RequestMapping(value = "/edit/{directionId}", method = RequestMethod.GET)
	public String edit(@PathVariable int directionId, Model model){
		Direction direction = directionDao.getDirection(directionId);
		model.addAttribute("direction", direction);
		return "backend/direction/edit";
	}

	@RequestMapping(value = "/edit/{directionId}", method = RequestMethod.POST)
	public String edit(@PathVariable int directionId, String name, String description,
			String enabled, RedirectAttributes redirectAttributes){
		directionDao.update(directionId, name, description, enabled);
		redirectAttributes.addFlashAttribute("directionMsg", "修改方向信息成功");
		return "redirect:/manage/direction/directions";
	}
	
	@RequestMapping(value = "/delete/{directionId}", method = RequestMethod.GET)
	public String delete(@PathVariable int directionId, RedirectAttributes redirectAttributes){
		directionDao.delete(directionId);
		redirectAttributes.addFlashAttribute("directionMsg", "删除方向信息成功");
		return "redirect:/manage/direction/directions";
	}
	
}
