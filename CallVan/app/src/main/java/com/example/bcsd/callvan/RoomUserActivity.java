package com.example.bcsd.callvan;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by kutemsys on 2016-05-25.
 */
public class RoomUserActivity extends Activity {
    private static final String TAG = "RoomUserActivity";

    ArrayList<String> listStart, listEnd;
    RoomData newRoom;
    String time, start, end;
    int minNum = 0;
    TimePicker tp;
    EditText etMin;
    Spinner spStart, spEnd;
    Button btnCreate;

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
        spStart = (Spinner) this.findViewById(R.id.sp_start);
        spStart.setAdapter(adapter);
        spStart.setOnItemSelectedListener(mOnItemSelectedListener);
        spStart.setSelection(0);

        //도착장소 list - 출발장소 복사
        listEnd = (ArrayList<String>)listStart.clone();
        listEnd.set(0, "도착 장소");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, listEnd);
        //스피너 속성
        spEnd = (Spinner) this.findViewById(R.id.sp_end);
        spEnd.setAdapter(adapter2);
        spEnd.setOnItemSelectedListener(mOnItemSelectedListener);
        spEnd.setSelection(0);

        tp = (TimePicker)findViewById(R.id.timePicker);
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener(){
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                time = String.valueOf(hourOfDay) + String.valueOf(minute);
            }
        });

        btnCreate = (Button)findViewById(R.id.btn_create);
        btnCreate.setOnClickListener(mClickListener);

        etMin = (EditText) findViewById(R.id.et_min_Mem);

    }

    View.OnClickListener mClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_create :
                    if(minNum != 0)
                        minNum = Integer.valueOf(etMin.getText().toString());

                    if(start.compareTo("출발 장소") == 0 && end.compareTo("도착 장소") == 0) {
                        Toast.makeText(getApplicationContext(),"장소를 선택하세요",Toast.LENGTH_LONG).show();
                    }else if( minNum <= 1) {
                        Toast.makeText(getApplicationContext(),"1명 이상을 선택하세요",Toast.LENGTH_LONG).show();
                    }else{
                        newRoom = new RoomData(start, end, time, minNum, 1);
                        System.out.print(newRoom);
                    }
                    break;

            }

        }
    };

    private AdapterView.OnItemSelectedListener mOnItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            start = spStart.getSelectedItem().toString();
            end = spEnd.getSelectedItem().toString();
            Log.i(TAG, "Spinner selected item = " + start + ", " + end);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
}
