package com.android.basicwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 선언, 찾기, 설정
        Button btn1 = null;
        btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(),"btn1", Toast.LENGTH_SHORT).show();
                // 새창띄우기
                Intent intent = new Intent(getApplicationContext(), RadioActivity.class);
                //
                startActivity(intent);
            }
        });
        // 선언, 찾기, 설정
        Button btn2 = null;
        btn2 = (Button)findViewById(R.id.btn2);


    }



}
