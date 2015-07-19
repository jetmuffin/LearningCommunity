package com.lst.lc.hbase.service;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

public class CreateHbaseTable {

	private HbaseTemplate mHbaseTemplate;
	private List<String> mNameList;
	private List<String> mColumnList;

	public CreateHbaseTable() {
	}

	public CreateHbaseTable(HbaseTemplate mHbaseTemplate,
			List<String> mNameList, List<String> mColumnList) {
		super();
		this.mHbaseTemplate = mHbaseTemplate;
		this.mNameList = mNameList;
		this.mColumnList = mColumnList;

		createTables();
	}

	/**
	 * 创建Hbase表
	 * 
	 * @param tableName
	 *            表名数组
	 * @param column
	 *            列族数组
	 */
	public void createTables() {
		try {

			HBaseAdmin hBaseAdmin = new HBaseAdmin(
					mHbaseTemplate.getConfiguration());
			int columnNumbers = mColumnList.size();
			int nameNumbers = mNameList.size();

			for (int i = 0; i < nameNumbers; i++) {
				// 如果存在要创建的表，不做操作
				if (hBaseAdmin.tableExists(mNameList.get(i))) {
					System.out.println("表" + mNameList.get(i) + "已存在");
				} else {
					HTableDescriptor tableDescriptor = new HTableDescriptor(
							TableName.valueOf(mNameList.get(i)));
					System.out.println("创建表:" + mNameList.get(i));
					// 添加列族
					for (int j = 0; j < columnNumbers; j++) {
						tableDescriptor.addFamily(new HColumnDescriptor(
								mColumnList.get(j)));
					}
					hBaseAdmin.createTable(tableDescriptor);
				}
			}
			hBaseAdmin.close();
		} catch (MasterNotRunningException e) {
			e.printStackTrace();
		} catch (ZooKeeperConnectionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
