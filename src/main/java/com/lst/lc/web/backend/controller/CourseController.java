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
import com.lst.lc.dao.LessonDao;
import com.lst.lc.entities.Category;
import com.lst.lc.entities.Course;
import com.lst.lc.entities.CourseLesson;
import com.lst.lc.entities.Direction;
import com.lst.lc.page.Page;
import com.lst.lc.page.PageHandler;
import com.lst.lc.utils.MultipartFileUtils;

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
	@Qualifier("lessonDao")
	private LessonDao lessonDao;

	@Autowired
	private PageHandler<Course> pageHandler;

	public CourseController() {
		super();
	}

	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public String listCourse(Model model, String pageNum, String pageSize) {
		int pageNow = 1;
		int pagesize = 10;
		if (pageSize != null) {
			pagesize = Integer.valueOf(pageSize);
		}
		if (pageSize != null) {
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
	public String add(String title, String description, String difficulty,
			int categoryId, int directionId, MultipartFile image,
			String enabled, RedirectAttributes redirectAttributes) {
		Category category = categoryDao.getCategory(categoryId);
		Direction direction = directionDao.getDirection(directionId);

		String imagePath = "/opt/LearningCommunity/thumb";

		String imageUrl = MultipartFileUtils.saveFile(image, imagePath);

		Course course = new Course(category, direction, title, description, 0,
				0, new Date(), difficulty, imageUrl, "0", enabled);
		courseDao.addCourse(course);
		redirectAttributes.addFlashAttribute("courseMsg", "添加课程信息成功");
		return "redirect:/manage/course/courses";
	}
	
	@RequestMapping(value = "/view/{courseId}", method = RequestMethod.GET)
	public String view(@PathVariable int courseId, Model model) {
		Course course = courseDao.getCourse(courseId);
		List <Direction> directions = directionDao.getEnabledDirections();
		List<CourseLesson> lessons = lessonDao.getLessonsOfCourse(courseId);
		model.addAttribute("course", course);
		model.addAttribute("directions",directions);
		model.addAttribute("lessons", lessons);
		return "backend/course/view";
	}
	
	@RequestMapping(value = "/edit/{courseId}", method = RequestMethod.GET)
	public String edit(@PathVariable int courseId, Model model) {
		Course course = courseDao.getCourse(courseId);
		List<Direction> directions = directionDao.getEnabledDirections();
		model.addAttribute("course", course);
		model.addAttribute("directions", directions);
		return "backend/course/edit";
	}

	@RequestMapping(value = "/edit/{courseId}", method = RequestMethod.POST)
	public String edit(@PathVariable int courseId, String title,
			String description, String difficulty, int categoryId,
			int directionId, MultipartFile image, String enabled,
			RedirectAttributes redirectAttributes) {
		Course course = courseDao.getCourse(courseId);

		if (image.getSize() == 0) {
			courseDao.update(courseId, title, description, difficulty,
					categoryId, directionId, enabled);
		} else {
			String imagePath = course.getImageUrl();
			String imageUrl = MultipartFileUtils.updateFile(image, imagePath);

			courseDao.update(courseId, title, description, difficulty,
					categoryId, directionId, enabled, imageUrl);

		}
		redirectAttributes.addFlashAttribute("courseMsg", "修改课程信息成功");
		return "redirect:/manage/course/courses";
	}

	@RequestMapping(value = "/delete/{courseId}", method = RequestMethod.GET)
	public String delete(@PathVariable int courseId, Model model,RedirectAttributes redirectAttributes) {
		Course course = courseDao.getCourse(courseId);
		MultipartFileUtils.removeFile(course.getImageUrl());
		courseDao.delete(courseId);
		redirectAttributes.addFlashAttribute("courseMsg", "删除课程成功");
		return "redirect:/manage/course/courses";
	}

	@RequestMapping(value = "/addlesson/{courseId}", method = RequestMethod.GET)
	public String addLesson(@PathVariable int courseId, Model model) {
		Course course = courseDao.getCourse(courseId);
		model.addAttribute("course", course);
		return "backend/course/addlesson";
	}

	@RequestMapping(value = "/addlesson/{courseId}", method = RequestMethod.POST)
	public String addLesson(@PathVariable int courseId, Model model,
			String title, String summary, String type, String content,
			MultipartFile video, RedirectAttributes redirectAttributes,
			HttpSession session) {
		Course course = courseDao.getCourse(courseId);
		CourseLesson courseLesson = new CourseLesson(course, title, summary, type);
		if(type.equals("text")){
			System.out.println(content);
			courseLesson.setContent(content);
		}else{
			String videoPath = "/opt/LearningCommunity/video/"+courseId;
			String videoUrl = MultipartFileUtils.saveFile(video, videoPath);
			courseLesson.setVideoUrl(videoUrl);
		}
		lessonDao.addLesson(courseLesson);
		
		redirectAttributes.addAttribute("courseMsg", "添加课程成功");
		return "redirect:/manage/course/view/"+courseId;
	}
	
	@RequestMapping(value = "/editlesson/{lessonId}", method = RequestMethod.GET)
	public String editLesson(@PathVariable int lessonId, Model model){
		CourseLesson lesson = lessonDao.getLesson(lessonId);
		model.addAttribute("lesson", lesson);
		return "backend/course/editlesson";
	}
	
	@RequestMapping(value = "/view/editlesson/{lessonId}", method = RequestMethod.POST)
	public String editLesson(@PathVariable int lessonId, Model model, String title, String summary, String type, String content,
			MultipartFile video, RedirectAttributes redirectAttributes){
		CourseLesson lesson = lessonDao.getLesson(lessonId);
		String oldType = lesson.getType();
		String videoUrl = null;
		if(oldType.equals("video") && (video.getSize() != 0 || type.equals("text"))){
			String videoPath = lesson.getVideoUrl();
			if(type.equals("text")){
				MultipartFileUtils.removeFile(videoPath);
			}else{
				videoUrl = MultipartFileUtils.updateFile(video, videoPath);
			}
		}
		lessonDao.update(lessonId, title, summary, type, videoUrl, content);
		redirectAttributes.addAttribute("courseMsg", "修改课程成功");
		return "backend/course/editlesson";
	}
	
	
}
