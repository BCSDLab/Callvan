package com.example.bcsd.callvan.Activiy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bcsd.callvan.R;

/**
 * Created by HYERIM on 2016-05-27.
 */
public class GatheringActivity extends Activity {
    private static final String TAG = "GatheringRoomActivity";

    private Button btnCall, btnCompleted;
    private boolean makeUser = true;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_room);

        btnCall = (Button)findViewById(R.id.btn_call);
        btnCall.setOnClickListener(mClickListener);

        btnCompleted = (Button)findViewById(R.id.btn_completed);
        btnCompleted.setOnClickListener(mClickListener);

        if(makeUser){
            btnCall.setVisibility(View.VISIBLE);
            btnCompleted.setVisibility(View.VISIBLE);
        }else{
            btnCall.setVisibility(View.INVISIBLE);
            btnCompleted.setVisibility(View.INVISIBLE);
        }

    }

    View.OnClickListener mClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_call:

                    break;

                case R.id.btn_completed:

                    break;

            }

        }
    };

}
