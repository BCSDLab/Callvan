package com.example.bcsd.callvan.Activiy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bcsd.callvan.Data.User;
import com.example.bcsd.callvan.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;


/**
 * Login and Register
 */
public class LoginActivity extends Activity implements View.OnClickListener{

    private TextView login_tv;
    private TextView regist_tv;
    private EditText email_et;
    private EditText password_et;
    User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_lin);

        init();
        initListener();
    }

    public void init(){
        login_tv = (TextView)findViewById(R.id.login_tv);
        regist_tv = (TextView)findViewById(R.id.regist_tv);
        email_et = (EditText)findViewById(R.id.email_et);
        password_et = (EditText)findViewById(R.id.password_et);

        user = new User(login_tv.getText().toString(), password_et.getText().toString(), "a");
    }

    public void initListener(){
        login_tv.setOnClickListener(this);
        regist_tv.setOnClickListener(this);
    }

    //get Device Id
    /*
    private String GetDevicesUUID(Context mContext){
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELECOM_SERVICE);

        String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        UUID devicedUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String deviceId = devicedUuid.toString();

        return deviceId;
    }
    */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_tv :
                // call AsynTask to perform network operation on separate thread
                new SendPost().execute("http://a.hanpyo.com");
                Intent log_intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(log_intent);
                finish();
            case R.id.regist_tv :
                Intent res_intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(res_intent);
                finish();
        }
    }

    public String POST(String url, User user) throws MalformedURLException {
        InputStream inputStream = null;
        String result = "";
        URL murl = new URL(url);
        try{
            // create HttpClient
            HttpClient httpClient = new DefaultHttpClient();
            //make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            String json = "";

            //build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("email", user.getEmail());
            jsonObject.accumulate("password", user.getPassword());
           // jsonObject.accumulate("device", user.getDevice());

            //convert JSONObject to JSON to String
            json = jsonObject.toString();

            //set json to StringEntity
            StringEntity se = new StringEntity(json);

            //set httpPost Entity
            httpPost.setEntity(se);

            // Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            //Execute POST request to the given URL
            HttpResponse httpResponse = httpClient.execute(httpPost);

            //receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            //convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                Toast.makeText(getApplicationContext(), "서버와 연결이 되지않습니다.", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        return result;
    }

    class SendPost extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                return POST(urls[0], user);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
        }

    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

}
