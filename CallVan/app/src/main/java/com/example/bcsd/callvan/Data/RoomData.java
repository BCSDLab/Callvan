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
    private String startLocation;
    private String arriveLocation;
    private String date;
    private int minMemer; //최소 인원
    private int presentMember; //현재 인원
    private String createId; //생성자 아이디
    private ArrayList<String> joinList = new ArrayList<String>(); //인원 리스트

    public RoomData(String startLocation, String arriveLocation, String date, int minMemer, int presentMember, String createId ) {
        this.startLocation = startLocation;
        this.arriveLocation = arriveLocation;
        this.date = date;
        this.minMemer = minMemer;
        this.presentMember = presentMember;
        this.createId = createId;

        this.joinList.add( this.createId );
    }

    public RoomData(){
    }

    public RoomData(String startLocation, String arriveLocation, String date, int minMemer, int presentMember){
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

    public void print(){
        Log.i( "RoomData : " , startLocation + " " + arriveLocation + ", " + date + ", "  +minMemer + ", " + joinList.get(0));
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
