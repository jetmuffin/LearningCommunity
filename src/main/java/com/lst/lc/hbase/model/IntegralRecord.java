package com.lst.lc.hbase.model;

import com.lst.lc.utils.DateUtils;

public class IntegralRecord {
        
        private String key;
        private String date;
        private String integral;
        
        public IntegralRecord() {
                super();
        }
        public IntegralRecord(String email, String integral) {
                super();
                this.key = email + DateUtils.getCurrentDate();
                this.date = DateUtils.getCurrentDateForShow();
                this.integral = integral;
        }
        
        public IntegralRecord(String key, String date, String integral) {
                super();
                this.key = key;
                this.date = date;
                this.integral = integral;
        }
        public String getKey() {
                return key;
        }
        public void setKey(String key) {
                this.key = key;
        }
        public String getDate() {
                return date;
        }
        public void setDate(String date) {
                this.date = date;
        }
        public String getIntegral() {
                return integral;
        }
        public void setIntegral(String integral) {
                this.integral = integral;
        }
        @Override
        public String toString() {
                return "IntegralRecord [key=" + key + ", date=" + date
                                + ", integral=" + integral + "]";
        }
}
