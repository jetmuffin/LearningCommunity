package com.lst.lc.test.upload;

import org.junit.Test;

import com.lst.lc.utils.HashUtils;

public class hashMD5Test {

	@Test
	public void test(){
		String string1 = "你好";
		String string2 = "烫烫烫";
		String string3 = "!@#$%^&*().jds";
		System.out.println(HashUtils.HashPath(string1));
		System.out.println(HashUtils.HashPath(string2));
		System.out.println(HashUtils.HashPath(string3));
	}
}
