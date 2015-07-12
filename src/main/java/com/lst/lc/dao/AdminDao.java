package com.lst.lc.dao;

import com.lst.lc.entities.Admin;

public interface AdminDao {
	
	/**
	 * 添加管理员信息
	 * @param admin
	 */
	public void addAdmin(Admin admin);
	
	/**
	 * 更新管理员信息
	 * @param admin
	 */
	public void updateAdmin(Admin admin);
	
	/**
	 * 验证管理登录信息
	 * @param email
	 * @param password
	 */
	public Admin validateAdmin(String email, String password);
	
}
