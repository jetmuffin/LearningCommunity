package com.lst.lc.hbase.service;

import java.util.List;

import org.apache.hadoop.hbase.client.ResultScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lst.lc.hbase.model.Log;

@Service
public class LogOperation {
	
	@Autowired
	private HbaseOperation mHbaseOperation;
	@Autowired
	private ModelMapping modelMapping;
	
	/**
	 * 将日志写入数据库
	 */
	public void add(Log log) {
		if((log == null)||(log.getKey().equals(""))){
			//传值有问题，处理一下
			return ;
		}
		mHbaseOperation.insertData("cloud_log", log.getKey(), "attr", "userEmail", log.getUserEmail());
		mHbaseOperation.insertData("cloud_log", log.getKey(), "attr", "time", log.getTime());
		mHbaseOperation.insertData("cloud_log", log.getKey(), "attr", "operation", log.getOperation());
	}

	/**
	 * 检索某用户的所有日志
	 */
	public List<Log> getByUserEmail(String userEmail) {
		ResultScanner rs = mHbaseOperation.queryByColumn("cloud_log", "attr", "userEmail", userEmail);
		return modelMapping.logListMapping(rs);
	}

	/**
	 * 根据时间范围检索日志
	 */
	public List<Log> getByTime(String userEmail, String min, String max) {
		ResultScanner rs = mHbaseOperation.queryLog(userEmail, min, max);
		return modelMapping.logListMapping(rs);
	}
}
