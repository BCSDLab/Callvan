package com.example.bcsd.callvan;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by kutemsys on 2016-05-25.
 */
public class RoomUser extends Activity implements AdapterView.OnItemSelectedListener {
    ArrayList<String> list;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_room_user);

        list = new ArrayList<String>();
        
        list.add("야우리");
        list.add("천안역");
        list.add("학교");
        list.add("병천");
        list.add("기타");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, list);
        //스피너 속성
        Spinner sp = (Spinner) this.findViewById(R.id.sp_start);
        sp.setPrompt("출발"); // 스피너 제목
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(this);


        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, list);
        //스피너 속성
        Spinner sp2 = (Spinner) this.findViewById(R.id.sp_end);
        sp2.setPrompt("도착"); // 스피너 제목
        sp2.setAdapter(adapter2);
        sp2.setOnItemSelectedListener(this);


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
