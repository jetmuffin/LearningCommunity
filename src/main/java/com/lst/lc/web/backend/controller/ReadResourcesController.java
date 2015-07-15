package com.lst.lc.web.backend.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lst.lc.dao.CourseDao;
import com.lst.lc.entities.Course;

@Controller
@RequestMapping("/manage/read")
public class ReadResourcesController {

	@Autowired
	@Qualifier("courseDao")
	private CourseDao courseDao;

	@RequestMapping(value = "/photo/{courseId}", method = RequestMethod.GET)
	public void readPhotos(@PathVariable int courseId,HttpServletResponse response) {
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
		}
	}
}
