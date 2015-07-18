package com.lst.lc.dao.impl;

import java.util.List;

import org.hibernate.Query;
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
		return get(User.class, userId);
	}

	@Override
	public void deleteUser(User user) {
		delete(user);
	}

	@Override
	public User validateUser(String email, String password) {
		String hql = "from User as user where user.email = ?";
		List<User> users = query(hql).setString(0, email).list();
		if (users.size() != 1)
			return null;
		User user = users.get(0);
		if (user.getPassword().equals(password))
			return user;
		else
			return null;
	}

	@Override
	public void addIntegral(int userId, int integral) {
		String hql = "update User as user set user.integral = user.integral + ? where user.userId = ?";
		Query query = query(hql).setInteger(0, integral).setInteger(1, userId);
		query.executeUpdate();
	}

	@Override
	public boolean ifEmailExisted(String email) {
		String hql = "from User as user where user.email = ?";
		Query query = query(hql).setString(0, email);
		List<User> users = query.list();
		if(users.size() != 0)
			return true;
		return false;
	}

}
