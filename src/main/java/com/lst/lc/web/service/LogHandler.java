package com.lst.lc.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lst.lc.dao.OperationDao;
import com.lst.lc.dao.RankDao;
import com.lst.lc.dao.UserDao;
import com.lst.lc.entities.User;

@Service
public class LogHandler {
	
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
	
	@Autowired
	@Qualifier("operationDao")
	private OperationDao operationDao;
	
	/**
	 * 将用户操作写入日志
	 * @param user
	 * @param operation
	 */
	public void toLog(User user, String operation){
		
	}
	
	/**
	 * 更新用户积分和等级信息
	 * @param userId
	 * @param operation
	 */
	public void updateIntegral(int userId, String operation){
		int integral = operationDao.getIntegral(operation);
		userDao.addIntegral(userId, integral);
	}
	
	

}
