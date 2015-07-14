package com.lst.lc.web.backend.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lst.lc.dao.CategoryDao;
import com.lst.lc.dao.CourseDao;
import com.lst.lc.dao.DirectionDao;
import com.lst.lc.entities.Category;
import com.lst.lc.entities.Course;
import com.lst.lc.entities.Direction;
import com.lst.lc.page.Page;
import com.lst.lc.page.PageHandler;
import com.lst.lc.utils.MultipartFileUtils;
import com.lst.lc.utils.PathUtils;

@Controller
@RequestMapping("/manage/course")
public class CourseController {

	@Autowired
	@Qualifier("categoryDao")
	private CategoryDao categoryDao;

	@Autowired
	@Qualifier("directionDao")
	private DirectionDao directionDao;

	@Autowired
	@Qualifier("courseDao")
	private CourseDao courseDao;

	@Autowired
	private PageHandler<Course> pageHandler;

	public CourseController() {
		super();
	}

	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public String listCourse(Model model, String pageNum,String pageSize) {
		int pageNow = 1;
		int pagesize = 10;
		if(pageSize != null){
			pagesize = Integer.valueOf(pageSize);
		}
		if(pageSize != null){
			pageNow = Integer.valueOf(pageNum);
		}
		Page<Course> page = pageHandler
				.getPage(pageNow, pagesize, Course.class);
		model.addAttribute("module", "course");
		model.addAttribute("page", page);
		return "backend/course/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		List<Direction> directions = directionDao.getEnabledDirections();
		model.addAttribute("directions", directions);
		return "backend/course/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(String title, String description, String difficulty, int categoryId, int directionId,
			MultipartFile image, RedirectAttributes redirectAttributes,HttpSession session) {
		Category category = categoryDao.getCategory(categoryId);
		Direction direction = directionDao.getDirection(directionId);
		
		
		//上传文件命名规则：项目路径+directionId
		String imagePath = session.getServletContext().getRealPath("/")+directionId;
	
		System.err.println("imagePath = "+imagePath);
		
		String imageUrl = imagePath+"/"+image.getOriginalFilename();
		MultipartFileUtils.saveFile(image, imagePath);

		System.err.println("imagePath = "+imageUrl);
		
		Course course = new Course(category, direction, title, description, 0, 0, new Date(), difficulty, imageUrl, "0", "0");
		courseDao.addCourse(course);
		redirectAttributes.addFlashAttribute("courseMsg", "添加课程信息成功");
		return "redirect:/manage/course/courses";
	}
}
