package com.lst.lc.test.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * hibernate测试基类，所有hibernate测试类都要继承该类
 * @author sloriac
 *
 */
public class BaseTestDao {
	
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	public void init() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(ssr);
        System.out.println(sessionFactory.toString());
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}
	
	public void destroy() {
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	
	/**
	 * 获取当前线程的session
	 * @return session对象
	 */
	public Session getSession(){
		init();
		return this.session;
	}
	
	/**
	 * 保存操作
	 * @param object 需要保存的对象
	 */
	public void save(Object object){
		getSession().save(object);
	}
	
	/**
	 * 更新操作
	 * @param object 更新对象
	 */
	public void update(Object object){
		getSession().update(object);
	}
	
	/**
	 * 保存或者更新
	 * @param object 操作对象
	 */
	public void saveOrUpdate(Object object){
		getSession().saveOrUpdate(object);
	}
	
	/**
	 * get操作
	 * @param arg0 类
	 * @param arg1 主键
	 * @return
	 */
	public <T> T get(Class<?> arg0, Serializable arg1){
		return (T) getSession().get(arg0, arg1);
	}
	
	/**
	 * 获取Query
	 * @param hql
	 * @return
	 */
	public Query query(String hql){
		return getSession().createQuery(hql);
	}
	
	/**
	 * 删除
	 * @param object 对象
	 */
	public void delete(Object object){
		getSession().delete(object);
	}
	
	/**
	 * 获取某个表的全部信息
	 * @param entity 表对应实体的类名
	 * @return 实体List
	 */
	public <T> List<T> getAll(String entity){
		String string = "from " + entity;
		Query query = query(string);
		@SuppressWarnings("unchecked")
		List<T> ts = query.list();
		return ts;
	}
	
	public SQLQuery sqlQuery(String query){
		return getSession().createSQLQuery(query);
	}
}
