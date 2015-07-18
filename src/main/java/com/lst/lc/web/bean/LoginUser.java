package com.lst.lc.web.bean;

import com.lst.lc.entities.User;

public class LoginUser {

	private User user;
	private String loginMsg;
	public LoginUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginUser(User user, String loginMsg) {
		super();
		this.user = user;
		this.loginMsg = loginMsg;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getLoginMsg() {
		return loginMsg;
	}
	public void setLoginMsg(String loginMsg) {
		this.loginMsg = loginMsg;
	}
	
	
}
