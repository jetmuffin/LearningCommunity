package com.lst.lc.web.bean;

import java.awt.image.BufferedImage;

public class MyCaptcha {
	
	private String code;
	private BufferedImage bufferedImage;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}
	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

}
