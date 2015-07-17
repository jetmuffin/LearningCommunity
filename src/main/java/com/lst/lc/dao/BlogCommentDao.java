package com.lst.lc.dao;

import java.util.List;

import com.lst.lc.entities.BlogComment;
import com.lst.lc.web.bean.BComment;

/**
 * @author innerac
 *
 */
public interface BlogCommentDao {

	public void addBlogComment(BlogComment blogComment);
	
	public void updateBlogComment(BlogComment blogComment);
	
	public BlogComment getBlogComment(int commentId);

	List<BComment> getAllBComments(int blogId);

	List<BlogComment> getAllBlogComments(int blogId);
	
}
