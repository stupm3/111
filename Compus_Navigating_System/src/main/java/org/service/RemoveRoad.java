package org.service;

import org.dao.JDBC;
import org.dao.LoadLoaction;
import org.dao.LoadRoad;
import org.domin.Location;
import org.view.MainFrame;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class RemoveRoad {
    MainFrame frame = new MainFrame(true);
    private static List<Location> locations = LoadLoaction.getLoaction();
    private static HashMap<Location , Location> roads = LoadRoad.getRoads();
    public RemoveRoad() {}
    public static boolean removeRoad(String location1 , String location2){
        if(location1.equals(null) || location2.equals(null) || location1.equals(location2)){
            return false;
        }
        Location loc1 = new Location();
        Location loc2 = new Location();
        Iterator<Location> it = locations.iterator();
        while(it.hasNext()){
            Location loc = it.next();
            if(loc.getName().equals(location1)){
                loc1 = loc;
            }
            if(loc.getName().equals(location2)){
                loc2 = loc;
            }
        }
        String sql = "delete from road where ( 1p = '"+loc1.getName()+"' && 2p = '"+loc2.getName()+"') || ( 1p = '"+loc2.getName()+"' && 2p = '"+loc1.getName()+"')";
        Iterator<Location> iterator = roads.keySet().iterator();
        while(iterator.hasNext()){
            while(iterator.hasNext()){
                Location l1 = iterator.next();
                Location l2 = roads.get(l1);
                if((l1.getName().equals(location1) && l2.getName().equals(location2))
                        || l1.getName().equals(location2) && l2.getName().equals(location1)
                ){
                    JDBC.update(sql);
                    return true;
                }
            }
        }
        return false;
    }
    public static void RemoveRoadL(String location){
        String sql = "delete from road where 1p = '"+ location+"' || 2p = '"+location+"' ";
        JDBC.update(sql);
    }
}
