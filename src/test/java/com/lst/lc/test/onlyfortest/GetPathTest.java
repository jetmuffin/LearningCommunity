package com.lst.lc.test.onlyfortest;

import org.junit.Test;

import com.lst.lc.utils.HashUtils;

public class GetPathTest {

	@Test
	public void getPahtTest(){
		System.out.println("path = "+System.getProperty("user.dir"));
	}
	
	@Test
	public void upateTest(){
		String path = "sfasdfasd/dsafsdf/dsfsda/amdin/aaaaaaaaa";
		int lastIndex = path.lastIndexOf("/");
		path = path.substring(0, lastIndex);
		long unixTime = System.currentTimeMillis();
		path += unixTime;
		System.out.println(path);
	}
}
