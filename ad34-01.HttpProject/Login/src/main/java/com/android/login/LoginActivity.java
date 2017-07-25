package com.android.login;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.mobilelibrary.HttpRequest;

import java.io.IOException;
import java.net.HttpURLConnection;

public class LoginActivity extends AppCompatActivity {

    private Button btn_login;
    private EditText edit_id;
    private EditText edit_pw;
    private TextView txt_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit_id = (EditText) findViewById(R.id.edit_id);
        edit_pw = (EditText) findViewById(R.id.edit_pw);
        txt_result = (TextView) findViewById(R.id.txt_result);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edit_id.getText().toString();
                String pw = edit_pw.getText().toString();
                new HttpLogin().execute(id, pw);

                // 클래스로 id, pw 전달
            }
        });

    }
    public String get_login(String id, String pw){

        String weburl = "http://192.168.0.59:8080/rest/Login";
        HttpRequest request = null;
        String response = "";


        try {
            request = new HttpRequest(weburl).addHeader("charset","utf-8");
            request.addParameter("id","test1id");
            request.addParameter("pw","test1pw");
            int httpCode = request.post();

            if(httpCode == HttpURLConnection.HTTP_OK){
                response = request.getStringResponse();

            }else{

            }
            //assertEquals("1", response);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            request.close();
        }

        return response;
    }



    // asyncTask
    public class HttpLogin extends AsyncTask<String, Integer, String>{
        private ProgressDialog waitDlg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            waitDlg = new ProgressDialog(LoginActivity.this);
            waitDlg.setMessage("버전 확인중");
            waitDlg.show();


            // ProgressDialog 보이기
        }
        @Override
        protected String doInBackground(String... params) {


            String id = params[0];
            String pw = params[1];

            String result = get_login(id, pw);


            return result;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            // ProgressDialog 감추기
            if(waitDlg != null){
                waitDlg.dismiss();
                waitDlg = null;
            }

            // 받은 결과 출력
            if(s.equals("1")){
                txt_result.setText("로그인 성공");
            }else{
                txt_result.setText("로그인 실패");
            }
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }


    }


}
