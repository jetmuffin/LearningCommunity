package com.lst.lc.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/LoginInterceptor")
public class LoginInterceptor implements Filter {
	String[] IGNORE_PAGE = new String[] { "login.jsp","/login","/resources"};  
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;    
        HttpServletRequest req=(HttpServletRequest) request; 
        String uri = req.getRequestURI();
        System.out.println(uri);
        boolean doFilter = true;  
        for (String s : IGNORE_PAGE) {  
            if (uri.indexOf(s) != -1) {  
                // 如果uri中包含不过滤的uri，则不进行过滤  
                doFilter = false;  
                break;  
            }  
        }  
        //doFilter =false;  
        if(doFilter){
        	HttpSession session =  req.getSession();
        	Object obj = session.getAttribute("admin");  
        	if(obj == null)
        	{
        		String message = "请先登录";
        		session.setAttribute("loginMsg", message);
        		res.sendRedirect("/LearningCommunity/manage/login");
        	}
        	
        }
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
