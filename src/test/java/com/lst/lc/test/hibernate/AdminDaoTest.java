package com.lst.lc.test.hibernate;

import java.util.List;

import org.junit.Test;

import com.lst.lc.entities.Admin;

public class AdminDaoTest extends BaseTestDao{
	
	@Test
	public void test(){
	}
	
	public Admin validateAdmin(String email, String password) {
		String hql = "from Admin as admin where admin.email = ?";
		List<Admin> admins = query(hql).setString(0, email).list();
		if (admins.size() != 1)
			return null;
		Admin admin = admins.get(0);
		return admin;
	}

}
