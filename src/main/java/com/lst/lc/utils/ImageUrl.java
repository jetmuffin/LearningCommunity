package com.lst.lc.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageUrl {
	/**
	 * 通过url获得图片的字节流
	 * @param urlPath
	 * @return
	 */
	public static byte[] getImageFromURL(String urlPath) { 
        byte[] data = null; 
        InputStream is = null; 
        HttpURLConnection conn = null; 
        try { 
            URL url = new URL(urlPath); 
            conn = (HttpURLConnection) url.openConnection(); 
            conn.setDoInput(true); 
            conn.setRequestMethod("GET"); 
            conn.setConnectTimeout(6000); 
            is = conn.getInputStream(); 
            if (conn.getResponseCode() == 200) { 
                data = readInputStream(is); 
            } else{ 
                data=null; 
            } 
        } catch (MalformedURLException e) { 
            //e.printStackTrace(); 
            System.out.println("time out");
        } catch (IOException e) { 
            e.printStackTrace(); 
        } finally { 
            try { 
                if(is != null){ 
                    is.close(); 
                }                
            } catch (IOException e) { 
                e.printStackTrace(); 
            } 
            conn.disconnect();           
        } 
        return data; 
    }
	public static byte[] readInputStream(InputStream is) { 
        ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
        byte[] buffer = new byte[10240]; 
        int length = -1; 
        try { 
            while ((length = is.read(buffer)) != -1) { 
                baos.write(buffer, 0, length); 
            } 
            baos.flush(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
        byte[] data = baos.toByteArray(); 
        try { 
            is.close(); 
            baos.close(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
        return data; 
    } 
}
