package com.lst.lc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class PathUtils {

	public static String getPropertyPath() {
		return System.getProperty("user.dir");
	}
	
	public static void readPhoto(String imagePath,HttpServletResponse response){
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
}
