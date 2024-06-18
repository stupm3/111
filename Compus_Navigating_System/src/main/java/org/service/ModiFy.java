package org.service;

import org.dao.JDBC;
import org.dao.LoadUser;
import org.domin.User;

import java.util.Iterator;
import java.util.List;

public class ModiFy {
    private static List<User> users = LoadUser.getUsers();
    private static List<User> admins = LoadUser.getAdmins();
    public ModiFy(){}
    static public boolean  modifyUser(String username , String password){
        String sql = "update user set username = '"+ username +"',password = '" + password +"' where username='"+username+"'";
        if(username.equals("") || password.equals("")){
            return false;
        }
        Iterator<User> it = users.iterator();
        while(it.hasNext()){
            User user = it.next();
            if(user.getUsername().equals(username)){
                user.setPassword(password);
                JDBC.update(sql);
                return true;
            }
        }
        return false;
    }
    static public boolean  modifyAdmin(String username , String password){
        String sql = "update admin set password = '" + password +"' where username='"+username+"'";
        if(username.equals("") || password.equals("")){
            return false;
        }
        Iterator<User> it = admins.iterator();
        while(it.hasNext()){
            User user = it.next();
            if(user.getUsername().equals(username)){
                user.setPassword(password);
                JDBC.update(sql);
                return true;
            }
        }
        return false;
    }
}
