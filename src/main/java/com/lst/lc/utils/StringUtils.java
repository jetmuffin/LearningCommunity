package com.lst.lc.utils;

import java.util.Arrays;
import java.util.List;

public class StringUtils {
	private static String div = ",";

	public static boolean isNull(String string) {
		if (string == null || string == "" || string.equals("")
				|| string.equals("0"))
			return true;
		return false;
	}

	public static List<String> stringSplit(String string) {
		String[] strings = string.split(div);
		List<String> list = Arrays.asList(strings);
		return list;
	}
	
	public static List<String> stringSplit(String string, String div1) {
		String[] strings = string.split(div1);
		List<String> list = Arrays.asList(strings);
		return list;
	}
	
}
