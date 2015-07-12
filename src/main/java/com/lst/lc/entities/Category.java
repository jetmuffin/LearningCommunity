package com.lst.lc.entities;

// Generated 2015-7-12 13:28:32 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Category generated by hbm2java
 */
@Entity
@Table(name = "category", catalog = "LearningCommunity")
public class Category implements java.io.Serializable {

	private String categoryName;
	private Set<Course> courses = new HashSet<Course>(0);

	public Category() {
	}

	public Category(String categoryName) {
		this.categoryName = categoryName;
	}

	public Category(String categoryName, Set<Course> courses) {
		this.categoryName = categoryName;
		this.courses = courses;
	}

	@Id
	@Column(name = "categoryName", unique = true, nullable = false, length = 20)
	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

}