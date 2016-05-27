package com.example.bcsd.callvan;

import android.util.Log;

/**
 * Created by EUNBEE on 2016-05-24.
 */
public class RoomData {
    private String startLocation;
    private String arriveLocation;
    private String startTime;
    private int minMemer;
    private int presentMember;

    RoomData(String startLocation, String arriveLocation, String startTime,
             int minMemer, int presentMember){
        this.startLocation = startLocation;
        this.arriveLocation = arriveLocation;
        this.startTime = startTime;
        this.minMemer = minMemer;
        this.presentMember = presentMember;
    }

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

    public void print(){
        Log.i( "RoomData : " , startLocation + " " + arriveLocation + ", " + startTime + ", "  +minMemer );
    }
}
