package com.lst.lc.test.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lst.lc.dao.BlogDao;
import com.lst.lc.dao.QuestionDao;
import com.lst.lc.dao.UserDao;
import com.lst.lc.entities.User;
import com.lst.lc.utils.SetUtils;
import com.lst.lc.web.service.BlogPageHandler;
import com.lst.lc.web.service.LogHandler;

@Controller
@RequestMapping("/test")
public class Test {

        @Autowired
        @Qualifier("userDao")
        private UserDao userDao;

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
                List<User> users = SetUtils.mergeFriend(user.getRelUsersForUserId1(), user.getRelUsersForUserId2(), user, 1);
                System.out.println(users.get(0).getUserName());
                return "frontend/user/login";
        }
        
        @RequestMapping(value = "/validate", method = RequestMethod.GET)
        public String validate(Model model) {
                userDao.validateFriend(18, 19, 1);
                return "frontend/user/login";
        }
}
