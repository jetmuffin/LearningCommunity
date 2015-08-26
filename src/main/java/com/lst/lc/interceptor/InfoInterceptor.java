package com.lst.lc.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lst.lc.dao.LetterDao;
import com.lst.lc.dao.UserDao;
import com.lst.lc.entities.User;

public class InfoInterceptor implements HandlerInterceptor {
        
        @Autowired
        @Qualifier("userDao")
        private UserDao userDao;
        private String loginUrl = "/LearningCommunity/index";
        
        @Autowired
        @Qualifier("letterDao")
        private LetterDao letterDao;

        @Override
        public void afterCompletion(HttpServletRequest arg0,
                        HttpServletResponse arg1, Object arg2, Exception arg3)
                        throws Exception {
                // TODO Auto-generated method stub
                
        }

        @Override
        public void postHandle(HttpServletRequest arg0,
                        HttpServletResponse arg1, Object arg2, ModelAndView arg3)
                        throws Exception {
                // TODO Auto-generated method stub
                
        }

        @Override
        public boolean preHandle(HttpServletRequest request,
                        HttpServletResponse response, Object object) throws Exception {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("loginUser");
                if(user == null){
                        return true;
                }else{
                        List<User> friends = userDao.getValidateFriends(user
                                        .getUserId());
                        int letters = letterDao.getUnRead(user.getUserId());
                        if (friends.size() > 0)
                                session.setAttribute("notity_friends",
                                                friends.size());
                        if (letters > 0)
                                session.setAttribute("notify_letters", letters);
                        return true;
                }
               
        }

}
