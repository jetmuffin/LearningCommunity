package com.lst.lc.entities;

// Generated 2015-7-13 13:03:22 by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Blog generated by hbm2java
 */
@Entity
@Table(name = "blog", catalog = "LearningCommunity")
public class Blog implements java.io.Serializable {

	private Integer blogId;
	private User user;
	private String title;
	private String content;
	private Date time;
	private int commentNums;
	private int readNums;
	private String tag;
	private Set<BlogComment> blogComments = new HashSet<BlogComment>(0);

	public Blog() {
	}

	public Blog(User user, String title, String content, Date time,
			int commentNums, int readNums) {
		this.user = user;
		this.title = title;
		this.content = content;
		this.time = time;
		this.commentNums = commentNums;
		this.readNums = readNums;
	}

	public Blog(User user, String title, String content, Date time,
			int commentNums, int readNums, String tag,
			Set<BlogComment> blogComments) {
		this.user = user;
		this.title = title;
		this.content = content;
		this.time = time;
		this.commentNums = commentNums;
		this.readNums = readNums;
		this.tag = tag;
		this.blogComments = blogComments;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "blogId", unique = true, nullable = false)
	public Integer getBlogId() {
		return this.blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "authorId", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "title", nullable = false, length = 200)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", nullable = false, length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time", nullable = false, length = 19)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "commentNums", nullable = false)
	public int getCommentNums() {
		return this.commentNums;
	}

	public void setCommentNums(int commentNums) {
		this.commentNums = commentNums;
	}

	@Column(name = "readNums", nullable = false)
	public int getReadNums() {
		return this.readNums;
	}

	public void setReadNums(int readNums) {
		this.readNums = readNums;
	}

	@Column(name = "tag", length = 200)
	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "blog")
	public Set<BlogComment> getBlogComments() {
		return this.blogComments;
	}

	public void setBlogComments(Set<BlogComment> blogComments) {
		this.blogComments = blogComments;
	}

}
