package com.example.bcsd.callvan;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Created by EUNBEE on 2016-05-24.
 */
public class RoomData {
    private String startLocation;
    private String arriveLocation;
    private String startTime;
    private int minMemer;
    private int presentMember;
    private String createId;
    private LinkedHashMap<String, Boolean> joinList;

    RoomData(String startLocation, String arriveLocation, String startTime,
             int minMemer, int presentMember, String createId){
        this.startLocation = startLocation;
        this.arriveLocation = arriveLocation;
        this.startTime = startTime;
        this.minMemer = minMemer;
        this.presentMember = presentMember;
        this.createId = createId;

        this.joinList.put(createId,true);
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

    public void addJoinList(String joinId) {
        this.joinList.put(joinId,false);
        presentMember++;
    }

    public void setJoinList(String readyId){
        boolean isSet = joinList.get(readyId);
        this.joinList.put(readyId, !isSet);
    }

    public Boolean getJoinList(String readyId){
        return joinList.get(readyId);
    }

    public void removeJoinList(String removeId) {
        this.joinList.remove(removeId);
        presentMember--;
    }

    public boolean okJoinList(){
        int cnt = joinList.size();
        for(int i = 0; i <= cnt; i++){
            if(!joinList.get(i))
                return false;
        }
        return true;
    }
}
