package com.lst.lc.web.bean;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.lst.lc.utils.PropertiesUtil;

@WebServlet(name = "SystemVariable", urlPatterns = { "/SystemVariable" }, loadOnStartup = 1)
public class SystemVariable extends HttpServlet {
        private static final long serialVersionUID = 1L;

        public static String chatRoomAddress;
        public static String cppRunAddress;
        public static String javaRunAddress;

        public SystemVariable() {
                super();
        }

        public void init() throws ServletException {
                ServletContext application = this.getServletContext();
                
                chatRoomAddress = PropertiesUtil.getValue("chatRoomAddress");
                cppRunAddress = PropertiesUtil.getValue("cppRunAddress");
                javaRunAddress = PropertiesUtil.getValue("javaRunAddress");
                application.setAttribute("chatRoomAddress", chatRoomAddress);
                application.setAttribute("cppRunAddress", cppRunAddress);
                application.setAttribute("javaRunAddress", javaRunAddress);

        }
}