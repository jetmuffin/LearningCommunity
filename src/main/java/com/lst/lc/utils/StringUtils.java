package com.lst.lc.utils;

public class StringUtils {

	public static boolean isNull(String string) {
		if (string == null || string == "" || string.equals("")
				|| string.equals("0"))
			return true;
		return false;
	}

}
