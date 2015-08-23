package com.lst.lc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserInterceptor implements HandlerInterceptor {
        
        private String loginUrl = "/LearningCommunity/user/login";

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
                if(session.getAttribute("loginUser") == null){
                        response.sendRedirect(loginUrl);
                        return false;
                }else{
                        return true;
                }
               
        }

}
