package org.dao;

import org.domin.User;

import java.util.List;

public class LoadUser {
    private static String SQL1 = "select * from user";
    private static String SQL2 = "select * from admin";
    public LoadUser(){}
    public static List<User> getUsers() {
        try {
            return JDBC.selectUsers(SQL1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static List<User> getAdmins()  {
        try {
            return JDBC.selectUsers(SQL2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
