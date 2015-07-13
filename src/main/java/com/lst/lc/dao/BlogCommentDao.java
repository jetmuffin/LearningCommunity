package com.lst.lc.dao;

import com.lst.lc.entities.BlogComment;

/**
 * @author innerac
 *
 */
public interface BlogCommentDao {

	public void addBlogComment(BlogComment blogComment);
	
	public void updateBlogComment(BlogComment blogComment);
	
	public BlogComment getBlogComment(int commentId);
	
}
