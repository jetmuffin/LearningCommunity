package com.lst.lc.dao.impl;

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

}
