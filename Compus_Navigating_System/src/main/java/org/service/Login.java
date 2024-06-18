package org.service;

import org.dao.LoadUser;
import org.domin.User;

import java.util.Iterator;
import java.util.List;


public class Login {
    private static List<User> users = LoadUser.getUsers();
    private static List<User> admins = LoadUser.getAdmins();
    public Login(){}
    public  static boolean loginUser(String username , String password){
        if(username == null || password == null) return false;
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()){
            User user = iterator.next();
            if(user.getUsername().equals(username)&& user.getPassword().equals(password) )
                return true;
        }
        return false;
    }
    public static boolean loginAdmin(String username , String password ){
        if(username == null || password == null) return false;
        Iterator<User> it = admins.iterator();
        while(it.hasNext()){
            User user = it.next();
            if(user.getUsername().equals(username)&& user.getPassword().equals( password) )
                return true;
        }
        return false;
    }


}
