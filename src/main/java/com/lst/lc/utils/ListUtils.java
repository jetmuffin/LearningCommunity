package com.lst.lc.utils;

import java.util.List;

public class ListUtils {
	
	public static <T> boolean isNull(List<T> lists){
		if(lists == null || lists.size() == 0)
			return true;
		return false;
	}

}
