package com.lst.lc.web.bean;

import com.lst.lc.entities.User;

public class StatusMessage {

	private int status;
	private String message;
	
	
	public StatusMessage() {
		super();
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public StatusMessage(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
}
