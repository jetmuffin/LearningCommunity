package com.lst.lc.entities;

// Generated 2015-7-13 19:59:48 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Admin generated by hbm2java
 */
@Entity
@Table(name = "admin", catalog = "LearningCommunity")
public class Admin implements java.io.Serializable {

	private Integer adminId;
	private String name;
	private String email;
	private String password;
	private Set<Direction> directions = new HashSet<Direction>(0);
	private Set<Category> categories = new HashSet<Category>(0);

	public Admin() {
	}

	public Admin(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public Admin(String name, String email, String password,
			Set<Direction> directions, Set<Category> categories) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.directions = directions;
		this.categories = categories;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "adminId", unique = true, nullable = false)
	public Integer getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "admin")
	public Set<Direction> getDirections() {
		return this.directions;
	}

	public void setDirections(Set<Direction> directions) {
		this.directions = directions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "admin")
	public Set<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

}
