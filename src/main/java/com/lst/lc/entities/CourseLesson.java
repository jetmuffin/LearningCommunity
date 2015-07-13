package com.lst.lc.entities;

// Generated 2015-7-13 14:13:10 by Hibernate Tools 4.3.1

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

/**
 * CourseLesson generated by hbm2java
 */
@Entity
@Table(name = "courseLesson", catalog = "LearningCommunity")
public class CourseLesson implements java.io.Serializable {

	private Integer lessonId;
	private Course course;
	private String title;
	private String summary;
	private String type;
	private String videoUrl;
	private String content;
	private Set<LessonComment> lessonComments = new HashSet<LessonComment>(0);

	public CourseLesson() {
	}

	public CourseLesson(Course course, String title, String summary, String type) {
		this.course = course;
		this.title = title;
		this.summary = summary;
		this.type = type;
	}

	public CourseLesson(Course course, String title, String summary,
			String type, String videoUrl, String content,
			Set<LessonComment> lessonComments) {
		this.course = course;
		this.title = title;
		this.summary = summary;
		this.type = type;
		this.videoUrl = videoUrl;
		this.content = content;
		this.lessonComments = lessonComments;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "lessonId", unique = true, nullable = false)
	public Integer getLessonId() {
		return this.lessonId;
	}

	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "courseId", nullable = false)
	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Column(name = "title", nullable = false, length = 200)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "summary", nullable = false, length = 65535)
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Column(name = "type", nullable = false, length = 10)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "videoUrl", length = 200)
	public String getVideoUrl() {
		return this.videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	@Column(name = "content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "courseLesson")
	public Set<LessonComment> getLessonComments() {
		return this.lessonComments;
	}

	public void setLessonComments(Set<LessonComment> lessonComments) {
		this.lessonComments = lessonComments;
	}

}
