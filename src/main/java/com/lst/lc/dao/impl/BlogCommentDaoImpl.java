package com.lst.lc.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lst.lc.dao.BlogCommentDao;
import com.lst.lc.entities.BlogComment;
import com.lst.lc.entities.User;
import com.lst.lc.web.bean.BComment;

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
	
	@Override
	public List<BlogComment> getAllBlogComments(int blogId) {
		String hql = "from BlogComment as blogComment where blogComment.blog.blogId = ?";
		Query query = query(hql);
		query.setInteger(0, blogId);
		return query.list();
	}
	
	@Override
	public List<BComment> getAllBComments(int blogId){
		String hql = "from BlogComment as blogComment where blogComment.blog.blogId = ?";
		Query query = query(hql);
		query.setInteger(0, blogId);
		List<BlogComment> blogComments = query.list();
		List<BComment> comments = new ArrayList<BComment>();
		for(BlogComment blogComment : blogComments){
			comments.add(toBComment(blogComment));
		}
		return comments;
	}

	
	public BComment toBComment(BlogComment blogComment) {
		BComment bComment;
		User user = blogComment.getUser();
		
		Integer commentId = blogComment.getCommentId();
		Date time = blogComment.getTime();
		String content = blogComment.getContent();
		String head = blogComment.getHead();
		Integer userId = user.getUserId();
		String userName = user.getUserName();
		int integral = user.getIntegral();
		String rank = user.getRank();
		String avatar = user.getAvatar();
		bComment = new BComment(commentId, time, content, head, userId, userName, integral, rank, avatar);
		return bComment;
	}
}
