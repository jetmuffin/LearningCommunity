package com.lst.lc.web.bean;

import java.util.Date;

public class Answer {
	//下面是回答部分
	private Integer answerId;
	private Date time;
	private String content;
	private String head;
	//下面是回答的用户信息
	private Integer userId;
	private String userName;
	private int integral;
	private String rank;
	private String avatar;
	public Answer() {
		super();
	}
	public Answer(Integer answerId, Date time, String content, String head,
			Integer userId, String userName, int integral, String rank,
			String avatar) {
		super();
		this.answerId = answerId;
		this.time = time;
		this.content = content;
		this.head = head;
		this.userId = userId;
		this.userName = userName;
		this.integral = integral;
		this.rank = rank;
		this.avatar = avatar;
	}
	public Integer getAnswerId() {
		return answerId;
	}
	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getIntegral() {
		return integral;
	}
	public void setIntegral(int integral) {
		this.integral = integral;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
}
