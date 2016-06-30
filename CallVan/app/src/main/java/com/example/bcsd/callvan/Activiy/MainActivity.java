package com.example.bcsd.callvan.Activiy;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.bcsd.callvan.Proxy;
import com.example.bcsd.callvan.R;
import com.example.bcsd.callvan.Adapter.RoomAdapter;
import com.example.bcsd.callvan.Data.RoomData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Main
 * CallVan Room List
 */
public class MainActivity extends AppCompatActivity {
    Button btnWait, btnMake;
    private ListView listView;
    RoomAdapter roomAdapter;
    //Dao dao;
    private ArrayList<RoomData> listRoomArray = new ArrayList<RoomData>();

    private String tag = "MainActivity";

    public MainActivity self = this;

    //Proxy proxy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_lin);

        init();
    }

    public void init(){
        listView = (ListView)findViewById(R.id.callvanRoom_lv);

       // new Async().execute();
        listView();
        makeSpinner();
    }

    private void listView(){

        new Async().execute();

        // Adapter 적용

        roomAdapter = new RoomAdapter(self, R.layout.listview_lin, listRoomArray);
        listView.setAdapter(roomAdapter);

        Log.d("TAG","먼저 갈게용!");
    }

    public void makeSpinner(){
        RoomAdapter roomAdapter = new RoomAdapter(this, R.layout.listview_lin, listRoomArray);
        listView.setAdapter(roomAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item

                // TODO : use item data.
            }
        }) ;

        //Spinner
        final Spinner start_sp = (Spinner)findViewById(R.id.start_sp);
        final Spinner arrive_sp = (Spinner)findViewById(R.id.arrive_sp);
        //ArrayAdapter
        ArrayAdapter start_ad = ArrayAdapter.createFromResource(
                this, R.array.location, android.R.layout.simple_spinner_item);
        start_sp.setAdapter(start_ad);

        ArrayAdapter arrive_ad = ArrayAdapter.createFromResource(
                this, R.array.location, android.R.layout.simple_spinner_item);
        arrive_sp.setAdapter(arrive_ad);

        btnMake = (Button)findViewById(R.id.makeRoom_bt);
        btnMake.setOnClickListener(mClickListener);

        //임시로 버튼 생성 - 리스트 뷰 리스너 구현시 삭제
        btnWait = (Button)findViewById(R.id.btn_wait);
        btnWait.setOnClickListener(mClickListener);
    }

    View.OnClickListener mClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.makeRoom_bt:
                    String createid = "callvantest";
                    Intent intent =  new Intent(MainActivity.this, CreateRoomActivity.class);
                    intent.putExtra("createId", createid);
                    startActivity(intent);
                    break;

                //임시 버튼 생성 - 리스트 뷰 리스너 구현시 구현 내용 이동
                case R.id.btn_wait:
                    intent =  new Intent(MainActivity.this, WaitRoomActivity.class);
                    startActivity(intent);
                    break;
            }

        }
    };

    class Async extends AsyncTask<Void, Void, String>{


        @Override
        protected String doInBackground(Void... params) {
            Proxy proxy = new Proxy();
            String jsonData = proxy.getJSON("http://a.hanpyo.com:3000/callvan/rooms/");
            return jsonData;
        }

        protected void onPostExecute(String result){
                    Log.d("TAG", "" + result);
                    try {
                        JSONArray jarray = new JSONArray(result);

                for(int i = 0 ; i < jarray.length() ; i++){
                    JSONObject jobject;
                    jobject = jarray.getJSONObject(i);

                    String startTime = jobject.getString("src");
                    String arriveTime = jobject.getString("dest");
                    String date = jobject.getString("date");
                    int minMembers = jobject.getInt("size");

                    Log.d("TAG", startTime);
                    Log.d("TAG", arriveTime);
                    Log.d("TAG", date);
                    Log.d("TAG", String.valueOf(minMembers));

                    RoomData roomData = new RoomData(startTime, arriveTime, date, minMembers, 0);

                    Log.d("TAG", "" + roomData);

                    roomAdapter.add(roomData);
                    roomAdapter.notifyDataSetChanged();
                    Log.d("TAG", "도착했습니당~");
                    Log.d("TAG", "" + listRoomArray.size());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
/**adaptor에 add method를 만들고
 * view는 listener만
 * view는 view만 관리합니다.
 * model의 수정, 삭제, 추가는 adapter에 위임합니다.
 * adapter에 add, update, delete
 */

}
