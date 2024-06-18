package org.service;

import org.dao.JDBC;
import org.dao.LoadLoaction;
import org.domin.Location;

import java.util.Iterator;
import java.util.List;

public class RemoveLocation {
    private static List<Location> list = LoadLoaction.getLoaction();
    public RemoveLocation() {}
    public static boolean removeLocation(String location) {
        String sql = "delete from locations where name = '" + location + "'";
        if(location.equals(null))
            return false;
        Iterator<Location> iterator = list.iterator();
        while(iterator.hasNext()) {
            Location loc = iterator.next();
            if(loc.getName().equals(location)){
                JDBC.update(sql);
                RemoveRoad.RemoveRoadL(location);
                JDBC.updateSql();
                return true;
            }
        }
        return false;
    }
}
