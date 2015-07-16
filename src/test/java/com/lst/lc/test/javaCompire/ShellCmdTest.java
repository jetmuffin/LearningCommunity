package com.lst.lc.test.javaCompire;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.junit.Test;

public class ShellCmdTest {

	public Object exec(String cmd) {

		try {
			String[] cmdA = { "/bin/sh", "-c", cmd };
			Process process = Runtime.getRuntime().exec(cmdA);

			LineNumberReader ibr = new LineNumberReader(new InputStreamReader(
					process.getInputStream()));
			StringBuffer isb = new StringBuffer();
			String line;
			while ((line = ibr.readLine()) != null) {
				isb.append(line).append("\n");
			}
			LineNumberReader ebr = new LineNumberReader(new InputStreamReader(
					process.getErrorStream()));
			StringBuffer esb = new StringBuffer();
			while ((line = ebr.readLine()) != null) {
				esb.append(line).append("\n");
			}
			return isb.append(esb).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String compireJava(String source,String stdin,String name){
		String ans;
		if(stdin == null){
			stdin = "";
		}
		if(name == null || source == null){
			ans = "Please enter the correct code !!!";
			return ans;
		}
		
		File pathFile = null;
		FileOutputStream fileSource = null;
		FileOutputStream fileStdin = null;
		long Timefile = System.currentTimeMillis();
		String cmd = "cd /tmp/LearningCommunity/Compire/"+Timefile+";javac "+name+".java;java "+name+"< "+name+".in";
		String path = "/tmp/LearningCommunity/Compire/"+Timefile;
		pathFile = new File(path);
		if(!pathFile.exists()){
			pathFile.mkdirs();
		}
		try{
			fileSource = new FileOutputStream(path+"/"+name+".java");
			fileStdin = new FileOutputStream(path+"/"+name+".in");
			fileSource.write(source.getBytes());
			fileStdin.write(stdin.getBytes());
		}catch(IOException e){
		}finally{
			try {
				fileStdin.close();
				fileSource.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ans = exec(cmd).toString();
		deleteFile(pathFile);
		return ans;
	}
	public void deleteFile(File file) {
	    File[] files = file.listFiles();
	    if (files != null)
	        for (File f : files)
	        	deleteFile(f);
	    file.delete();
	}
	
	@Test
	public void cmdTest() throws IOException {
		String source = "import java.util.*;import java.lang.*;import java.io.*;class Main{public static void main (String[] args) throws java.lang.Exception{Scanner cin = new Scanner (new BufferedInputStream(System.in));int a,b;a = cin.nextInt();System.out.println(a);b = cin.nextInt();System.out.println(b);}}";
		String stdin = "1 2";
		String name = "Main";
		String stdout = null;
		stdout = compireJava(source, stdin, name);
		System.out.println(stdout);
	}
}
