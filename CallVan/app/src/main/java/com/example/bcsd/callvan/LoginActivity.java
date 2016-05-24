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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_lin);

        //init login textView
        login_tv = (TextView)findViewById(R.id.login_tv);
        login_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_tv :
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
        }
    }
}
