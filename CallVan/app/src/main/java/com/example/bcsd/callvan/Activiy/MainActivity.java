package com.example.bcsd.callvan.Activiy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.bcsd.callvan.R;
import com.example.bcsd.callvan.Adapter.RoomAdapter;
import com.example.bcsd.callvan.Data.RoomData;

import java.util.ArrayList;
/**
 * Main
 * CallVan Room List
 */
public class MainActivity extends AppCompatActivity {

    private ListView listView;
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

        listView = (ListView)findViewById(R.id.callvanRoom_lv);
        makeSpinner();

    }

    public void makeSpinner(){
        RoomAdapter roomAdapter = new RoomAdapter(this, R.layout.listview_lin, listRoomArray);
        listView.setAdapter(roomAdapter);
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
    }
}
