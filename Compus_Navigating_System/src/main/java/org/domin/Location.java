package org.domin;

public class Location {
    private int id;
    private String name;
    private int x;
    private int y;
    private String description;
    public Location() {}
    public Location(String name , int x , int y , String des){
        this.name = name;
        this.x = x;
        this.y = y;
        this.description = des;
    }
    public Location(int id , String name , int x , int y , String des){
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.description = des;
    }
    public int getId(){
        return id;
    }
    public String getName() {
        return name;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public String getDescription() {
        return description;
    }
    public void setAll(String name , int x , int y , String description){
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.description = description;
    }
}
