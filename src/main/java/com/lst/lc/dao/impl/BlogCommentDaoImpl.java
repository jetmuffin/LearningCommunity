package com.lst.lc.dao.impl;

import org.springframework.stereotype.Repository;

import com.lst.lc.dao.BlogCommentDao;
import com.lst.lc.entities.BlogComment;

/**
 * @author innerac
 *
 */
@Repository("blogCommentDao")
public class BlogCommentDaoImpl extends BaseDao implements BlogCommentDao {

	@Override
	public void addBlogComment(BlogComment blogComment) {
		save(blogComment);

	}

	@Override
	public void updateBlogComment(BlogComment blogComment) {
		update(blogComment);

	}

	@Override
	public BlogComment getBlogComment(int commentId) {
		return get(BlogComment.class, commentId);
	}

}
