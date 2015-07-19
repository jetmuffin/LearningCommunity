package com.lst.lc.hbase.model;

import com.lst.lc.utils.DateUtils;

public class Log {

		//主键
		String key = "";
		//用户
		String userEmail = "";
		//时间
		String time = "";
		//操作
		String operation = "";
		
		/**
		 * 构造方法，为了进行排序，请使用该构造方法
		 * @param userEmail 用户id
		 * @param operation  操作
		 */
		public Log(String userEmail, String operation) {
			super();
			this.userEmail = userEmail;
			this.operation = operation;
			try {
				this.time = DateUtils.getCurrentDateStr();
				String max = "99999999999999999";
				double d1 =   Double.parseDouble(max);
				double d2 = Double.parseDouble(DateUtils.getCurrentDateMS());
				this.key = String.valueOf(d1-d2)+userEmail;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public Log() {
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getUserEmail() {
			return userEmail;
		}

		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getOperation() {
			return operation;
		}

		public void setOperation(String operation) {
			this.operation = operation;
		}

}
