package org.service;

import org.dao.JDBC;
import org.dao.LoadUser;
import org.domin.User;

import java.util.Iterator;
import java.util.List;

public class Registe {
    private static List<User> users = LoadUser.getUsers();
    private static List<User> admins = LoadUser.getAdmins();


    public Registe(){}

    public static boolean userRegiste(String username , String password){
        String SQL1 = "insert into user (username , password) value( '"+username+"', '"+password+"')";
        if(username == null || password == null) return false;
        Iterator<User> it = users.iterator();
        while(it.hasNext()){
            User user = it.next();
            if(user.getUsername().equals(username))
                return false;
        }
        if(JDBC.update(SQL1) != 0)
            return true;
        return false;
    }
    public static boolean adminRegiste(String username, String password) {
        String SQL2 = "insert into admin (username , password) value( '"+username+"', '"+password+"')";
        if(username == null || password == null) return false;
        Iterator<User> it = admins.iterator();
        while(it.hasNext()){
            User user = it.next();
            if(user.getUsername().equals(username))
                return false;
        }
        if(JDBC.update(SQL2) != 0)
            return true;
        return false;
    }

}
