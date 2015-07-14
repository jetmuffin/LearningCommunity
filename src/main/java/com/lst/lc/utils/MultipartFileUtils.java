package com.lst.lc.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class MultipartFileUtils {

	public static void saveFile(MultipartFile multipartFile,String path){
		String filename = multipartFile.getOriginalFilename();
		File file = null;
		try {
			file = new File(path);
			if(!file.exists()){
				file.mkdirs();
			}
			byte[] buffer = multipartFile.getBytes();
			FileOutputStream fStream = new FileOutputStream(path+"/"+filename);
			fStream.write(buffer);
			fStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
