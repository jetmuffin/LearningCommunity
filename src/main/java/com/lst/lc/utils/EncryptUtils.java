package com.lst.lc.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class EncryptUtils {
	
    public static final String KEY_MD5 = "MD5";  
	
    public static String encryptMD5(byte[] data) throws Exception {  
    	  
        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);  
        md5.update(data);  
        BigInteger md5BI = new BigInteger(md5.digest());  
        return  md5BI.toString(16);  
  
    }  
    
}
