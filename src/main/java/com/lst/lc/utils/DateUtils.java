package com.lst.lc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static String getDateString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		return sdf.format(date);
	}

}
