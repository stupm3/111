package org.dao;

import org.domin.Location;
import org.domin.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class JDBC {
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static{
        InputStream asStream = JDBC.class.getResourceAsStream("/db.properties");
        Properties prop = new Properties();
        try {
            prop.load(asStream);
            URL = prop.getProperty("url");
            USER = prop.getProperty("user");
            PASSWORD = prop.getProperty("password");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnetion() {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
    public static int update(String sql) {
        try(Connection conn = getConnetion();
            PreparedStatement ps = conn.prepareStatement(sql);){
            return ps.executeUpdate();
        }catch(SQLException e){
        e.printStackTrace();
        }
        return 0;
    }
    public static List selectUsers(String sql){
        List list = new ArrayList<>();
        try(Connection conn = getConnetion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ){
            while(rs.next()){
                User user = new User(rs.getString("username"), rs.getString("password"));
                list.add(user);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public static List selectLocations(String sql){
        List<Location> list = new ArrayList<>();
        try(Connection conn = getConnetion();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();){
            while(rs.next()){
                Location location = new Location(rs.getInt("id") , rs.getString("name"),rs.getInt("x"),rs.getInt("y"),rs.getString("description"));
                list.add(location);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public static HashMap<Location , Location > getRoads(String sqlRoad){
        HashMap<Location , Location> locations = new HashMap<>();
        try(Connection conn = getConnetion();
        PreparedStatement  ps = conn.prepareStatement(sqlRoad);
        ResultSet rs = ps.executeQuery();){
            while(rs.next()){
                String sqlLocation1 = "select * from locations where name = '"+rs.getString("1p")+"'";
                String sqlLocation2 = "select * from locations where name = '"+rs.getString("2p")+"'";
                List<Location> location1s = selectLocations(sqlLocation1);
                List<Location> location2s = selectLocations(sqlLocation2);
                Iterator<Location> it1 = location1s.iterator();
                Iterator<Location> it2 = location2s.iterator();
                if(it1.hasNext() && it2.hasNext()){
                    locations.put(it1.next(),it2.next());
                }
            }
            return locations;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return locations;
    }
    public static int getDistance(String l1, String l2){
        int dis = 0;
        String sql = "select distance from road where (1p = '"+l1+"' && 2p = '"+l2+"') || (1p = '"+l2+"' && 2p = '"+ l1 +"')";
        try(Connection conn = getConnetion();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();){
            if(rs.next())
                dis = rs.getInt("distance");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return dis;
    }
    public static int getSum(String sql) {
        try (Connection conn = getConnetion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("sum");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return 0;
    }
    public static void updateSql(){
        String SelectSql = "select id from locations";
        int i = 0;
        try(Connection conn = getConnetion();
        PreparedStatement ps = conn.prepareStatement(SelectSql);
        ResultSet rs = ps.executeQuery();
        ){
            while(rs.next()){
                int k = rs.getInt("id");
                if(k != i++){
                    String UpdateSql = "update locations set id ='"+ (i - 1) +"' where id='"+ k +"'";
                    update(UpdateSql);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
