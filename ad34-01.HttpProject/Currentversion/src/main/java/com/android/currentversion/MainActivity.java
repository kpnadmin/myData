package com.android.currentversion;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText edtIpAddr;
    private Button btn_httpGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtIpAddr = (EditText) findViewById(R.id.edtIpAddr);
        btn_httpGet = (Button) findViewById(R.id.btn_httpGet);

        btn_httpGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpReQuestAsyncTask().execute();
            }
        });
    }

    public class HttpReQuestAsyncTask extends AsyncTask<Integer, Integer, Integer>{

        ProgressDialog waitDlg = null;


        @Override
        protected Integer doInBackground(Integer... params) {
           // http통신을 이용해서 데이터 받아오기
            Integer version = Request();

            return version;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            waitDlg = new ProgressDialog(MainActivity.this);
            waitDlg.setMessage("버전 확인중");
            waitDlg.show();


        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            // 프로그래스 바 감추기
            if(waitDlg != null){
                waitDlg.dismiss();
                waitDlg = null;
            }
            //결과를 화면에 출력
            TextView current_ver_text = (TextView) findViewById(R.id.current_ver_text);
            current_ver_text.setText(String.valueOf(integer));


        }
        @Override
        protected void onCancelled(Integer integer) {
            super.onCancelled(integer);

            // 프로그래스 바 감추기
            if(waitDlg != null){
                waitDlg.dismiss();
                waitDlg = null;
            }

        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }


    }

    private Integer Request() {
       Integer result = null;
       InputStream     in  = null;
       BufferedReader  rd  = null;
        HttpURLConnection httpConn = null;

        int TIME_OUT = 60;  // 초
        String HTTP_METHOD = "GET";

        String weburl = String.format("http://%s/rest/currentversion", edtIpAddr.getText().toString());




        try {
            URL url = new URL(weburl);
            httpConn = (HttpURLConnection) url.openConnection();//Connection 생성.
            httpConn.setConnectTimeout(60000); // 60초동안 서버접속을 기다린다.
            httpConn.setReadTimeout(60000);//60초동안 응답을 기다린다.
            httpConn.setRequestMethod("GET"); //HTTP 전송방식 GET, POST, PUT, DELETE
            httpConn.setRequestProperty("charset","utf-8");
            httpConn.connect(); // 서버접속 시작

            // 서버의 요청에 대한 응답코드 닫기
            int responseCode = httpConn.getResponseCode();
            // 200 =< responseCode && responseCode <= 299 만 정상
            if(responseCode < 200 || responseCode >= 300 ){
                // 오류
                Log.d("request", httpConn.getResponseMessage());

                return -1;
            }
            // 서버응답을 stream에 담은 후 String 으로 전환
              in      = httpConn.getInputStream();
              rd   = new BufferedReader(new InputStreamReader(in));
             StringBuffer bf     = new StringBuffer();
            // bf.append(line) 삭제

            String line = "";
            for(;(line = rd.readLine()) != null;){
                bf.append(line);

            }


            result = Integer.valueOf(bf.toString());


        }catch (IOException e){
            e.printStackTrace();
            result = -1;
        }finally {

               try {
                   if(rd !=null)   rd.close();  // 반드시 close 하시오. 메모리 누수 발생
                   if(in !=null)   in.close();
                   httpConn.disconnect(); // connection 닫기
               } catch (IOException e) {
                   e.printStackTrace();
               }

        }


        return result;
    }




}
