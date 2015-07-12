package com.lst.lc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lst.lc.dao.UserDao;
import com.lst.lc.entities.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public void addUser(User user) {
		save(user);
	}

	@Override
	public void updateUser(User user) {
		update(user);
	}

	@Override
	public User getById(int userId) {
		return  get(User.class, userId);
	}

	@Override
	public void deleteUser(User user) {
		delete(user);
	}

	@Override
	public User validateUser(String email, String password) {
		String hql = "from User as user where user.email = ?";
		List<User> users = query(hql).setString(0, email).list();
		if(users.size() != 1)
			return null;
		User user = users.get(0);
		if(user.getPassword().equals(password))
			return user;
		else 
			return null;
	}

}
