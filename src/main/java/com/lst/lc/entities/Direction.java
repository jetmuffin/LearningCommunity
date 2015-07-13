package com.lst.lc.entities;

// Generated 2015-7-13 13:03:22 by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Direction generated by hbm2java
 */
@Entity
@Table(name = "direction", catalog = "LearningCommunity")
public class Direction implements java.io.Serializable {

	private String directionName;
	private Admin admin;
	private Date time;
	private String description;
	private Set<Course> courses = new HashSet<Course>(0);

	public Direction() {
	}

	public Direction(String directionName, Admin admin, Date time) {
		this.directionName = directionName;
		this.admin = admin;
		this.time = time;
	}

	public Direction(String directionName, Admin admin, Date time,
			String description, Set<Course> courses) {
		this.directionName = directionName;
		this.admin = admin;
		this.time = time;
		this.description = description;
		this.courses = courses;
	}

	@Id
	@Column(name = "directionName", unique = true, nullable = false, length = 20)
	public String getDirectionName() {
		return this.directionName;
	}

	public void setDirectionName(String directionName) {
		this.directionName = directionName;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "adminId", nullable = false)
	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time", nullable = false, length = 19)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "direction")
	public Set<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

}
