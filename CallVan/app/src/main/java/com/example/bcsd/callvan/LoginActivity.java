package com.example.bcsd.callvan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by EUNBEE on 2016-05-24.
 */
public class LoginActivity extends Activity implements View.OnClickListener{

    private TextView login_tv;
    private TextView regist_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_lin);

        //init login textView
        login_tv = (TextView)findViewById(R.id.login_tv);
        regist_tv = (TextView)findViewById(R.id.regist_tv);
        login_tv.setOnClickListener(this);
        regist_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_tv :
                Intent log_intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(log_intent);
                finish();
            case R.id.regist_tv :
                Intent res_intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(res_intent);
                finish();
        }
    }
}
