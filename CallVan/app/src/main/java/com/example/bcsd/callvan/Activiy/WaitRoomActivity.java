package com.example.bcsd.callvan.Activiy;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bcsd.callvan.Data.CallVanData;
import com.example.bcsd.callvan.Adapter.CallVanAdapter;
import com.example.bcsd.callvan.Data.RoomData;
import com.example.bcsd.callvan.R;

import java.util.ArrayList;

/**
 * Created by HYERIM on 2016-05-27.
 */
public class WaitRoomActivity  extends Activity {
    private static final String TAG = "WaitRoomActivity";
    public WaitRoomActivity waitSelf= this;
    CallVanAdapter callVanAdapter;

    private Button btnReady, btnReadyCancel, btnStart, btnCalls, btnBack;
    private TextView start, end, time, mem, cost;
    private int costMem;
    private boolean makeUser = true;

    private RoomData data = new RoomData("학교", "야우리", "6시", 3, 2, "phl2898");
    private ArrayList<CallVanData> callVanList = new ArrayList<CallVanData>();

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
        time.setText(data.getDate());

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
                    //NotificationOut(); - 푸시알림 error 존재
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

        callVanAdapter = new CallVanAdapter(WaitRoomActivity.this,
                android.R.layout.select_dialog_singlechoice, callVanList);

        callVanList.add(new CallVanData("병천콜밴", "tel:0415628254"));
        callVanList.add(new CallVanData("붕붕콜밴", "tel:01085538222"));
        callVanList.add(new CallVanData("한기콜밴", "tel:01064976659"));
        callVanList.add(new CallVanData("아우내콜밴","tel:0415522114"));

        alertBuilder.setAdapter((ListAdapter) callVanList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                String strName = callVanList.get(id).callName;
                final String strNum = callVanList.get(id).callVanNum;
                AlertDialog.Builder innBuilder = new AlertDialog.Builder(
                        WaitRoomActivity.this);
                innBuilder.setMessage(strNum);
                innBuilder.setTitle(strName);
                innBuilder
                        .setPositiveButton(
                                "확인",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog,
                                            int which) {
                                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(strNum));
                                        startActivity(intent);
                                        dialog.dismiss();
                                    }
                                });
                innBuilder.show();
            }
        });
        alertBuilder.show();
    }

    public void NotificationOut(){
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Resources res = getResources();

        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.putExtra("notificationId", 9999); //전달할 값
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        PendingIntent intent = PendingIntent.getActivity(
                WaitRoomActivity.this, 0,
                new Intent(WaitRoomActivity.this, MainActivity.class), 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle("CallVan")
                .setContentText("상태바 드래그시 보이는 서브타이틀")
                .setTicker("방장 변경 : ")
                //.setSmallIcon(R.drawable.ic_launcher)
                //.setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.ic_launcher))
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_VIBRATE|Notification.DEFAULT_LIGHTS)
                .setNumber(13);

        Notification n = builder.build();
        nm.notify(1234, n);
    }
}
