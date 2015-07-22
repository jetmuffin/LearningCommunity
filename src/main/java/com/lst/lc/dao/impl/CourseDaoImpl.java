package com.lst.lc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.lst.lc.dao.CourseDao;
import com.lst.lc.entities.Course;

@Repository("courseDao")
public class CourseDaoImpl extends BaseDao implements CourseDao {

	@Override
	public void addCourse(Course course) {
		save(course);
	}

	@Override
	public Course getCourse(int id) {
		return get(Course.class, id);
	}

	@Override
	public void updateCourse(Course course) {
		update(course);
	}

	@Override
	public List<Course> getEnabledCourses() {
		String hqlString = "from Courses as course where course.enabled=?";
		Query query = query(hqlString);
		query.setString(0, "1");
		List<Course> ts = query.list();
		return ts;
	}

	@Override
	public List<Course> getAllCourses() {
		return getAll("Course");
	}

	@Override
	public void update(int courseId, String title, String description,
			String difficulty, int categoryId, int directionId, String enabled) {
		String hql = "update Course as course set course.title = ?, course.description = ?, course.difficulty = ?, course.category.categoryId = ?, course.direction.directionId = ?, course.enabled = ? where course.courseId = ?";
		Query query = query(hql);
		query.setString(0, title).setString(1, description)
				.setString(2, difficulty).setInteger(3, categoryId)
				.setInteger(4, directionId).setString(5, enabled)
				.setInteger(6, courseId).executeUpdate();
	}

	@Override
	public void update(int courseId, String title, String description,
			String difficulty, int categoryId, int directionId, String enabled,
			String imageUrl) {
		String hql = "update Course as course set course.title = ?, course.description = ?, course.difficulty = ?, course.category.categoryId = ?, course.direction.directionId = ?, course.enabled = ?, course.imageUrl = ? where course.courseId = ?";
		Query query = query(hql);
		query.setString(0, title).setString(1, description)
				.setString(2, difficulty).setInteger(3, categoryId)
				.setInteger(4, directionId).setString(5, enabled)
				.setString(6, imageUrl).setInteger(7, courseId).executeUpdate();
	}

	@Override
	public Query getAllCoursesOfCategory(int categoryId) {
		String hql = "from Course as course where course.category.categoryId = ?";
		Query query = query(hql);
		query.setInteger(0, categoryId);
		return query;
	}

	@Override
	public Query getAllCoursesOfCategoryByNums(int categoryId) {
		String hql = "from Course as course where course.category.categoryId = ? order by course.studentNums desc";
		Query query = query(hql);
		query.setInteger(0, categoryId);
		return query;
	}

	@Override
	public Query getQuery(String hql) {
		return query(hql);
	}

	@Override
	public void delete(int courseId) {
		String hql = "delete Course as course where course.courseId = ?";
		Query query = query(hql);
		query.setInteger(0, courseId).executeUpdate();
	}

	@Override
	public List<Course> search(String key) {
		String hql = "from Course as course where course.title like ?";
		Query query = query(hql).setString(0, "%" + key + "%");
		return query.list();
	}

	@Override
	public List<Course> getOtherCourses(int courseId, int directionId) {
		String hql = "from Course as course where course.courseId <> ? and course.direction.directionId = ? order by course.studentNums desc";
		Query query = query(hql).setInteger(0, courseId).setInteger(1, directionId).setMaxResults(5);
		return query.list();
	}

	@Override
	public void addNums(int courseId) {
		String hql = "update Course as course set course.studentNums = course.studentNums + 1 where course.courseId = ?";
		Query query = query(hql).setInteger(0, courseId);
		query.executeUpdate();
	}

}
