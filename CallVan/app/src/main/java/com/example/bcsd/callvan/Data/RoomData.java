package com.example.bcsd.callvan.Data;

/**
 * Information of Room
 */
public class RoomData {
    private String startLocation;
    private String arriveLocation;
    private String date;
    private int minMemer;
    private int presentMember;

    public RoomData(){
    }

    public RoomData(String startLocation, String arriveLocation, String date,
             int minMemer, int presentMember){
        this.startLocation = startLocation;
        this.arriveLocation = arriveLocation;
        this.date = date;
        this.minMemer = minMemer;
        this.presentMember = presentMember;
    }

    public String getStartLocation(){
        return startLocation;
    }

    public String getArriveLocation(){
        return arriveLocation;
    }

    public String getDate(){
        return date;
    }

    public int getMinMemer(){
        return minMemer;
    }

    public int getPresentMember(){
        return presentMember;
    }

    @Override
    public String toString() {
        return "RoomData{" +
                "startLocation='" + startLocation + '\'' +
                ", arriveLocation='" + arriveLocation + '\'' +
                ", date='" + date + '\'' +
                ", minMemer=" + minMemer +
                ", presentMember=" + presentMember +
                '}';
    }

    public void setStartLocation(String startLocation){
        this.startLocation = startLocation;
    }

    public void setArriveLocation(String arriveLocation){
        this.arriveLocation = arriveLocation;
    }

    public void setDate(String date){ this.date = date; }

    public void setMinMemer(int minMemer){
        this.minMemer = minMemer;
    }

    public void setPresentMember(int presentMember){
        this.presentMember = presentMember;
    }
}
