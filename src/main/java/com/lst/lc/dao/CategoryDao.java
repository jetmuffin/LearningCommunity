package com.lst.lc.dao;

import java.util.List;

import com.lst.lc.entities.Category;
import com.lst.lc.entities.Direction;
import com.lst.lc.web.bean.CategoryBean;

public interface CategoryDao {

	public void addCategory(Category category);
	
	public Category getCategory(int id);
	
	public List<Category> getAllCategories();

	public void update(int categoryId, String categoryName, String description, String enabled, int directionId);

	public void delete(int categoryId);
	
	public List<CategoryBean> getCategoriesOfDirection(int directionId);
}
