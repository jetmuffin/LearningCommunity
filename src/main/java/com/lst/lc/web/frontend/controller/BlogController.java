package com.lst.lc.web.frontend.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lst.lc.dao.BlogDao;

@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	@Qualifier("blogDao")
	private BlogDao blogDao;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		return "frontend/blog/add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Model model, String title, String tag, String content,RedirectAttributes redirectAttributes, HttpSession session){
		
		//添加博客的方法
		
		//然后blog放在model里面，名字叫blog
		
		//一般删除更新添加操作，要给结果信息
		redirectAttributes.addFlashAttribute("blogMsg", "博客发布成功");
		return "frontend/blog/view";
	}
	
	
	@RequestMapping(value = "/view/{blogId}", method = RequestMethod.GET)
	public String detail(Model model, int blogId){
		
		//获取blog放进model，名字叫blog
		//获取该blog的评论放进取，评论也需要封装一些，用web.bean下面的BComment，名字叫comments
		
		return "frontend/blog/view";
	}

}
