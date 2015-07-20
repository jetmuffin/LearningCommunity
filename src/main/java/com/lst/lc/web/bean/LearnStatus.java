package com.lst.lc.web.bean;

public class LearnStatus {
	
	private boolean ifLearn;
	private int progress;
	public LearnStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LearnStatus(boolean ifLearn, int progress) {
		super();
		this.ifLearn = ifLearn;
		this.progress = progress;
	}
	public boolean isIfLearn() {
		return ifLearn;
	}
	public void setIfLearn(boolean ifLearn) {
		this.ifLearn = ifLearn;
	}
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	
}
