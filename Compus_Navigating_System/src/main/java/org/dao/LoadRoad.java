package org.dao;

import org.domin.Location;

import java.util.HashMap;

public class LoadRoad {
    private static String sql = "select * from road";
    public LoadRoad(){}
    public static HashMap<Location , Location> getRoads(){
        return JDBC.getRoads(sql);
    }
}
