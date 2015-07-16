package com.lst.lc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lst.lc.dao.BlogDao;
import com.lst.lc.entities.Blog;

/**
 * @author innerac
 *
 */
@Repository("blogDao")
public class BlogDaoImpl extends BaseDao implements BlogDao {

	@Override
	public void addBlog(Blog blog) {
		save(blog);
	}

	@Override
	public void updateBlog(Blog blog) {
		update(blog);
	}

	@Override
	public Blog getBlog(int blogId) {
		return get(Blog.class, blogId);
	}

	@Override
	public void deleteBlog(int blogId) {
		String hql = "delete Blog as blog where blog.blogId = ?";
		Query query = query(hql);
		query.setInteger(0, blogId);
		query.executeUpdate();
	}

	@Override
	public List<Blog> getBlogsOfUser(int userId) {
		String hql = "from Blog as blog where blog.user.userId = ?";
		Query query = query(hql);
		query.setInteger(0, userId);
		return query.list();
	}

}
