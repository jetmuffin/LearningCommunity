package com.lst.lc.dao;

import com.lst.lc.entities.Admin;

public interface AdminDao {
	
	/**
	 * 添加管理员信息
	 * @param admin
	 */
	public void addAdmin(Admin admin);
	
	/**
	 * 
	 * @param admin
	 */
	public void updateAdmin(Admin admin);
	
	public void validateAdmin(String email, String password);
	
}
