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
	public Category getCategory(int id) {
		return get(Category.class, id);
	}

	@Override
	public List<Category> getAllCategories() {
		return getAll("Category");
	}
	
	@Override
	public void update(Category category){
		saveOrUpdate(category);
	}
	
	@Override
	public void delete(Category category){
		delete(category);
	}
	
}
