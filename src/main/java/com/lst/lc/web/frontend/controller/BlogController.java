package com.lst.lc.web.frontend.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.swing.text.StyledEditorKit.BoldAction;

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
import com.lst.lc.entities.BlogComment;
import com.lst.lc.entities.User;
import com.lst.lc.web.bean.BComment;

@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	@Qualifier("blogDao")
	private BlogDao blogDao;
	
	@Autowired
	@Qualifier("blogCommentDao")
	private BlogCommentDao blogCommentDao;
	
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
	public String detail(Model model, @PathVariable int blogId){
		Blog blog = blogDao.getBlog(blogId);
		//获取blog放进model，名字叫blog
		//获取该blog的评论放进取，评论也需要封装一些，用web.bean下面的BComment，名字叫comments
		List<BComment> comments = blogCommentDao.getAllBComments(blogId);
		
		model.addAttribute("blog", blog);
		model.addAttribute("comments", comments);
		
		return "frontend/blog/view";
	}

}
