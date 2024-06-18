package org.service;

import org.dao.JDBC;
import org.dao.LoadLoaction;
import org.dao.LoadSum;
import org.domin.Location;
import org.view.AddFrame;

import java.util.*;

public class AddLoaction {
    static private int id = LoadSum.getLocationSum();
    private static boolean flag = false;
    private static List<Location> list = LoadLoaction.getLoaction();
    public AddLoaction() {}
    public static boolean addLocation(Location loc){
        String name = loc.getName();
        int x = loc.getX();
        int y = loc.getY();
        String des = loc.getDescription();
        String sql = "insert into locations(id,name,x,y,description) values("+ id++ +" ,'"+name+"' , '"+x+"','"+y+"','"+des+"')";
        if(name == null || x == 0 || y == 0 || des == null)
            return false;
        Iterator<Location> it = list.iterator();
        while(it.hasNext()){
            Location l = it.next();
            if(name.equals(l.getName() ) || ((x <= l.getX() + 80 && x >= l.getX() - 80) && (y <= l.getX() + 30 && y >= l.getX() - 30)))
                return false;
        }
        JDBC.update(sql);
        return true;
    }


}
