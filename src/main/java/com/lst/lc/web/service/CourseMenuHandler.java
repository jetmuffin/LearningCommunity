package com.lst.lc.web.service;

import java.util.ArrayList;
import java.util.List;

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
import com.lst.lc.utils.StringUtils;
import com.lst.lc.web.bean.CourseMenu;
import com.lst.lc.web.bean.CourseTag;

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

		/*
		 * 初始化所需参数
		 */
		CourseMenu courseMenu = new CourseMenu();
		List<CourseTag> directionTags = new ArrayList<CourseTag>();
		List<CourseTag> categoryTags = new ArrayList<CourseTag>();
		List<CourseTag> difficultyTags = new ArrayList<CourseTag>();
		Page<Course> page;
		// 方向信息无论哪种情况都是全部的
		List<Direction> directions = directionDao.getAllDirections();
		// 分类信息不一定是全部，也可能是某个方向下的分类
		List<Category> categories;

		/*
		 * 初始化页码和每页大小
		 */
		int pageNow, pagesize;
		if (StringUtils.isNull(pageNum)) {
			pageNow = 1;
		} else {
			pageNow = Integer.valueOf(pageNum);
		}
		if (StringUtils.isNull(pageSize)) {
			pagesize = Integer.valueOf(PropertiesUtil.getValue("pageSize"));
		} else {
			pagesize = Integer.valueOf(pageSize);
		}
		
		//主键查询参数
		int idParamNum = 0;
		int idParam = 0;
		//难度查询参数
		int difficultyParamNum = 0;
		String difficultyParam = null;
		//起始查询语句
		String hql = "from Course as course ";
		
		/*
		 * 先做一个处理，即当方向与分类都有参数，且他们是冲突的，以方向为准，分类置空
		 */
		if(directionId != null && directionId.equals("0")){
			categoryId = null;
		}
		if(!StringUtils.isNull(categoryId) && !StringUtils.isNull(directionId)){
			Category category = categoryDao.getCategory(Integer.valueOf(categoryId));
			if(category.getDirection().getDirectionId() != Integer.valueOf(directionId)){
				categoryId = null;
			}

		}

		/*
		 * 分类不为空的情况下，不论方向是否指定，均可以确定为改分类所属的方向
		 */
		if (!StringUtils.isNull(categoryId)) {
			int categoryid = Integer.valueOf(categoryId);
			Category category = categoryDao.getCategory(categoryid);
			int directionid = category.getDirection().getDirectionId();
			/*
			 * 添加方向标签行信息;
			 * 方向标签行的＂全部＂置为不活跃;
			 * 将与分类所属方向相同的方向标签置为活跃;
			 */
			CourseTag directionAllTag = new CourseTag(0, "全部", false);
			directionTags.add(directionAllTag);
			for (int i = 0; i < directions.size(); i++) {
				CourseTag directionTag = new CourseTag(directions.get(i)
						.getDirectionId(),
						directions.get(i).getDirectionName(), false);
				if (directionTag.getId() == directionid) {
					directionTag.setActive(true);
				}
				directionTags.add(directionTag);
			}

			/*
			 * 添加分类标签行信息;
			 * 分类标签行的＂全部＂置为不活跃;
			 * 将与该分类相同的标签置为活跃;
			 */
			CourseTag categoryAllTag = new CourseTag(0, "全部", false);
			categoryTags.add(categoryAllTag);
			categories = categoryDao.getCategories(directionid);
			for (int i = 0; i < categories.size(); i++) {
				CourseTag categoryTag = new CourseTag(categories.get(i)
						.getCategoryId(),
						categories.get(i).getCategoryName(), false);
				if (categoryTag.getId() == categoryid) {
					categoryTag.setActive(true);
				}
				categoryTags.add(categoryTag);
			}
			
			//拼接查询语句
			hql = hql + "where course.category.categoryId = ? ";
			idParamNum += 1;
			idParam = categoryid;
		}
		/*
		 *分类为空，即没有指定分类，需要判断方向是否为空
		 */
		else {
			/*
			 * 如果方向不为空，即指定了方向，那么该方向要标记活跃，同时分类取该方向下的分类
			 */
			if(!StringUtils.isNull(directionId)) {
				int directionid = Integer.valueOf(directionId);
				/*
				 * 添加方向标签行信息;
				 * 方向标签行的＂全部＂置为不活跃;
				 * 将与分类所属方向相同的方向标签置为活跃;
				 */
				CourseTag directionAllTag = new CourseTag(0, "全部", false);
				directionTags.add(directionAllTag);
				for (int i = 0; i < directions.size(); i++) {
					CourseTag directionTag = new CourseTag(directions.get(i)
							.getDirectionId(),
							directions.get(i).getDirectionName(), false);
					if (directionTag.getId() == directionid) {
						directionTag.setActive(true);
					}
					directionTags.add(directionTag);
				}
				
				/*
				 * 添加分类标签行信息;
				 * 分类标签行的＂全部＂置为活跃;
				 * 分类取该方向下的分类，不活跃;
				 */
				CourseTag categoryAllTag = new CourseTag(0, "全部", true);
				categoryTags.add(categoryAllTag);
				categories = categoryDao.getCategories(directionid);
				for (int i = 0; i < categories.size(); i++) {
					CourseTag categoryTag = new CourseTag(categories.get(i)
							.getCategoryId(),
							categories.get(i).getCategoryName(), false);
					categoryTags.add(categoryTag);
				}
				
				//拼接查询语句
				hql = hql + "where course.direction.directionId = ? ";
				idParamNum += 1;
				idParam = directionid;
			}
			/*
			 * 如果方向为空，即既没有指定方向也没有指定分类，那么分类取全部分类
			 */
			else {
				/*
				 * 添加方向标签行信息;
				 * 方向标签行的＂全部＂置为活跃;
				 * 所有方向均不活跃;
				 */
				CourseTag directionAllTag = new CourseTag(0, "全部", true);
				directionTags.add(directionAllTag);
				for (int i = 0; i < directions.size(); i++) {
					CourseTag directionTag = new CourseTag(directions.get(i)
							.getDirectionId(),
							directions.get(i).getDirectionName(), false);
					directionTags.add(directionTag);
				}
				
				/*
				 * 添加分类标签行信息;
				 * 分类标签行的＂全部＂置为活跃;
				 * 分类取所有分类，不活跃;
				 */
				CourseTag categoryAllTag = new CourseTag(0, "全部", true);
				categoryTags.add(categoryAllTag);
				categories = categoryDao.getAllCategories();
				for (int i = 0; i < categories.size(); i++) {
					CourseTag categoryTag = new CourseTag(categories.get(i)
							.getCategoryId(),
							categories.get(i).getCategoryName(), false);
					categoryTags.add(categoryTag);
				}
				//查询语句不需要处理
			}
		}
		
		/*
		 *难度标签需要根据difficulty传值判断，如果difficulty为空，则＂全部＂为活跃；否则，该难度活跃
		 */
		CourseTag difficultyAllTag = new CourseTag(0, "全部", true);
		difficultyTags.add(difficultyAllTag);
		for (int i = 0; i < 3; i++) {
			CourseTag difficultyTag = new CourseTag(i + 1, difficulties[i], false);
			difficultyTags.add(difficultyTag);
		}
		/*
		 * 如果difficulty不为空，则＂全部＂不活跃
		 */
		if (!StringUtils.isNull(difficulty)) {
			difficultyTags.get(0).setActive(false);
			int index = Integer.valueOf(difficulty);
			difficultyTags.get(index).setActive(true);
			
			//处理查询语句
			if(idParamNum > 0)
				hql = hql + "and ";
			else
				hql = hql + "where ";
			hql = hql + "course.difficulty = ? ";
			difficultyParam = difficulty;
			difficultyParamNum += 1;
		}
		
		//如果排序方式不为空
		if(!StringUtils.isNull(type)){
			hql = hql + "order by course.studentNums desc";
		}
		
		Query query = courseDao.getQuery(hql);
		if(idParamNum > 0){
			query.setInteger(0, idParam);
		}
		if(difficultyParamNum > 0)
			query.setString(idParamNum, difficultyParam);
		page = pageHandler.getPage(pageNow, pagesize, Course.class, query);
		
		courseMenu.setCategoryTags(categoryTags);
		courseMenu.setDifficultyTags(difficultyTags);
		courseMenu.setDirectionTags(directionTags);
		courseMenu.setPage(page);
		
		return courseMenu;

	}

}
