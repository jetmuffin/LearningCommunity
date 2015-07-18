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
import com.lst.lc.entities.Course;
import com.lst.lc.web.bean.MyCaptcha;
import com.lst.lc.web.service.CaptchaHandler;

@Controller
@RequestMapping("/read")
public class ReadResourcesController {

	@Autowired
	@Qualifier("courseDao")
	private CourseDao courseDao;

	@Autowired
	private CaptchaHandler captchaHandler;

	@RequestMapping(value = "/photo/{courseId}", method = RequestMethod.GET)
	public void readPhotos(@PathVariable int courseId,
			HttpServletResponse response) {
		Course course = courseDao.getCourse(courseId);
		String imagePath = course.getImageUrl();
		File imageFile = new File(imagePath);
		if (imageFile != null && imageFile.exists()) {
			byte[] buffer = new byte[5120];
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(imageFile);
				os = response.getOutputStream();
				while (is.read(buffer) != -1) {
					os.write(buffer);
					os.flush();
				}
			} catch (Exception e) {
				try {
					response.getWriter().write("Can't read the Photo!");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} finally {
				try {
					if (is != null) {
						is.close();
					}
					if (os != null) {
						os.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else{
			try {
				response.getWriter().write("Not Found the Photo!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
