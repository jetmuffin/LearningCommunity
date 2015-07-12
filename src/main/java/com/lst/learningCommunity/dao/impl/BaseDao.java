package com.lst.learningCommunity.dao.impl;


import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 封装的dao基类,所有dao的实现类都应继承该类
 * @author sloriac
 *
 */
public class BaseDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 获取当前线程的session
	 * @return session对象
	 */
	public Session getSession(){
		return this.sessionFactory.getCurrentSession();
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

}