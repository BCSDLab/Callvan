package com.example.bcsd.callvan;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bcsd.callvan.Data.RoomData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by EUNBEE on 2016-05-28.
 */
public class Dao {
    private Context context;
    private SQLiteDatabase database;

    public Dao(Context context) {
        this.context = context;

        //SQLite 초기화
        database = context.openOrCreateDatabase("LocalDATA.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        //create table
        try {
            String sql = "CREATE TABLE IF NOT EXISTS Rooms(RoomNumber integer primary key autoincrement,"
                    + "                                      StartLocation text not null,"
                    + "                                      ArriveLocation text not null,"
                    + "                                      StartTime text not null,"
                    + "                                      MinMember integer not null,"
                    + "                                      PresentMember integer not null);";
            database.execSQL(sql);
        } catch (Exception e) {
            Log.e("test", "CREATE TABLE FAILED! - " + e);
            e.printStackTrace();
        }
    }

    public String getJsonData(){
        StringBuilder sb = new StringBuilder();
        sb.append("");

        sb.append("[");

        sb.append(" {");
        sb.append("     'RoomNumber':'1',");
        sb.append("     'StartLocation':'야우리',");
        sb.append("     'ArriveLocation':'학교',");
        sb.append("     'StartTime':'3시',");
        sb.append("     'MinMember':'3',");
        sb.append("     'PresentMember':'2'");
        sb.append(" },");

        sb.append(" {");
        sb.append("     'RoomNumber':'2',");
        sb.append("     'StartLocation':'병천',");
        sb.append("     'ArriveLocation':'학교',");
        sb.append("     'StartTime':'6시',");
        sb.append("     'MinMember':'2',");
        sb.append("     'PresentMember':'2'");
        sb.append(" }");

        sb.append("]");

        return sb.toString();
    }
        //String -> JSON -> Parsing -> DB
        public void insertJsonData(String jsonData){

            //json으로 데이터를 파싱할 때 쓸 임시 변수
            int roomNumber;
            String startLocation;
            String arriveLocation;
            String startTime;
            int minMember;
            int presentMember;

            try{
                JSONArray jsonArray = new JSONArray(jsonData);

                for(int i = 0 ; i <jsonArray.length() ; i++){
                    JSONObject jsonObject= jsonArray.getJSONObject(i);
                    roomNumber = jsonObject.getInt("RoomNumber");
                    startLocation = jsonObject.getString("StartLocation");
                    arriveLocation = jsonObject.getString("ArriveLocation");
                    startTime = jsonObject.getString("StartTime");
                    minMember = jsonObject.getInt("MinMember");
                    presentMember = jsonObject.getInt("PresentMember");

                    Log.i("test", "RoomNumber: " + roomNumber);

                    //Insert Data into DB
                    String sql = "INSERT INTO Rooms(RoomNumber, StartLocation, ArriveLocation, StartTime, MinMember, PresentMember)"
                            + " VALUES(" + roomNumber + ", '" + startLocation + "', '" + arriveLocation + "', '" + startTime +
                            "', " + minMember + ", " + presentMember + ");";

                    try {
                        database.execSQL(sql);
                    } catch (Exception e){
                        Log.e("test", "DB ERROR! - " + e);
                        e.printStackTrace();
                    }

                }
            } catch (JSONException e){
                Log.e("test", "JSON ERROR! - " + e);
                e.printStackTrace();
            }
        }

    public ArrayList<RoomData> getRoomDataList(){

        //DB에서 꺼낼 때 쓸 변수
        ArrayList<RoomData> roomDataList = new ArrayList<RoomData>();

        int roomNumber;
        String startLocation;
        String arriveLocation;
        String startTime;
        int minMember;
        int presentMember;

        //select data
        String sql = "SELECT * FROM Rooms;";
        Cursor cursor = database.rawQuery(sql, null);

        while(cursor.moveToNext()){
            roomNumber = cursor.getInt(0);
            startLocation = cursor.getString(1);
            arriveLocation = cursor.getString(2);
            startTime = cursor.getString(3);
            minMember = cursor.getInt(4);
            presentMember = cursor.getInt(5);

            roomDataList.add( new RoomData(roomNumber, startLocation, arriveLocation, startTime, minMember, presentMember));
        }
        cursor.close();
        return roomDataList;
    }

}
