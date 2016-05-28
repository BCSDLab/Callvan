package com.example.bcsd.callvan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
            String sql = "CREATE TABLE IF NOT EXITSTS Users(Email text primary key autoincrement,"
                    + "                                     Password text not null,"
                    + "                                     Device text not null,"
                    + "                                     Location text not null,"
                    + "                                     Login_status boolean not null);";
            database.execSQL(sql);
        } catch (Exception e) {
            Log.e("test", "CREATE TABLE FAILED! - " + e);
            e.printStackTrace();
        }
    }

        //String -> JSON -> Parsing -> DB
        public void insertJsonData(String jsonData){

            String email;
            String password;
            String device;
            String location;
            boolean login_status;

        }

}
