package com.lst.lc.web.frontend.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lst.lc.dao.CourseDao;
import com.lst.lc.dao.LessonDao;
import com.lst.lc.dao.UserDao;
import com.lst.lc.entities.Course;
import com.lst.lc.entities.User;
import com.lst.lc.utils.PathUtils;
import com.lst.lc.web.bean.MyCaptcha;
import com.lst.lc.web.service.CaptchaHandler;

@Controller
@RequestMapping("/read")
public class ReadResourcesController {

	@Autowired
	@Qualifier("courseDao")
	private CourseDao courseDao;
	
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
	
	@Autowired
	@Qualifier("lessonDao")
	private LessonDao lessonDao;
	
	@Autowired
	private CaptchaHandler captchaHandler;

	@RequestMapping(value = "/photo/{courseId}", method = RequestMethod.GET)
	public void readPhotos(@PathVariable int courseId,
			HttpServletResponse response) {
		Course course = courseDao.getCourse(courseId);
		String imagePath = course.getImageUrl();
		PathUtils.readPhoto(imagePath, response);
	}

	@RequestMapping(value = "/avatar/{userId}", method = RequestMethod.GET)
	public void readAvatar(@PathVariable int userId,
			HttpServletResponse response){
		User user = userDao.getById(userId);
		String avatarPath = user.getAvatar();
		PathUtils.readPhoto(avatarPath, response);
	}
	
	@RequestMapping(value = "/video/{lessonId}", method = RequestMethod.GET)
	public void readVideo(@PathVariable int lessonId,
			HttpServletResponse response){
		String videoPath = lessonDao.getLesson(lessonId).getVideoUrl();
		PathUtils.readPhoto(videoPath, response);
	}
	
	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
	public void getCaptcha(HttpServletResponse response, HttpSession session) {
		response.setContentType("image/png");
		response.setHeader("cache", "no-cache");
		OutputStream outputStream;
		try {
			outputStream = response.getOutputStream();
			MyCaptcha myCaptcha = captchaHandler.getCatpcha();
			// 取得验证码字符串放入Session
			String validationCode = myCaptcha.getCode();
			session.setAttribute("validationCode", validationCode);
			// 取得验证码图片并输出
			BufferedImage bufferedImage = myCaptcha.getBufferedImage();
			ImageIO.write(bufferedImage, "png", outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
