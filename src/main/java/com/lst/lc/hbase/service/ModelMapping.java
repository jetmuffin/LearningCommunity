package com.lst.lc.hbase.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.springframework.stereotype.Service;

import com.lst.lc.hbase.model.IntegralRecord;
import com.lst.lc.hbase.model.Log;
import com.lst.lc.hbase.model.Message;

@Service
public class ModelMapping {

	/**
	 * 将数据库读出的数据映射到Log的 List
	 * @param rs
	 * @return
	 */
	public List<Log> logListMapping(ResultScanner rs){
		List<Log> list = new ArrayList<Log>();
		for (Result r : rs) {
			Log log = new Log();
			log.setKey(new String(r.getRow()));
			for(Cell cell:r.rawCells()){
				String v = new String(CellUtil.cloneQualifier(cell));
				String val = new String(CellUtil.cloneValue(cell));
				if (v.equals("userEmail")) {
					log.setUserEmail(val);
				}
				if (v.equals("time")) {
					log.setTime(val);
				}
				if (v.equals("operation")) {
					log.setOperation(val);
				}
			}
			list.add(log);
		}
		rs.close();
		if (list.size() == 0) {
			return null;
		}
		return list;
	}
	
	 /**
         * 将数据库读出的数据映射到Message的 List
         * @param rs
         * @return
         */
        public List<Message> messageListMapping(ResultScanner rs){
                List<Message> list = new ArrayList<Message>();
                for (Result r : rs) {
                        Message message = new Message();
                        message.setKey(new String(r.getRow()));
                        for(Cell cell:r.rawCells()){
                                String v = new String(CellUtil.cloneQualifier(cell));
                                String val = new String(CellUtil.cloneValue(cell));
                                if (v.equals("fromUid")) {
                                        message.setFromUid(val);
                                }
                                if (v.equals("toUid")) {
                                       message.setToUid(val);
                                }
                                if (v.equals("content")) {
                                       message.setContent(val);
                                }
                                if (v.equals("time")) {
                                        message.setTime(val);
                                 }
                                if (v.equals("state")) {
                                        message.setState(val);
                                 }
                        }
                        list.add(message);
                }
                rs.close();
                if (list.size() == 0) {
                        return null;
                }
                return list;
        }
        
        public List<IntegralRecord> integralListMapping(ResultScanner rs){
                List<IntegralRecord> list = new ArrayList<IntegralRecord>();
                for (Result r : rs) {
                        IntegralRecord IntegralRecord = new IntegralRecord();
                        IntegralRecord.setKey(new String(r.getRow()));
                        for(Cell cell:r.rawCells()){
                                String v = new String(CellUtil.cloneQualifier(cell));
                                String val = new String(CellUtil.cloneValue(cell));
                                if (v.equals("date")) {
                                        IntegralRecord.setDate(val);
                                }
                                if (v.equals("integral")) {
                                       IntegralRecord.setIntegral(val);
                                }
                        }
                        list.add(IntegralRecord);
                }
                rs.close();
                if (list.size() == 0) {
                        return null;
                }
                return list;
        }
        
        public IntegralRecord integralRecordMapping(Result rs, String rowkey) {
                IntegralRecord integral = new IntegralRecord();
                if (rs.isEmpty()) {
                        // 没有检索到，说明数据库中没有该图片，返回错误信息
                        return null;
                } else {
                        integral.setKey(rowkey);
                        for (Cell cell : rs.rawCells()) {
                                String v = new String(
                                                CellUtil.cloneQualifier(cell));
                                String val = new String(
                                                CellUtil.cloneValue(cell));
                                if (v.equals("date")) {
                                        integral.setDate(val);
                                }
                                if (v.equals("integral")) {
                                        integral.setIntegral(val);
                                }
                        }
                }
                return integral;
        }
}
