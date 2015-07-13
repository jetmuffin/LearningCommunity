package com.lst.lc.dao;

import java.util.List;

import com.lst.lc.entities.Category;

public interface CategoryDao {

	public void addCategory(Category category);
	
	public Category getCategory(int id);
	
	public List<Category> getAllCategories();
}
