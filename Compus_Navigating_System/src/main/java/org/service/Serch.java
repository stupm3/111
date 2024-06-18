package org.service;

import org.dao.JDBC;
import org.dao.LoadLoaction;
import org.dao.LoadRoad;
import org.dao.LoadSum;
import org.domin.Location;

import javax.xml.transform.Result;
import java.util.*;

public class Serch {
    static final int MAX = 0x7fffffff;
    private static List<Location> locations = LoadLoaction.getLoaction();
    private static HashMap<Location , Location> roads = LoadRoad.getRoads();
    private static String source;
    private static String to;
    public Serch(String source,String to){
        this.source = source;
        this.to = to;
    }
    private static Graph getGraph(){
        Graph graph = new Graph();
        Iterator<Location> it = roads.keySet().iterator();
        while(it.hasNext()){
            Location loc1 = it.next();
            Location loc2 = roads.get(loc1);
            graph.addEdge(loc1.getId(),loc2.getId(),JDBC.getDistance(loc1.getName(),loc2.getName()));
            graph.addEdge(loc2.getId(),loc1.getId(),JDBC.getDistance(loc1.getName(),loc2.getName()));
        }
        return graph;
    }
    private static int[] getTarget(){
        Iterator<Location> it = locations.iterator();
        int[] tar = new int[2];
        tar[0] = -1;
        tar[1] = -1;
        while(it.hasNext()){
            Location loc = it.next();
            if(loc.getName().equals(source))
                tar[0] = loc.getId();
            if(loc.getName().equals(to))
                tar[1] = loc.getId();
        }
        return tar;
    }
    public static List<String> getPath(){
        if(getTarget()[0] == getTarget()[1]) return null;
        if(getTarget()[0] == -1 || getTarget()[1] == -1) return null;
        List<Integer> paths = Dijkstra.dijkstra(getGraph(),getTarget()[0] ,getTarget()[1]);
        ArrayList<String> PreResult = new ArrayList<>();
        Iterator<Integer> it = paths.iterator();
        while(it.hasNext()){
            Iterator<Location> it2 = locations.iterator();
            int k = it.next();
            while(it2.hasNext()){
                Location loc = it2.next();
                if(k  == loc.getId()){
                    PreResult.add(loc.getName());
                    break;
                }
            }
        }
        List<String> Result = new ArrayList<>();
        for(int i = PreResult.size() - 1; i >= 0; i--){
            Result.add(PreResult.get(i));
        }
        return Result;
    }
    public static String getResult(List<String> path){
        String result = new String();
        if(path.isEmpty()) return result;
        Iterator<String> it = path.iterator();
        StringJoiner sj =  new StringJoiner(" -> ");
        sj.add(source);
        while(it.hasNext()){
            sj.add(it.next());
        }
        return sj.toString();
    }
    public static boolean serchRoad(String loc1 , String loc2){
        Iterator<Location> it = roads.keySet().iterator();
        while(it.hasNext()){
            Location location1 = it.next();
            Location location2 = roads.get(location1);
            if((loc1.equals(location1.getName()) && loc2.equals(location2.getName())) || (loc1.equals(location2.getName()) && loc2.equals(location1.getName()))){
                return true;
            }
        }
        return false;
    }
}




