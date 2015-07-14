package com.lst.lc.test.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;


public class UploadMultipartFileTest {

	public void saveFile(byte[] multipartFile,String path){
		String filename = "aa.txt";
		File file = null;
		try {
			file = new File(path);
			if(!file.exists()){
				file.mkdirs();
			}
			byte[] buffer = multipartFile;
			FileOutputStream fStream = new FileOutputStream(path+"/"+filename);
			fStream.write(buffer);
			fStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test(){
		String xyzString = "dsfadsfadsfas";
		String path = "/home/innerac/dev/vc/vb/vc";
		saveFile(xyzString.getBytes(), path);
	}
}
