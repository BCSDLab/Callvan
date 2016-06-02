package com.example.bcsd.callvan.Activiy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bcsd.callvan.Data.RoomData;
import com.example.bcsd.callvan.R;

/**
 * Created by HYERIM on 2016-05-27.
 */
public class WaitRoomActivity  extends Activity {
    private static final String TAG = "WaitRoomActivity";

    private Button btnReady, btnReadyCancel, btnStart, btnCalls, btnBack;
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

        btnBack = (Button)findViewById(R.id.btn_waitBack);
        btnBack.setOnClickListener(mClickListener);

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
                case R.id.btn_waitBack:
                    data.removeJoinList(joinId);
                    //푸시알림 날리기
                    //메인으로 이동하기
                    Intent intent =  new Intent(WaitRoomActivity.this, MainActivity.class);
                    startActivity(intent);
                case R.id.btn_start:
                    btnStart.setVisibility(View.INVISIBLE);
                    btnCalls.setVisibility(View.VISIBLE);
                    break;

                case R.id.btn_calls:
                    alertCall();
                    Toast.makeText(getApplicationContext(), "콜밴전화", Toast.LENGTH_SHORT).show();
                    break;
/*
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
                    */
            }

        }
    };

    public void alertCall(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(WaitRoomActivity.this);
        alertBuilder.setTitle("콜밴을 선택하세요.");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                WaitRoomActivity.this,
                android.R.layout.select_dialog_singlechoice);
        adapter.add("병천콜밴");
        adapter.add("가나콜밴");
        adapter.add("한기콜밴");

        alertBuilder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                String strName = adapter.getItem(id);
                AlertDialog.Builder innBuilder = new AlertDialog.Builder(
                        WaitRoomActivity.this);
                innBuilder.setMessage(strName);
                innBuilder.setTitle("당신이 선택한 것은 ");
                innBuilder
                        .setPositiveButton(
                                "확인",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog,
                                            int which) {
                                        dialog.dismiss();
                                    }
                                });
                innBuilder.show();
            }
        });
        alertBuilder.show();
    }


}
