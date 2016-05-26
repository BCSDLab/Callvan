package com.example.bcsd.callvan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

/**
 * Created by EUNBEE on 2016-05-24.
 */
public class IntroActivity extends Activity{
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.intro_lin);
        handler = new Handler();
        handler.postDelayed(mrun, 3000);
    }

    Runnable mrun  = new Runnable(){

        @Override
        public void run() {
            Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    };

    //Intro중에 뒤로가기를 누를 경우 핸들러를 끊음
    public void onBackPressed(){
        super.onBackPressed();
        handler.removeCallbacks(mrun);
    }
}
