package com.lst.lc.web.service;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lst.lc.dao.QueryDao;
import com.lst.lc.dao.BlogCommentDao;
import com.lst.lc.entities.Blog;
import com.lst.lc.entities.BlogComment;
import com.lst.lc.page.Page;
import com.lst.lc.page.PageHandler;

@Service
public class BlogPageHandler {

	@Autowired
	private PageHandler<Blog> blogPageHandler;
	
	@Autowired
	private PageHandler<BlogComment> commentPageHandler;

	@Autowired
	@Qualifier("queryDao")
	private QueryDao queryDao;

	@Autowired
	@Qualifier("blogCommentDao")
	private BlogCommentDao blogCommentDao;

	public Page<Blog> getBlogs(int pageNum, int pageSize, int type) {
		// 按照回答数量排序,type=1
		String hql1 = "from Blog as blog order by blog.commentNums desc";
		// 按照阅读数量排序,type=2
		String hql2 = "from Blog as blog order by blog.readNums desc";
		// 按照发布时间排序,type=3
		String hql3 = "from Blog as blog order by blog.time desc";
		String hql = null;
		if (type == 1)
			hql = hql1;
		else if (type == 2)
			hql = hql2;
		else
			hql = hql3;
		Query query = queryDao.getQuery(hql);
		Page<Blog> page = blogPageHandler.getPage(pageNum, pageSize,
				Blog.class, query);
		return page;
	}

	public Page<BlogComment> getComments(int blogId, int pageNum,
			int pageSize) {

		String hql = "from BlogComment as blogComment where blogComment.blog.blogId = ?";
		Query query = queryDao.getQuery(hql);
		query.setInteger(0, blogId);
		Page<BlogComment> page = commentPageHandler.getPage(pageNum, pageSize,
				BlogComment.class, query);
		return page;
	}
}
