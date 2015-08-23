package com.lst.lc.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.lst.lc.entities.RelUser;
import com.lst.lc.entities.User;

public class SetUtils {
        
        public static List<User> mergeFriend(Set<RelUser> set1, Set<RelUser> set2, User user){
                Set<User> set = new HashSet<User>();
                for(Iterator<RelUser> iterator = set1.iterator(); iterator.hasNext();){
                        RelUser relUser1 = iterator.next();
                        set.add(relUser1.getUserByUserId1());
                        set.add(relUser1.getUserByUserId2());
                }
                for(Iterator<RelUser> iterator = set2.iterator(); iterator.hasNext();){
                        RelUser relUser2 = iterator.next();
                        set.add(relUser2.getUserByUserId1());
                        set.add(relUser2.getUserByUserId2());
                }
                set.remove(user);
                List<User> users = new ArrayList<User>(set);
                return users;
        }
}
