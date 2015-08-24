package com.lst.lc.web.backend.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lst.lc.dao.AdminDao;
import com.lst.lc.dao.BlogDao;
import com.lst.lc.dao.QuestionDao;
import com.lst.lc.dao.UserDao;
import com.lst.lc.entities.Admin;
import com.lst.lc.utils.EncryptUtils;

@Controller
@RequestMapping("/manage")
public class ManageController {

        @Autowired
        @Qualifier("adminDao")
        private AdminDao adminDao;
        
        @Autowired
        @Qualifier("blogDao")
        private BlogDao blogDao;

        @Autowired
        @Qualifier("userDao")
        private UserDao userDao;
        
        @Autowired
        @Qualifier("questionDao")
        private QuestionDao questionDao;

        public ManageController() {
                super();
        }

        @RequestMapping(value = "/login", method = RequestMethod.GET)
        public String login(Model model) {
                return "backend/admin/login";
        }

        @RequestMapping(value = "/login", method = RequestMethod.POST)
        public String login(HttpSession session, String email, String password,
                        RedirectAttributes redirectAttributes) throws Exception {
                Admin admin = adminDao.validateAdmin(email, password);
                if (admin == null) {
                        redirectAttributes
                                        .addFlashAttribute("loginMsg", "邮箱错误");
                        return "redirect:/manage/login";
                } else if (admin.getPassword().equals(password)) {
                        session.setAttribute("admin", admin);
                        return "redirect:/manage/index";
                } else {
                        redirectAttributes
                                        .addFlashAttribute("loginMsg", "密码错误");
                        return "redirect:/manage/login";
                }
        }

        @RequestMapping(value = "/logout", method = RequestMethod.GET)
        public String logout(HttpSession session,
                        RedirectAttributes redirectAttributes) {
                redirectAttributes.addFlashAttribute("logoutMsg", "退出成功");
                session.setAttribute("admin", null);
                return "redirect:/manage/login";
        }

        @RequestMapping(value = "/index", method = RequestMethod.GET)
        public String index(Model model) {
                long num = userDao.count();
                model.addAttribute("userNum", num);
                long num1 = blogDao.count();
                model.addAttribute("blogNum", num1);
                long num2 = blogDao.userCount();
                model.addAttribute("blogUserCount", num2);
                long num3 = questionDao.count();
                model.addAttribute("questionNum", num3);
                long num4 = questionDao.userCount();
                model.addAttribute("questionUserCount", num4);
                return "backend/index/index";
        }

        @RequestMapping(value = "/users", method = RequestMethod.GET)
        public String user(Model model) {
                model.addAttribute("module", "user");
                return "backend/user/list";
        }

        @RequestMapping(value = "/adduser", method = RequestMethod.GET)
        public String addUser(Model model) {
                model.addAttribute("module", "user");
                return "backend/user/add";
        }

}
