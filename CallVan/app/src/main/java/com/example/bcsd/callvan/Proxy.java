package com.example.bcsd.callvan;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by EUNBEE on 2016-05-31.
 */
public class Proxy {

    //URL변수를 넘겨줄지 결정하기
    public String getJSON(String _url){

        try{
            URL url = new URL(_url);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // 서버 접속시의 Time out(ms)
            conn.setConnectTimeout(10 * 1000);
            // Read시의 Time out(ms)
            conn.setReadTimeout(10 * 1000);

            // 요청 방식 선택
            conn.setRequestMethod("GET");
            // 연결 지속
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 캐릭터셋을 UTF-8로 요청
            conn.setRequestProperty("Accept-Charset", "UTF-8");

            // 캐시된 데이터를 사용하지 않고 매번 서버로 다시 받음
            conn.setRequestProperty("Cache-Control", "no-cache");
            // JSON 형식의 타입으로 데이터 요청
            conn.setRequestProperty("Accept", "application/json");

            // InputStream으로 서버로 부터 응답을 받음
            conn.setDoInput(true);
            conn.connect();

            int status = conn.getResponseCode();
            Log.i("test", "ProxyResponseCode : " + status);

            switch (status){
                //정상적으로 연결된 상태
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader( new InputStreamReader(conn.getInputStream()) );
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while( (line = br.readLine() ) != null){
                        sb.append(line + "\n");
                    }
                    br.close();

                    return sb.toString();
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.i("test", "NETWORK ERROR : " + e);
        }
        return null;
    }
}
