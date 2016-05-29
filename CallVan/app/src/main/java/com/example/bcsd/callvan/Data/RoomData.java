package com.example.bcsd.callvan.Data;

/**
 * Information of Room
 */
public class RoomData {
    private int roomNum;
    private String startLocation;
    private String arriveLocation;
    private String startTime;
    private int minMemer;
    private int presentMember;

    public RoomData(int roomNum, String startLocation, String arriveLocation, String startTime,
             int minMemer, int presentMember){
        this.roomNum = roomNum;
        this.startLocation = startLocation;
        this.arriveLocation = arriveLocation;
        this.startTime = startTime;
        this.minMemer = minMemer;
        this.presentMember = presentMember;
    }

    public int getRoomNum(){return  roomNum;}

    public String getStartLocation(){
        return startLocation;
    }

    public String getArriveLocation(){
        return arriveLocation;
    }

    public String getStartTime(){
        return startTime;
    }

    public int getMinMemer(){
        return minMemer;
    }

    public int getPresentMember(){
        return presentMember;
    }

}
