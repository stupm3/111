package org.dao;

public class LoadSum {
    private static String RoadSumSelectSql = "select count(*) as sum from road";
    private static String LocationSumSelectSql = "select count(*) as sum from locations";
    public LoadSum(){}
    public static int getLoadSum() {
        return JDBC.getSum(RoadSumSelectSql);
    }
    public static int getLocationSum() {
        return JDBC.getSum(LocationSumSelectSql);
    }
}
