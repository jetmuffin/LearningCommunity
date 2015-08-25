package com.lst.lc.utils;

import java.io.ByteArrayOutputStream;
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
	
	public static byte[] readPhoto(String imagePath){
		File imageFile = new File(imagePath);
		//String format = imagePath.substring(imagePath.lastIndexOf(".") + 1).trim().toLowerCase();
		byte[] buffer = null;
		byte[] data = null;
		if (imageFile != null && imageFile.exists()) {
			buffer = new byte[5120];
			InputStream is = null;
			//OutputStream os = null;
	        ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
			try {
				is = new FileInputStream(imageFile);
				while (is.read(buffer) != -1) {
					baos.write(buffer);
				}
				baos.flush();
				data = baos.toByteArray();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (is != null) {
						is.close();
					}
					if (baos != null) {
						baos.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}
}
