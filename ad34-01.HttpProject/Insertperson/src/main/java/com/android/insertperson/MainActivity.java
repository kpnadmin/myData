package com.android.insertperson;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.mobilelibrary.HttpRequest;
import com.android.mobilelibrary.ModelPerson;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    private EditText edit_id, edit_pw, edit_name, edit_email;
    private Button btn_login;
    private TextView res_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_id = (EditText) findViewById(R.id.edit_id);
        edit_pw = (EditText) findViewById(R.id.edit_pw);
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_email = (EditText) findViewById(R.id.edit_email);
        res_login = (TextView) findViewById(R.id.res_login);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edit_id.getText().toString();
                String pw = edit_pw.getText().toString();
                String name = edit_name.getText().toString();
                String email = edit_email.getText().toString();
                new HttpInsert().execute(id, pw, name, email);
            }
        });

    }
    public String insert_Person(String id, String pw, String name, String email) {
        String weburl = "http://192.168.0.59:8080/rest/insertPerson";
        HttpRequest request = null;
        String response = "";
        String t = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());

        int httpCode = 0 ;
        try {
            ModelPerson obj = new ModelPerson(id, pw, name, email);
            String data = new Gson().toJson(obj);
            request = new HttpRequest(weburl).addHeader("charset","UTF-8").addHeader("Content-Type","application/json").addHeader("Accept","application/json");

            httpCode = request.post(data);

            if(httpCode == HttpURLConnection.HTTP_OK){
                try {
                    response = request.getStringResponse();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else{

            }
           /* assertNotNull(response);
            assertEquals("1", response);*/



        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            request.close();
        }



        return response;
    }


    //
    public class HttpInsert extends AsyncTask<String, Integer, String>{
        private ProgressDialog waitDlg;
        @Override
        protected String doInBackground(String... params) {
            String id = params[0];
            String pw = params[1];
            String name = params[2];
            String email = params[3];


            String result = insert_Person(id, pw, name, email);



            return result;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            waitDlg = new ProgressDialog(MainActivity.this);
            waitDlg.setMessage("버전 확인중");
            waitDlg.show();


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
                res_login.setText("insert 성공");
            }else{
                res_login.setText("insert 실패");
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }
    }



}
