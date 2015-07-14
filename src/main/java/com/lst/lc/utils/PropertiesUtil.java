package com.lst.lc.utils;

import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

public class PropertiesUtil {
	
	public static String getValue(String key) {
		Properties mProperties = new Properties();
		try {
			mProperties.load(PropertiesUtil.class.getClassLoader()
					.getResourceAsStream("config.properties"));
			return mProperties.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
