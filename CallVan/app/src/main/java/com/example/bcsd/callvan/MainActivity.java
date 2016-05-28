package com.example.bcsd.callvan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    Button btnWait, btnMake;
    private ArrayList<RoomData> listRoomArray = new ArrayList<RoomData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_lin);

        RoomData data1 = new RoomData("학교", "야우리", "6시", 3, 2);
        listRoomArray.add(data1);
        RoomData data2 = new RoomData("학교", "병천", "2시", 2, 1);
        listRoomArray.add(data2);
        RoomData data3 = new RoomData("천안역", "학교", "7시", 5, 3);
        listRoomArray.add(data3);

        ListView listView = (ListView)findViewById(R.id.callvanRoom_lv);
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
                    Intent intent =  new Intent(MainActivity.this, CreateRoomActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_wait:
                    intent =  new Intent(MainActivity.this, WaitRoomActivity.class);
                    startActivity(intent);
                    break;
            }

        }
    };

}
