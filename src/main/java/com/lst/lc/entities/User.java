package com.lst.lc.entities;

// Generated 2015-7-13 13:03:22 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "LearningCommunity")
public class User implements java.io.Serializable {

	private Integer userId;
	private String userName;
	private String email;
	private String password;
	private String gender;
	private int integral;
	private String rank;
	private String avatar;
	private String type;
	private String motto;
	private String city;
	private Set<BlogComment> blogComments = new HashSet<BlogComment>(0);
	private Set<RelUserRoute> relUserRoutes = new HashSet<RelUserRoute>(0);
	private Set<QuestionAnswer> questionAnswers = new HashSet<QuestionAnswer>(0);
	private Set<Question> questions = new HashSet<Question>(0);
	private Set<LessonComment> lessonComments = new HashSet<LessonComment>(0);
	private Set<Blog> blogs = new HashSet<Blog>(0);
	private RelUserCourse relUserCourse;

	public User() {
	}

	public User(String userName, String email, String password, String gender,
			int integral, String rank, String avatar, String type) {
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.integral = integral;
		this.rank = rank;
		this.avatar = avatar;
		this.type = type;
	}

	public User(String userName, String email, String password, String gender,
			int integral, String rank, String avatar, String type,
			String motto, String city, Set<BlogComment> blogComments,
			Set<RelUserRoute> relUserRoutes,
			Set<QuestionAnswer> questionAnswers, Set<Question> questions,
			Set<LessonComment> lessonComments, Set<Blog> blogs,
			RelUserCourse relUserCourse) {
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.integral = integral;
		this.rank = rank;
		this.avatar = avatar;
		this.type = type;
		this.motto = motto;
		this.city = city;
		this.blogComments = blogComments;
		this.relUserRoutes = relUserRoutes;
		this.questionAnswers = questionAnswers;
		this.questions = questions;
		this.lessonComments = lessonComments;
		this.blogs = blogs;
		this.relUserCourse = relUserCourse;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "userId", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "userName", nullable = false, length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "email", nullable = false, length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = false, length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "gender", nullable = false, length = 10)
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "integral", nullable = false)
	public int getIntegral() {
		return this.integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	@Column(name = "rank", nullable = false, length = 10)
	public String getRank() {
		return this.rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	@Column(name = "avatar", nullable = false, length = 100)
	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Column(name = "type", nullable = false, length = 10)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "motto", length = 65535)
	public String getMotto() {
		return this.motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	@Column(name = "city", length = 50)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<BlogComment> getBlogComments() {
		return this.blogComments;
	}

	public void setBlogComments(Set<BlogComment> blogComments) {
		this.blogComments = blogComments;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<RelUserRoute> getRelUserRoutes() {
		return this.relUserRoutes;
	}

	public void setRelUserRoutes(Set<RelUserRoute> relUserRoutes) {
		this.relUserRoutes = relUserRoutes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<QuestionAnswer> getQuestionAnswers() {
		return this.questionAnswers;
	}

	public void setQuestionAnswers(Set<QuestionAnswer> questionAnswers) {
		this.questionAnswers = questionAnswers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<LessonComment> getLessonComments() {
		return this.lessonComments;
	}

	public void setLessonComments(Set<LessonComment> lessonComments) {
		this.lessonComments = lessonComments;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Blog> getBlogs() {
		return this.blogs;
	}

	public void setBlogs(Set<Blog> blogs) {
		this.blogs = blogs;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	public RelUserCourse getRelUserCourse() {
		return this.relUserCourse;
	}

	public void setRelUserCourse(RelUserCourse relUserCourse) {
		this.relUserCourse = relUserCourse;
	}

}
