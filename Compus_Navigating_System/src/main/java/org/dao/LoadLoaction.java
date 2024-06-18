package org.dao;

import org.domin.Location;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class LoadLoaction {
    private static String sql = "select * from locations";
    public LoadLoaction(){}
    public static List<Location> getLoaction(){
        try{
            return JDBC.selectLocations(sql);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

}
