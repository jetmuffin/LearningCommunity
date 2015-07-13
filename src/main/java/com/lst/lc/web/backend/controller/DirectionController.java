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

import com.lst.lc.dao.DirectionDao;
import com.lst.lc.entities.Admin;
import com.lst.lc.entities.Direction;

@Controller
@RequestMapping("/direction")
public class DirectionController {

	@Autowired
	@Qualifier("directionDao")
	private DirectionDao directionDao;

	public DirectionController() {
		super();
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		return "backend/direction/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpSession session, String name, String description,
			String enabled) {
		Admin admin = (Admin) session.getAttribute("admin");
		Direction direction = new Direction(name, admin, new Date(),
				description, enabled, null);
		return "redirect:/direction/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "backend/direction/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String list(Model model) {
		List<Direction> directions = directionDao.getAllDirections();
		model.addAttribute("directions", directions);
		return "redirect:/direction/list";
	}

}
