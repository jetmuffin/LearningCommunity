package com.lst.lc.web.bean;

public class CourseTag {
	
	private Integer id;
	private String name;
	private boolean isActive;
	
	public CourseTag() {
		super();
	}
	
	public CourseTag(Integer id, String name, boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.isActive = isActive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "CourseTag [id=" + id + ", name=" + name + ", isActive="
				+ isActive + "]";
	}
	
}
