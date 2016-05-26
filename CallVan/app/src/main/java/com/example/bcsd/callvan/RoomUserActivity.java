package com.example.bcsd.callvan;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by kutemsys on 2016-05-25.
 */
public class RoomUserActivity extends Activity implements AdapterView.OnItemSelectedListener {
    ArrayList<String> listStart, listEnd;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_user);

        //출발장소 list
        listStart = new ArrayList<String>();
        listStart.add("출발 장소");
        listStart.add("야우리");
        listStart.add("천안역");
        listStart.add("학교");
        listStart.add("병천");
        listStart.add("기타");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, listStart);
        //스피너 속성
        Spinner sp = (Spinner) this.findViewById(R.id.sp_start);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(this);
        sp.setSelection(0);

        //도착장소 list - 출발장소 복사
        listEnd = (ArrayList<String>)listStart.clone();
        listEnd.set(0, "도착 장소");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, listEnd);
        //스피너 속성
        Spinner sp2 = (Spinner) this.findViewById(R.id.sp_end);
        sp2.setAdapter(adapter2);
        sp2.setOnItemSelectedListener(this);
        sp.setSelection(0);

    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {
        // TODO Auto-generated method stub
//        Toast.makeText(this, startList.get(arg2), Toast.LENGTH_LONG).show();//해당목차눌렸을때
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}
