package com.lst.lc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lst.lc.dao.CategoryDao;
import com.lst.lc.entities.Category;
import com.lst.lc.entities.Course;

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
	public void update(int categoryId, String categoryName, String description, String enabled,
			int directionId) {
		String hql = "update Category as category set category.categoryName = ?, category.description = ?, category.enabled = ?, directionId = ? where categoryId = ?";
		Query query = query(hql);
		query.setString(0, categoryName).setString(1, description)
				.setString(2, enabled).setInteger(3, directionId).setInteger(4, categoryId)
				.executeUpdate();
	}

	@Override
	public void delete(int categoryId) {
		String hql = "delete Category as category where category.categoryId = ?";
		Query query = query(hql);
		query.setInteger(0, categoryId).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getEnabledCategories() {
		String hqlString = "from Category as category where category.enabled=?";
		Query query = query(hqlString);
		query.setString(0, "1");
		List<Category> ts = query.list();
		return ts;
	}
	

}
