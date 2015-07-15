package com.lst.lc.web.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lst.lc.dao.CategoryDao;
import com.lst.lc.dao.CourseDao;
import com.lst.lc.dao.DirectionDao;
import com.lst.lc.entities.Category;
import com.lst.lc.entities.Course;
import com.lst.lc.entities.Direction;
import com.lst.lc.page.Page;
import com.lst.lc.page.PageHandler;
import com.lst.lc.utils.PropertiesUtil;
import com.lst.lc.web.bean.CourseMenu;
import com.lst.lc.web.bean.CourseTag;
import com.lst.lc.web.bean.PartCategory;

@Service
public class CourseMenuHandler {

	@Autowired
	private PageHandler<Course> pageHandler;

	@Autowired
	@Qualifier("categoryDao")
	private CategoryDao categoryDao;

	@Autowired
	@Qualifier("directionDao")
	private DirectionDao directionDao;

	@Autowired
	@Qualifier("courseDao")
	private CourseDao courseDao;

	String[] difficulties = { "初级", "中级", "高级" };

	public CourseMenu getCourseMenu(String directionId, String categoryId,
			String difficulty, String type, String pageNum, String pageSize) {
		int pageNow, pagesize;
		if (pageNum == null) {
			pageNow = 1;
		} else {
			pageNow = Integer.valueOf(pageNum);
		}
		if (pageSize == null) {
			pagesize = Integer.valueOf(PropertiesUtil.getValue("pageSize"));
		} else {
			pagesize = Integer.valueOf(pageSize);
		}
		/*
		 * 情况1:没有指定方向与分类，分类与方向是全部，但可能指定了排序方式和难度
		 */
		if (directionId == null && categoryId == null) {
			return getCourseMenuDefault(difficulty, type, pageNow, pagesize);
		} else if (directionId != null) {
			return getCourseMenuByDirection(directionId, difficulty, type, pageNow, pagesize);
		} else {
			return getCourseMenuByCategory(categoryId, difficulty, type, pageNow, pagesize);
		}

	}

	public CourseMenu getCourseMenuDefault(String difficulty, String type,
			int pageNum, int pageSize) {

		CourseMenu courseMenu = new CourseMenu();
		List<CourseTag> directionTags = new ArrayList<CourseTag>();
		List<CourseTag> categoryTags = new ArrayList<CourseTag>();
		List<CourseTag> difficultyTags = new ArrayList<CourseTag>();

		List<Direction> directions = directionDao.getAllDirections();
		List<Category> categories = categoryDao.getAllCategories();

		/*
		 * ＂全部＂标记为活跃
		 */
		CourseTag allTag = new CourseTag("全部", true);
		directionTags.add(allTag);
		categoryTags.add(allTag);
		/*
		 * 添加标记信息
		 */
		for (int i = 0; i < directions.size(); i++) {
			CourseTag courseTag = new CourseTag(directions.get(i)
					.getDirectionName(), false);
			directionTags.add(courseTag);
		}
		for (int i = 0; i < categories.size(); i++) {
			CourseTag courseTag = new CourseTag(categories.get(i)
					.getCategoryName(), false);
			categoryTags.add(courseTag);
		}

		difficultyTags.add(allTag);
		for (int i = 0; i < 3; i++) {
			CourseTag courseTag1 = new CourseTag(difficulties[i], false);
			difficultyTags.add(courseTag1);
		}

		if (difficulty != null) {
			difficultyTags.get(0).setActive(false);
			int index = Integer.valueOf(difficulty);
			difficultyTags.get(index).setActive(true);
		}

		courseMenu.setCategoryTags(categoryTags);
		courseMenu.setDifficultyTags(difficultyTags);
		courseMenu.setDirectionTags(directionTags);
		Page<Course> page;

		if (type == null && difficulty == null) {
			page = pageHandler.getPage(pageNum, pageSize, Course.class);
		} else if (type != null && difficulty == null) {
			String hql = "from Course as course order by course.studentNums desc";
			Query query = courseDao.getQuery(hql);
			page = pageHandler.getPage(pageNum, pageSize, Course.class, query);
		} else if (type == null && difficulty != null) {
			String hql = "from Course as course where course.difficulty = ?";
			Query query = courseDao.getQuery(hql);
			query.setString(0, difficulties[Integer.valueOf(difficulty)]);
			page = pageHandler.getPage(pageNum, pageSize, Course.class, query);
		} else {
			String hql = "from Course as course where course.difficulty = ? order by course.studentNums desc";
			Query query = courseDao.getQuery(hql);
			query.setString(0, difficulties[Integer.valueOf(difficulty)]);
			page = pageHandler.getPage(pageNum, pageSize, Course.class, query);
		}
		courseMenu.setPage(page);
		return courseMenu;
	}

	public CourseMenu getCourseMenuByDirection(String directionId,
			String difficulty, String type, int pageNum, int pageSize) {

		CourseMenu courseMenu = new CourseMenu();
		List<CourseTag> directionTags = new ArrayList<CourseTag>();
		List<CourseTag> categoryTags = new ArrayList<CourseTag>();
		List<CourseTag> difficultyTags = new ArrayList<CourseTag>();

		int directionid = Integer.valueOf(directionId);
		List<Direction> directions = directionDao.getAllDirections();
		Direction activeDirection = directionDao.getDirection(directionid);
		List<PartCategory> categories = categoryDao
				.getCategoriesOfDirection(directionid);

		CourseTag allTag = new CourseTag("全部", false);
		directionTags.add(allTag);
		for (int i = 0; i < directions.size(); i++) {
			CourseTag courseTag = new CourseTag();
			courseTag.setName(directions.get(i).getDirectionName());
			if (directions.get(i).getDirectionId() == activeDirection
					.getDirectionId()) {
				courseTag.setActive(true);
			} else {
				courseTag.setActive(false);
			}
			directionTags.add(courseTag);
		}

		allTag.setActive(true);
		categoryTags.add(allTag);
		for (int i = 0; i < categories.size(); i++) {
			CourseTag courseTag = new CourseTag(categories.get(i).getCategoryName(), false);
			categoryTags.add(courseTag);
		}
		
		difficultyTags.add(allTag);
		for (int i = 0; i < 3; i++) {
			CourseTag courseTag1 = new CourseTag(difficulties[i], false);
			difficultyTags.add(courseTag1);
		}

		if (difficulty != null) {
			difficultyTags.get(0).setActive(false);
			int index = Integer.valueOf(difficulty);
			difficultyTags.get(index).setActive(true);
		}
		
		courseMenu.setCategoryTags(categoryTags);
		courseMenu.setDifficultyTags(difficultyTags);
		courseMenu.setDirectionTags(directionTags);
		Page<Course> page;

		if (type == null && difficulty == null) {
			String hql = "from Course as course where course.direction.directionId = ?";
			Query query = courseDao.getQuery(hql);
			query.setInteger(0, directionid);
			page = pageHandler.getPage(pageNum, pageSize, Course.class, query);
		} else if (type != null && difficulty == null) {
			String hql = "from Course as course where course.direction.directionId = ? order by course.studentNums desc";
			Query query = courseDao.getQuery(hql);
			query.setInteger(0, directionid);
			page = pageHandler.getPage(pageNum, pageSize, Course.class, query);
		} else if (type == null && difficulty != null) {
			String hql = "from Course as course where course.direction.directionId = ? and course.difficulty = ?";
			Query query = courseDao.getQuery(hql);
			query.setInteger(0, directionid);
			query.setString(1, difficulties[Integer.valueOf(difficulty)]);
			page = pageHandler.getPage(pageNum, pageSize, Course.class, query);
		} else {
			String hql = "from Course as course where course.direction.directionId = ? and course.difficulty = ? order by course.studentNums desc";
			Query query = courseDao.getQuery(hql);
			query.setInteger(0, directionid);
			query.setString(1, difficulties[Integer.valueOf(difficulty)]);
			page = pageHandler.getPage(pageNum, pageSize, Course.class, query);
		}
		courseMenu.setPage(page);
		return courseMenu;
	}
	
	public CourseMenu getCourseMenuByCategory(String categoryId,
			String difficulty, String type, int pageNum, int pageSize) {
		
		CourseMenu courseMenu = new CourseMenu();
		List<CourseTag> directionTags = new ArrayList<CourseTag>();
		List<CourseTag> categoryTags = new ArrayList<CourseTag>();
		List<CourseTag> difficultyTags = new ArrayList<CourseTag>();
		
		int categoryid = Integer.valueOf(categoryId);
		Category category = categoryDao.getCategory(categoryid);
		int directionid = category.getDirection().getDirectionId();
		List<Direction> directions = directionDao.getAllDirections();
		List<PartCategory> categories = categoryDao
				.getCategoriesOfDirection(directionid);
		
		CourseTag allTag = new CourseTag("全部", false);
		directionTags.add(allTag);
		categoryTags.add(allTag);
		
		for (int i = 0; i < directions.size(); i++) {
			CourseTag courseTag = new CourseTag();
			courseTag.setName(directions.get(i).getDirectionName());
			if (directions.get(i).getDirectionId() == directionid) {
				courseTag.setActive(true);
			} else {
				courseTag.setActive(false);
			}
			directionTags.add(courseTag);
		}
		
		for(int i = 0; i < categories.size(); i++){
			CourseTag courseTag = new CourseTag();
			courseTag.setName(categories.get(i).getCategoryName());
			if(categories.get(i).getCategoryId() == categoryid){
				courseTag.setActive(true);
			}else{
				courseTag.setActive(false);
			}
			categoryTags.add(courseTag);
		}
		
		allTag.setActive(true);
		
		difficultyTags.add(allTag);
		for (int i = 0; i < 3; i++) {
			CourseTag courseTag1 = new CourseTag(difficulties[i], false);
			difficultyTags.add(courseTag1);
		}

		if (difficulty != null) {
			difficultyTags.get(0).setActive(false);
			int index = Integer.valueOf(difficulty);
			difficultyTags.get(index).setActive(true);
		}
		
		courseMenu.setCategoryTags(categoryTags);
		courseMenu.setDifficultyTags(difficultyTags);
		courseMenu.setDirectionTags(directionTags);
		Page<Course> page;

		if (type == null && difficulty == null) {
			String hql = "from Course as course where course.category.categoryId = ?";
			Query query = courseDao.getQuery(hql);
			query.setInteger(0, categoryid);
			page = pageHandler.getPage(pageNum, pageSize, Course.class, query);
		} else if (type != null && difficulty == null) {
			String hql = "from Course as course where course.category.categoryId = ? order by course.studentNums desc";
			Query query = courseDao.getQuery(hql);
			query.setInteger(0, categoryid);
			page = pageHandler.getPage(pageNum, pageSize, Course.class, query);
		} else if (type == null && difficulty != null) {
			String hql = "from Course as course where course.category.categoryId = ? and course.difficulty = ?";
			Query query = courseDao.getQuery(hql);
			query.setInteger(0, categoryid);
			query.setString(1, difficulties[Integer.valueOf(difficulty)]);
			page = pageHandler.getPage(pageNum, pageSize, Course.class, query);
		} else {
			String hql = "from Course as course where course.category.categoryId = ? and course.difficulty = ? order by course.studentNums desc";
			Query query = courseDao.getQuery(hql);
			query.setInteger(0, categoryid);
			query.setString(1, difficulties[Integer.valueOf(difficulty)]);
			page = pageHandler.getPage(pageNum, pageSize, Course.class, query);
		}
		courseMenu.setPage(page);
		return courseMenu;
	}

}
