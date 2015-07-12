package com.lst.learningCommunity.dao;

import com.lst.learningCommunity.entities.User;

public interface UserDao {

	/**
	 * 添加用户
	 * @param user
	 */
	public void addUser(User user);
	
	/**
	 * 更新用户信息
	 * @param user 用户信息
	 */
	public void updateUser(User user);
	
	/**
	 * 根据主键检索用户
	 * @param userId 用户id
	 * @return
	 */
	public User getById(int userId);
	
	/**
	 * 删除用户
	 * @param user 用户信息
	 */
	public void deleteUser(User user);
	
	/**
	 * 验证用户登录信息
	 * @param email
	 * @param password
	 * @return
	 */
	public User validateUser(String email, String password);
}
