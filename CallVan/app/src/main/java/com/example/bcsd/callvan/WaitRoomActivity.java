package com.example.bcsd.callvan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by HYERIM on 2016-05-27.
 */
public class WaitRoomActivity  extends Activity {
    private static final String TAG = "WaitRoomActivity";

    private Button btnReady, btnReadyCancel, btnStart, btnCalls;
    private TextView start, end, time, mem, cost;
    private int costMem;
    private boolean makeUser = true;

    private RoomData data = new RoomData("학교", "야우리", "6시", 3, 2, "phl2898");
    private String joinId;
    private String id;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_room);

        btnReady = (Button)findViewById(R.id.btn_ready);
        btnReady.setOnClickListener(mClickListener);

        btnStart = (Button)findViewById(R.id.btn_start);
        btnStart.setOnClickListener(mClickListener);

        btnReadyCancel = (Button)findViewById(R.id.btn_ready_cancel);
        btnReadyCancel.setOnClickListener(mClickListener);

        btnCalls = (Button)findViewById(R.id.btn_calls);
        btnCalls.setOnClickListener(mClickListener);

        //Intent intent = getIntent();
        //id = (String)intent.getSerializableExtra("id");

        if(makeUser){
            btnStart.setVisibility(View.VISIBLE);
            btnReady.setVisibility(View.INVISIBLE);
        }else{
            btnStart.setVisibility(View.INVISIBLE);
            btnReady.setVisibility(View.VISIBLE);
        }

        start = (TextView)findViewById(R.id.room_start);
        start.setText(data.getStartLocation());

        end = (TextView)findViewById(R.id.room_end);
        end.setText(data.getArriveLocation());

        time = (TextView)findViewById(R.id.room_time);
        time.setText(data.getStartTime());

        mem = (TextView)findViewById(R.id.room_mem);
        mem.setText(data.getPresentMember() + " / " + data.getMinMemer());

        cost = (TextView)findViewById(R.id.room_cost);
        costMem = 15000 / data.getPresentMember();
        cost.setText(String.valueOf(costMem));

        data.addJoinList(joinId);

    }

    View.OnClickListener mClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_start:
                    btnStart.setVisibility(View.INVISIBLE);
                    btnCalls.setVisibility(View.VISIBLE);
                    break;

                case R.id.btn_calls:
                    Toast.makeText(getApplicationContext(), "콜밴전화", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.btn_ready:
                    data.setJoinList(joinId);
                    btnReady.setVisibility(View.INVISIBLE);
                    btnReadyCancel.setVisibility(View.VISIBLE);
                    break;

                case R.id.btn_ready_cancel:
                    data.setJoinList(joinId);
                    btnReady.setVisibility(View.VISIBLE);
                    btnReadyCancel.setVisibility(View.INVISIBLE);
                    break;
            }

        }
    };

}
