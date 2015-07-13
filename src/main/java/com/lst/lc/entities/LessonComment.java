package com.lst.lc.entities;

// Generated 2015-7-13 13:31:58 by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * LessonComment generated by hbm2java
 */
@Entity
@Table(name = "lessonComment", catalog = "LearningCommunity")
public class LessonComment implements java.io.Serializable {

	private Integer commentId;
	private CourseLesson courseLesson;
	private User user;
	private String content;
	private Date time;
	private String head;

	public LessonComment() {
	}

	public LessonComment(CourseLesson courseLesson, User user, String content,
			Date time) {
		this.courseLesson = courseLesson;
		this.user = user;
		this.content = content;
		this.time = time;
	}

	public LessonComment(CourseLesson courseLesson, User user, String content,
			Date time, String head) {
		this.courseLesson = courseLesson;
		this.user = user;
		this.content = content;
		this.time = time;
		this.head = head;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "commentId", unique = true, nullable = false)
	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lessonId", nullable = false)
	public CourseLesson getCourseLesson() {
		return this.courseLesson;
	}

	public void setCourseLesson(CourseLesson courseLesson) {
		this.courseLesson = courseLesson;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "authorId", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
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

	@Column(name = "head", length = 200)
	public String getHead() {
		return this.head;
	}

	public void setHead(String head) {
		this.head = head;
	}

}
