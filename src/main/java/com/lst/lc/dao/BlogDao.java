package com.lst.lc.dao;

import com.lst.lc.entities.Blog;

/**
 * @author innerac
 *
 */
public interface BlogDao {
	
	/**
	 * 添加Blog
	 * @param blog
	 */
	public void addBlog(Blog blog);
	
	/**
	 * 更新Blog
	 * @param blog
	 */
	public void updateBlog(Blog blog);
	
	/**
	 * 根据ID查找Blog
	 * @param blogId
	 */
	public Blog getBlog(int blogId);
}
