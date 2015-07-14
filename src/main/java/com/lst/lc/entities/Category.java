package com.lst.lc.entities;

// Generated 2015-7-14 10:29:11 by Hibernate Tools 4.3.1

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
 * Category generated by hbm2java
 */
@Entity
@Table(name = "category", catalog = "LearningCommunity")
public class Category implements java.io.Serializable {

	private Integer categoryId;
	private Admin admin;
	private Direction direction;
	private String categoryName;
	private Date time;
	private String description;
	private String enabled;
	private Set<Course> courses = new HashSet<Course>(0);

	public Category() {
	}

	public Category(Admin admin, Direction direction, String categoryName,
			Date time, String description, String enabled) {
		this.admin = admin;
		this.direction = direction;
		this.categoryName = categoryName;
		this.time = time;
		this.description = description;
		this.enabled = enabled;
	}

	public Category(Admin admin, Direction direction, String categoryName,
			Date time, String description, String enabled, Set<Course> courses) {
		this.admin = admin;
		this.direction = direction;
		this.categoryName = categoryName;
		this.time = time;
		this.description = description;
		this.enabled = enabled;
		this.courses = courses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "categoryId", unique = true, nullable = false)
	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "adminId", nullable = false)
	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "directionId", nullable = false)
	public Direction getDirection() {
		return this.direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	@Column(name = "categoryName", nullable = false, length = 20)
	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time", nullable = false, length = 19)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "description", nullable = false, length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "enabled", nullable = false, length = 10)
	public String getEnabled() {
		return this.enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

}
