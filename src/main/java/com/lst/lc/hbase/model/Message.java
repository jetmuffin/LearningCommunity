package com.lst.lc.hbase.model;

import java.util.Date;

import com.lst.lc.utils.DateUtils;
import com.lst.lc.utils.EncryptUtils;

public class Message {
        
        private String key;
        private String fromUid;
        private String toUid;
        private String content;
        private String time;
        private String state;
        
        public Message() {
                super();
        }
        
        public Message(String fromUid, String toUid,
                        String content,  String state) throws Exception {
                super();
                String t = DateUtils.getDateString(new Date());
                String head, foot;
                if(fromUid.compareTo(toUid) < 0){
                        head = fromUid;
                        foot = toUid;
                }else{
                        head = toUid;
                        foot = fromUid;
                }
                String tempkey =  EncryptUtils.encryptMD5(head.getBytes())+EncryptUtils.encryptMD5(foot.getBytes())+t;
                this.key = tempkey;
                this.fromUid = fromUid;
                this.toUid = toUid;
                this.content = content;
                this.time = t;
                this.state = state;
        }

        public String getKey() {
                return key;
        }
        public void setKey(String key) {
                this.key = key;
        }
        public String getFromUid() {
                return fromUid;
        }
        public void setFromUid(String fromUid) {
                this.fromUid = fromUid;
        }
        public String getToUid() {
                return toUid;
        }
        public void setToUid(String toUid) {
                this.toUid = toUid;
        }
        public String getContent() {
                return content;
        }
        public void setContent(String content) {
                this.content = content;
        }
        public String getTime() {
                return time;
        }
        public void setTime(String time) {
                this.time = time;
        }
        public String getState() {
                return state;
        }
        public void setState(String state) {
                this.state = state;
        }

}
