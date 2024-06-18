package org.service;

import org.dao.JDBC;
import org.dao.LoadLoaction;
import org.dao.LoadRoad;
import org.domin.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class AddRoad {
    private static List<Location> locations = LoadLoaction.getLoaction();
    private static HashMap<Location , Location> roads = LoadRoad.getRoads();
    public AddRoad(){}
    public static boolean addRoad(String location1, String location2){
        if(location1.equals(null) || location2.equals(null) || location1.equals(location2)){
            return false;
        }
        Location loc1 = new Location();
        Location loc2 = new Location();
        Iterator<Location> it = locations.iterator();
        boolean found1 = false;
        boolean found2 = false;
        while(it.hasNext()){
            Location loc = it.next();
            if(loc.getName().equals(location1)){
                found1 = true;
                loc1 = loc;
            }
            if(loc.getName().equals(location2)){
                found2 = true;
                loc2 = loc;
            }
        }
        if(!found1 || !found2) return false;
        int dis = (int) Math.sqrt(Math.abs(loc1.getX() * loc1.getX() - loc2.getX() * loc2.getX() )) + (int)Math.sqrt(Math.abs(loc1.getY() * loc1.getY() - loc2.getY() * loc2.getY()));
        String sql = "insert into road( 1p , 2p , distance) values('"+loc1.getName() +"', '"+loc2.getName()+"' , '"+dis+"')";
        Iterator<Location> iterator = roads.keySet().iterator();
        while(iterator.hasNext()){
            Location l1 = iterator.next();
            Location l2 = roads.get(l1);
            if((l1.getName().equals(location1) && l2.getName().equals(location2))
                    || l1.getName().equals(location2) && l2.getName().equals(location1)
            ){
                return false;
            }
            }
        JDBC.update(sql);
        return true;
    }
}
