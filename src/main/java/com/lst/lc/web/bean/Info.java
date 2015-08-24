package com.lst.lc.web.bean;

import java.util.List;

public class Info {

        private int num;
        private List<String> messages;
        
        public Info() {
                super();
        }
        public Info(int num, List<String> messages) {
                super();
                this.num = num;
                this.messages = messages;
        }
        public int getNum() {
                return num;
        }
        public void setNum(int num) {
                this.num = num;
        }
        public List<String> getMessages() {
                return messages;
        }
        public void setMessages(List<String> messages) {
                this.messages = messages;
        }
}
