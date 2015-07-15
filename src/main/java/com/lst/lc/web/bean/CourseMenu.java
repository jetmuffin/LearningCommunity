package com.lst.lc.web.bean;

import java.util.ArrayList;
import java.util.List;

import com.lst.lc.entities.Course;
import com.lst.lc.page.Page;

public class CourseMenu {
	
	private List<CourseTag> directionTags = new ArrayList<CourseTag>();
	private List<CourseTag> categoryTags = new ArrayList<CourseTag>();
	private List<CourseTag> difficultyTags = new ArrayList<CourseTag>();
	private Page<Course> page;
	public List<CourseTag> getDirectionTags() {
		return directionTags;
	}
	public void setDirectionTags(List<CourseTag> directionTags) {
		this.directionTags = directionTags;
	}
	public List<CourseTag> getCategoryTags() {
		return categoryTags;
	}
	public void setCategoryTags(List<CourseTag> categoryTags) {
		this.categoryTags = categoryTags;
	}
	public List<CourseTag> getDifficultyTags() {
		return difficultyTags;
	}
	public void setDifficultyTags(List<CourseTag> difficultyTags) {
		this.difficultyTags = difficultyTags;
	}
	public Page<Course> getPage() {
		return page;
	}
	public void setPage(Page<Course> page) {
		this.page = page;
	}
	@Override
	public String toString() {
		return "CourseMenu [directionTags=" + directionTags + ", categoryTags="
				+ categoryTags + ", difficultyTags=" + difficultyTags
				+ ", page=" + page + "]";
	}
}
