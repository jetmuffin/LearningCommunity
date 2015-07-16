package com.lst.lc.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class MultipartFileUtils {

	public static String saveFile(MultipartFile multipartFile,String path){
		
		long unixTime = System.currentTimeMillis();
		String name = HashUtils.HashPath(multipartFile.getOriginalFilename()+unixTime);
		
		String multipartUrl = path+"/"+name;
		
		File file = null;
		try {
			file = new File(path);
			if(!file.exists()){
				file.mkdirs();
			}
			byte[] buffer = multipartFile.getBytes();
			FileOutputStream fStream = new FileOutputStream(multipartUrl);
			fStream.write(buffer);
			fStream.close();
		} catch (IOException e) {
			multipartUrl = "";
			e.printStackTrace();
		}
		return multipartUrl;
	}
	
	public static void removeFile(String path){
		File file = null;
		file = new File(path);
		if(file.exists()){
			file.delete();
		}
	}
}
