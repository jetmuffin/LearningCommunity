package com.lst.lc.web.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lst.lc.dao.BlogDao;
import com.lst.lc.dao.CourseDao;
import com.lst.lc.dao.QuestionDao;
import com.lst.lc.dao.UserDao;
import com.lst.lc.entities.Blog;
import com.lst.lc.entities.Course;
import com.lst.lc.entities.Question;
import com.lst.lc.web.bean.SearchResult;

@Controller
@RequestMapping("/search")
public class SearchController {
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;

	@Autowired
	@Qualifier("blogDao")
	private BlogDao blogDao;

	@Autowired
	@Qualifier("courseDao")
	private CourseDao courseDao;

	@Autowired
	@Qualifier("questionDao")
	private QuestionDao questionDao;

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String add(Model model, String key, String type) {
		SearchResult results;
		if (type.equals("blog")) {
			results = new SearchResult<Blog>("blog", blogDao.search(key));
		} else if (type.equals("question")) {
			results = new SearchResult<Question>("question", questionDao.search(key));
		} else {
			results = new SearchResult<Course>("course", courseDao.search(key));
		}
		model.addAttribute("results", results);
		return "frontend/search/list";
	}

}
