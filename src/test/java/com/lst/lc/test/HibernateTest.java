package com.lst.lc.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;

public class HibernateTest {
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	/*
	 * 特别说明：在实际生产中，Session和Transaction是不能作为成员变量的，会引起并发问题。 但本环境只是一个简单测试，所以忽略该问题。
	 */

	/**
	 * 初始化各种变量写在该方法内
	 */
	@Before
	public void init() {
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.build();

		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}

	/**
	 * 提交事务、关闭操作等等写在该方法内
	 */
	@After
	public void destroy() {
		transaction.commit();
		session.close();
		sessionFactory.close();
	}

	
}
