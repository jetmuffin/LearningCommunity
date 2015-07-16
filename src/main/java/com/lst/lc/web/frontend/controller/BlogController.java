package com.lst.lc.web.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lst.lc.dao.BlogDao;

@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	@Qualifier("blogDao")
	private BlogDao blogDao;
	
	@RequestMapping(value = "/blogs", method = RequestMethod.GET)
	public String list(){
		
		return null;
	}

}
