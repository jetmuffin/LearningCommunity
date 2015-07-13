package com.lst.lc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lst.lc.dao.CategoryDao;
import com.lst.lc.entities.Category;

@Repository("categoryDao")
public class CategoryDaoImpl extends BaseDao implements CategoryDao {

	@Override
	public void addCategory(Category category) {
		save(category);
	}

	@Override
	public Category getCategory(String name) {
		return get(Category.class, name);
	}

	@Override
	public List<Category> getAllCategories() {
		return getAll("Category");
	}

}
