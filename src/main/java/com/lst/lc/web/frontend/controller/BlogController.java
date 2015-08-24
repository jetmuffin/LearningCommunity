package com.lst.lc.web.frontend.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.lst.lc.dao.BlogTagDao;
import com.lst.lc.dao.UserDao;
import com.lst.lc.entities.Blog;
import com.lst.lc.entities.BlogComment;
import com.lst.lc.entities.BlogTag;
import com.lst.lc.entities.Question;
import com.lst.lc.entities.QuestionAnswer;
import com.lst.lc.entities.User;
import com.lst.lc.utils.StringUtils;
import com.lst.lc.web.service.BlogPageHandler;
import com.lst.lc.web.service.LogHandler;

@Controller
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	@Qualifier("blogDao")
	private BlogDao blogDao;
	
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;

	@Autowired
	@Qualifier("blogTagDao")
	private BlogTagDao blogTagDao;

	@Autowired
	@Qualifier("blogCommentDao")
	private BlogCommentDao blogCommentDao;

	@Autowired
	@Qualifier("blogPageHandler")
	private BlogPageHandler blogPageHandler;
	
	@Autowired
	private LogHandler logHandler;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("module", "blog");
		return "frontend/blog/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Model model, String title, String tag, String content,
			RedirectAttributes redirectAttributes, HttpSession session) {
		User user = (User) session.getAttribute("loginUser");

		Set<BlogTag> tagSet = new HashSet<BlogTag>();
		List<String> tags = StringUtils.stringSplit(tag);
		for (int i = 0; i < tags.size(); i++) {
			BlogTag blogTag = blogTagDao.getTagByName(tags.get(i));
			if (blogTag == null) {
				blogTag = new BlogTag(tags.get(i), 1);
				blogTagDao.addTag(blogTag);
			} else {
				// 数量加１
				int number = blogTag.getNumber() + 1;
				blogTag.setNumber(number);
			}
			tagSet.add(blogTag);
		}

		Blog blog = new Blog(user, title, content, new Date(), 0, 0, tag,
				tagSet, null);
		blogDao.addBlog(blog);
		//写入日志，用户加积分
		logHandler.toLog(user, "发布博客:"+blog.getBlogId());
		logHandler.updateIntegral(user.getUserId(), "addBlog");
		
		redirectAttributes.addFlashAttribute("blogMsg", "博客发布成功");
		return "redirect:/blog/view/" + blog.getBlogId();
	}

	@RequestMapping(value = "/view/{blogId}", method = RequestMethod.GET)
	public String detail(Model model, @PathVariable int blogId, String pageNum,
			String pageSize) {

		int pageNow = 1;
		int pagesize = 10;
		if (pageSize != null) {
			pagesize = Integer.valueOf(pageSize);
		}
		if (pageSize != null) {
			pageNow = Integer.valueOf(pageNum);
		}
		Blog blog = blogDao.getBlog(blogId);
		blogDao.addReadNums(blogId);
		List<Blog> blogs = blogDao.getOtherBlogs(blog.getUser().getUserId(), blogId);
		model.addAttribute("blog", blog);
		model.addAttribute("otherBlogs", blogs);
		model.addAttribute("comments",
				blogPageHandler.getComments(blogId, pageNow, pagesize));
		model.addAttribute("module", "blog");
		return "frontend/blog/view";
	}

	/**
	 * 博客列表
	 * 
	 * @param model
	 * @param pageNum
	 * @param pageSize
	 * @param type　排序类型，取指1,2,3，１表示按照回答数排序，2表示按照阅读数排序，3表示按照时间排序
	 * @return
	 */
	@RequestMapping(value = "/blogs", method = RequestMethod.GET)
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
		
		List<User> users = userDao.getTopFive();
		model.addAttribute("users", users);
		List<BlogTag> tags = blogTagDao.getTagsOrderByNum();
		model.addAttribute("tags", tags);
		model.addAttribute("page",
				blogPageHandler.getBlogs(pageNow, pagesize, sorttype));
		model.addAttribute("module", "blog");
		return "frontend/blog/list";
	}

	@RequestMapping(value = "/edit/{blogId}", method = RequestMethod.GET)
	public String edit(@PathVariable int blogId, Model model) {
		Blog blog = blogDao.getBlog(blogId);
		model.addAttribute("blog", blog);
		model.addAttribute("module", "blog");
		return "frontend/question/edit";
	}
	
	
	@RequestMapping(value = "/comment/{blogId}", method = RequestMethod.POST)
	public String comment(Model model, @PathVariable int blogId, String head, String content,
			HttpSession session, RedirectAttributes redirectAttributes) {
		
		User user = (User) session.getAttribute("loginUser");
		Blog blog = blogDao.getBlog(blogId);
		BlogComment comment = new BlogComment(blog, user, new Date(), content, head);
		blogCommentDao.addBlogComment(comment);
		blogDao.addCommentNums(blogId);
		//写入日志
		logHandler.toLog(user, "回复了博客:"+ blogId);
		logHandler.updateIntegral(user.getUserId(), "commentBlog");
		
		redirectAttributes.addFlashAttribute("blogMsg", "评论成功");
		return "redirect:/blog/view/" + blogId;
	}
}
