package com.lst.lc.web.bean;

import com.lst.lc.entities.User;

public class LoginUser {

	private int state;
	private String msg;
	public LoginUser() {
		super();
	}
	public LoginUser(int state, String loginMsg) {
		super();
		this.state = state;
		this.msg = loginMsg;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getLoginMsg() {
		return msg;
	}
	public void setLoginMsg(String loginMsg) {
		this.msg = loginMsg;
	}
	
}
