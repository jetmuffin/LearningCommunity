package com.lst.lc.utils;

import javax.servlet.http.HttpSession;

public class ControllerUtils {
        
        public static boolean validateLogin(HttpSession session){
                if(session.getAttribute("loginUser") == null)
                        return false;
                return true;
        }

}
