package com.lst.lc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lst.lc.dao.AdminDao;
import com.lst.lc.entities.Admin;

@Repository("adminDao")
public class AdminDaoImpl extends BaseDao implements AdminDao {

	@Override
	public void addAdmin(Admin admin) {
		save(admin);
	}

	@Override
	public void updateAdmin(Admin admin) {
		update(admin);
	}

	@Override
	public Admin validateAdmin(String email, String password) {
		String hql = "from Admin as admin where admin.email = ?";
		List<Admin> admins = query(hql).setString(0, email).list();
		if (admins.size() != 1)
			return null;
		Admin admin = admins.get(0);
		return admin;
	}

}
