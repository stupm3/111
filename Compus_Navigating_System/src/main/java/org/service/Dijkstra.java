package org.service;

import org.dao.LoadLoaction;
import org.dao.LoadRoad;
import org.domin.Location;

import java.util.*;
class Edge {
    int target;
    int weight;

    public Edge(int target, int weight) {
        this.target = target;
        this.weight = weight;
    }
}
class Graph {
    private Map<Integer, List<Edge>> adjList = new HashMap<>();

    public void addEdge(int source, int target, int weight) {
        adjList.computeIfAbsent(source, k -> new ArrayList<>()).add(new Edge(target, weight));
    }
    public List<Edge> getNeighbors(int vertex) {
        return adjList.getOrDefault(vertex, Collections.emptyList());
    }
}
public class Dijkstra {
    private static List<Location> locations = LoadLoaction.getLoaction();
    private static HashMap<Location , Location> roads = LoadRoad.getRoads();
    public static List<Integer> dijkstra(Graph graph , int source , int target){

        int now = source;
        int end = target;
        Map<Integer , Integer> distances = new HashMap<>();
        Map<Integer , Integer> previous = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for(int i = 0;i < locations.size(); i++){
            distances.put(i, Integer.MAX_VALUE );
            pq.offer(new int[]{i,distances.get(i)});
        }
        distances.put(now, 0);
        pq.offer(new int[]{now, 0});
        while(!pq.isEmpty()){
            int[] current = pq.poll();
            int vertex = current[0];
            int distance = current[1];
            if(vertex == end){
                break;
            }
            for(Edge edge : graph.getNeighbors(vertex)){
                int next = edge.target;
                int weight = edge.weight;
                int distanceToNext = distance + weight;
                if(distanceToNext < distances.get(next)){
                    distances.put(next, distanceToNext);
                    previous.put(next, vertex);
                    pq.offer(new int[]{next, distanceToNext});
                }
            }
        }
        return getPath(previous,end,new ArrayList<>());
    }
    private static List<Integer> getPath(Map<Integer , Integer> previous, int end, List<Integer> path){
        if(previous.get(end) == null){
            return Collections.emptyList();
        }
        path.add(end);
        getPath(previous,previous.get(end), path);
        return path;
    }
}
