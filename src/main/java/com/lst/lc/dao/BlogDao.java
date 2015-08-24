package com.lst.lc.dao;

import java.util.List;

import com.lst.lc.entities.Blog;
import com.lst.lc.entities.Question;

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
	
	/**
	 * 根据主键删除blog
	 * @param blogId
	 */
	public void deleteBlog(int blogId);
	
	/**
	 * 获得某个用户的博客
	 * @param userId
	 * @return
	 */
	public List<Blog> getBlogsOfUser(int userId);
	
	public List<Blog> getTopFiveRecently();
	
	public void addReadNums(int blogId);
	
	public void addCommentNums(int blogId);
	
	public List<Blog> getOtherBlogs(int userId, int blogId);
	
	public List<Blog> search(String key);
	
	public long count();
	
	public long userCount();
}
