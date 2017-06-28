package com.android.startactivity;

import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Button btn1 = (Button) findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 새창 띄우기
                // getApplicationContext() 는 현재 실행중인 프로그램의 환경 정보
                //Intent intent =  new Intent(getApplicationContext(), SecondActivity.class);
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);

               /*// 인텐트 생성
                Intent intent = new Intent();
                //
                ComponentName comname = new ComponentName("com.android.startactivity"
                , "com.android.startactivity.SecondActivity"
                );
                intent.setComponent(comname);
                //
                startActivity(intent);
*/

            }
        });

    }
}
