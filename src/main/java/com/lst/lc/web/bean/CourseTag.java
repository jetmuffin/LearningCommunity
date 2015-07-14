package com.lst.lc.web.bean;

public class CourseTag {
	
	private String name;
	private boolean isActive;
	
	public CourseTag() {
		super();
	}
	
	public CourseTag(String name, boolean isActive) {
		super();
		this.name = name;
		this.isActive = isActive;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}
