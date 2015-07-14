package com.lst.lc.web.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lst.lc.dao.CategoryDao;
import com.lst.lc.dao.CourseDao;
import com.lst.lc.dao.DirectionDao;
import com.lst.lc.entities.Category;
import com.lst.lc.entities.Course;
import com.lst.lc.entities.Direction;
import com.lst.lc.page.PageHandler;
import com.lst.lc.utils.PropertiesUtil;
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
	
	public CourseMenu getCourseMenu(String directionId, String categoryId, String difficulty, String type, String pageNum, String pageSize){
		int pageNow, pagesize;
		if(pageNum == null){
			pageNow = 1;
		}else{
			pageNow = Integer.valueOf(pageNum);
		}
		if(pageSize == null){
			pagesize = Integer.valueOf(PropertiesUtil.getValue("pageSize"));
		}else{
			pagesize = Integer.valueOf(pageSize);
		}
		/*
		 * 初始情况
		 */
		if(directionId == null && categoryId == null && difficulty == null){
			
		}
		
		List<Direction> directions = directionDao.getAllDirections();
		List<Category> categories = categoryDao.getAllCategories();
		CourseMenu courseMenu = new CourseMenu();
		List<CourseTag> directionTags = new ArrayList<CourseTag>();
		List<CourseTag> categoryTags = new ArrayList<CourseTag>();
		List<CourseTag> difficultyTags = new ArrayList<CourseTag>();
		
		CourseTag allTag = new CourseTag("全部", false);
		directionTags.add(allTag);
		categoryTags.add(allTag);
		difficultyTags.add(allTag);
		
		boolean isDirection = true;
		
		return null;
	}

}
