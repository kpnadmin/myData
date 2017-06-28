package com.android.startactforres;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class SubActivity extends AppCompatActivity {

    private Button btn_sub;
    private final int RES_OK = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);


        btn_sub = (Button) findViewById(R.id.btn_sub);


        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent();
             Intent  intent = getIntent();
                int no1 = intent.getIntExtra("var1", 0) ;
                int no2 = intent.getIntExtra("var2", 0);
                int sum = no1 +no2;
                intent.putExtra("sum", sum);
                setResult(RES_OK, intent);
                finish();


            }
        });

    }





}
