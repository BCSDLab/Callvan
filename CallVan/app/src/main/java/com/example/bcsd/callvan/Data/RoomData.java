package com.example.bcsd.callvan.Data;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

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
    private String createId;
    private ArrayList<String> joinList = new ArrayList<String>();

    public RoomData(String startLocation, String arriveLocation, String startTime, int minMemer, int presentMember, String createId ) {
        this.startLocation = startLocation;
        this.arriveLocation = arriveLocation;
        this.startTime = startTime;
        this.minMemer = minMemer;
        this.presentMember = presentMember;
        this.createId = createId;

        this.joinList.add( this.createId );
    }

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

    public void print(){
        Log.i( "RoomData : " , startLocation + " " + arriveLocation + ", " + startTime + ", "  +minMemer + ", " + joinList.get(0));
    }

    public void addJoinList(String joinId) {
        this.joinList.add(joinId);
        presentMember++;
    }
/*
    public void setJoinList(String readyId){
        boolean isSet = joinList.get(readyId);
        this.joinList.put(readyId, !isSet);
    }

    public Boolean getJoinList(String readyId){
        return joinList.get(readyId);
    }

    public boolean okJoinList(){
        int cnt = joinList.size();
        for(int i = 0; i <= cnt; i++){
            if(!joinList.get(i))
                return false;
        }
        return true;
    }
*/
    public void removeJoinList(String removeId) {
        this.joinList.remove(removeId);
        presentMember--;
    }

}
