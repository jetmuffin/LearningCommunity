package com.lst.lc.web.frontend.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lst.lc.dao.BlogCommentDao;
import com.lst.lc.dao.BlogDao;
import com.lst.lc.entities.Blog;
import com.lst.lc.entities.Question;
import com.lst.lc.entities.User;
import com.lst.lc.web.service.BlogPageHandler;

@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	@Qualifier("blogDao")
	private BlogDao blogDao;
	
	@Autowired
	@Qualifier("blogCommentDao")
	private BlogCommentDao blogCommentDao;
	
	@Autowired
	@Qualifier("blogPageHandler")
	private BlogPageHandler blogPageHandler;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		return "frontend/blog/add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Model model, String title, String tag, String content,RedirectAttributes redirectAttributes, HttpSession session){
		User user = (User) session.getAttribute("user");
		Blog blog = new Blog(user, title, content, new Date(), 0, 0);
		model.addAttribute("blog", blog);
		blogDao.addBlog(blog);
		redirectAttributes.addFlashAttribute("blogMsg", "博客发布成功");
		return "redirect:/blog/view/"+blog.getBlogId();
	}
	
	
	@RequestMapping(value = "/view/{blogId}", method = RequestMethod.GET)
	public String detail(Model model, @PathVariable int blogId, String pageNum, String pageSize){
		
		int pageNow = 1;
		int pagesize = 10;
		if (pageSize != null) {
			pagesize = Integer.valueOf(pageSize);
		}
		if (pageSize != null) {
			pageNow = Integer.valueOf(pageNum);
		}
		Blog blog = blogDao.getBlog(blogId);
		model.addAttribute("blog", blog);
		model.addAttribute("comments", blogPageHandler.getComments(blogId, pageNow, pagesize));
		return "frontend/blog/view";
	}
	
	/**
	 * 博客列表
	 * @param model
	 * @param pageNum
	 * @param pageSize
	 * @param type　排序类型，取指1,2,3，１表示按照回答数排序，2表示按照阅读数排序，3表示按照时间排序
	 * @return
	 */
	@RequestMapping(value = "/blos", method = RequestMethod.GET)
	public String list(Model model, String pageNum, String pageSize, String type) {
		int pageNow = 1;
		int pagesize = 10;
		int sorttype = 1;
		if (pageSize != null) {
			pagesize = Integer.valueOf(pageSize);
		}
		if (pageSize != null) {
			pageNow = Integer.valueOf(pageNum);
		}
		if (type != null) {
			sorttype = Integer.valueOf(type);
		}
		model.addAttribute("page",
				blogPageHandler.getBlogs(pageNow, pagesize, sorttype));

		return "frontend/blog/list";
	}

	@RequestMapping(value = "/edit/{blogId}", method = RequestMethod.GET)
	public String edit(@PathVariable int blogId, Model model) {
		Blog blog = blogDao.getBlog(blogId);
		model.addAttribute("blog", blog);
		return "frontend/question/edit";
	}
}
