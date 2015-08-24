package com.lst.lc.hbase.service;

import java.util.List;

import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lst.lc.hbase.model.Log;
import com.lst.lc.hbase.model.Message;
import com.lst.lc.utils.EncryptUtils;

@Service
public class MessageOperation {
        
        @Autowired
        private HbaseOperation mHbaseOperation;
        @Autowired
        private ModelMapping modelMapping;
        
        /**
         * 将日志写入数据库
         */
        public void add(Message message) {
                if((message == null)||(message.getKey().equals(""))){
                        //传值有问题，处理一下
                        return ;
                }
                mHbaseOperation.insertData("cloud_log", message.getKey(), "attr", "fromUid", message.getFromUid());
                mHbaseOperation.insertData("cloud_log", message.getKey(), "attr", "toUid", message.getToUid());
                mHbaseOperation.insertData("cloud_log", message.getKey(), "attr", "content", message.getContent());
                mHbaseOperation.insertData("cloud_log", message.getKey(), "attr", "time", message.getTime());
                mHbaseOperation.insertData("cloud_log", message.getKey(), "var", "state", message.getState());
        }

        public List<Message> getAllMessage(int uid) throws Exception{
                String substr = EncryptUtils.encryptMD5(String.valueOf(uid).getBytes());
                ResultScanner rs = mHbaseOperation.queryByVagueRowKey("message", substr);
                return modelMapping.messageListMapping(rs);
                
        }
}
