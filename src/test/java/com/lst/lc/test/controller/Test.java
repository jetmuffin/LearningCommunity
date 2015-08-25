package com.lst.lc.test.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.http.impl.cookie.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lst.lc.dao.BlogDao;
import com.lst.lc.dao.LetterDao;
import com.lst.lc.dao.QuestionDao;
import com.lst.lc.dao.UserDao;
import com.lst.lc.entities.Letter;
import com.lst.lc.entities.LetterId;
import com.lst.lc.entities.User;
import com.lst.lc.hbase.model.IntegralRecord;
import com.lst.lc.hbase.service.IntegralRecordOperation;
import com.lst.lc.utils.SetUtils;
import com.lst.lc.web.bean.Info;
import com.lst.lc.web.service.BlogPageHandler;
import com.lst.lc.web.service.LogHandler;

@Controller
@RequestMapping("/test")
public class Test {
        @Autowired
        private IntegralRecordOperation integralRecordOperation;
        @Autowired
        @Qualifier("userDao")
        private UserDao userDao;
        
        @Autowired
        @Qualifier("letterDao")
        private LetterDao letterDao;

        @Autowired
        @Qualifier("blogDao")
        private BlogDao blogDao;

        @Autowired
        @Qualifier("questionDao")
        private QuestionDao questionDao;

        @Autowired
        private LogHandler logHandler;

        @Autowired
        @Qualifier("blogPageHandler")
        private BlogPageHandler blogPageHandler;

        public Test() {
                super();
        }

        @RequestMapping(value = "/addFriend", method = RequestMethod.GET)
        public String add(Model model) {
                userDao.addRel(18, 19);
                return "frontend/user/login";
        }

        @RequestMapping(value = "/getFriend", method = RequestMethod.GET)
        public String get(Model model) {
                User user = userDao.getById(18);
                System.out.println(user.getRelUsersForUserId1().size());
                System.out.println(user.getRelUsersForUserId2().size());
                return "frontend/user/login";
        }

        @RequestMapping(value = "/set", method = RequestMethod.GET)
        public String set(Model model) {
                User user1 = userDao.getById(18);
                User user2 = userDao.getById(18);
                Set<User> set = new HashSet<User>();
                set.add(user1);
                set.add(user2);
                System.out.println(set.size());
                return "frontend/user/login";
        }

        @RequestMapping(value = "/merge", method = RequestMethod.GET)
        public String merge(Model model) {
                User user = userDao.getById(18);
                List<User> users = SetUtils.mergeFriend(
                                user.getRelUsersForUserId1(),
                                user.getRelUsersForUserId2(), user, 1);
                System.out.println(users.get(0).getUserName());
                return "frontend/user/login";
        }

        @RequestMapping(value = "/validate", method = RequestMethod.GET)
        public String validate(Model model) {
                userDao.validateFriend(18, 19, 1);
                return "frontend/user/login";
        }

        @RequestMapping(value = "/ifFriend", method = RequestMethod.GET)
        public String ifFriend(Model model) {
                System.out.println(userDao.ifFriend(18, 19));
                return "frontend/user/login";
        }

        @RequestMapping(value = "/info", method = RequestMethod.GET)
        public String info(Model model) {
                List<User> friends = userDao.getValidateFriends(18);
                if (friends.size() > 0) {
                        Info info = new Info();
                        info.setNum(friends.size());
                        List<String> messages = new ArrayList<String>();
                        for (int i = 0; i < friends.size(); i++) {
                                String str = friends.get(i).getUserName()
                                                + "请求添加你为好友";
                                messages.add(str);
                        }
                        info.setMessages(messages);
                        System.out.println(info.getNum());
                }
                return "frontend/user/login";
        }

        @RequestMapping(value = "/record", method = RequestMethod.GET)
        public String record(Model model, HttpSession session) throws Exception {
                User user = (User) session.getAttribute("loginUser");
                for (int i = 20150824; i > 20150800; i--) {
                        String key = user.getEmail() + String.valueOf(i);
                        int num = (int) (Math.random() * 50);
                        integralRecordOperation
                                        .updateTest(com.lst.lc.utils.DateUtils
                                                        .formatDate(com.lst.lc.utils.DateUtils
                                                                        .formatString(String
                                                                                        .valueOf(i),
                                                                                        "yyyyMMdd"),
                                                                        "yyyy-MM-dd"),
                                                        num, key);
                }
                return "frontend/user/login";
        }
        
        @RequestMapping(value = "/addLetter", method = RequestMethod.GET)
        public String addLetter(Model model) {
                LetterId id = new LetterId(19, 18, new Date());
                User fromUser = userDao.getById(19);
                User toUser = userDao.getById(18);
                Letter letter = new Letter(id, fromUser, toUser, "test", 0);
                letterDao.add(letter);
                
                List<Letter> letters = letterDao.getAll(18);
                System.out.println(letters.size());
                
                System.out.println(letterDao.getUnRead(18));
                
                return "frontend/user/login";
        }
}
